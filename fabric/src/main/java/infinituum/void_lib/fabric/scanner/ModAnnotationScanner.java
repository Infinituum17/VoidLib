package infinituum.void_lib.fabric.scanner;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.ScannedFile;
import infinituum.void_lib.fabric.scanner.impl.ScannedModFile;
import infinituum.void_lib.fabric.utils.DevModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public final class ModAnnotationScanner {
    private volatile static ModAnnotationScanner INSTANCE;
    private final Set<String> excludedMods;
    private final Set<String> excludedAuthors;
    private final Set<ScannedFile> result;

    private ModAnnotationScanner() {
        Collection<ModContainer> loadedMods = FabricLoader.getInstance().getAllMods();
        this.excludedMods = Set.of("java", "minecraft", "fabricloader", "mixinextras", "void_lib");
        this.excludedAuthors = Set.of("FabricMC");

        VoidLib.LOGGER.info("Scanning jar files for annotations...");

        long startTime = System.currentTimeMillis();
        this.result = scan(loadedMods);
        long endTime = System.currentTimeMillis();

        VoidLib.LOGGER.info("Scan completed in {} ms!", (endTime - startTime));
    }

    /**
     * Creates (if it doesn't exist) and returns an instance of {@link ModAnnotationScanner}
     *
     * @return An instance (singleton) of {@link ModAnnotationScanner}
     */
    public static ModAnnotationScanner init() {
        if (INSTANCE == null) {
            synchronized (ModAnnotationScanner.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ModAnnotationScanner();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Returns the list of {@link ScannedFile ScannedFiles}
     *
     * @return The list of {@link ScannedFile ScannedFiles}
     */
    public Set<ScannedFile> get() {
        return result;
    }

    /**
     * Returns the list of {@link ScannedFile ScannedFilees} that have the annotation passed in
     *
     * @param annotationClass The annotation to search for
     * @return The list of {@link ScannedFile ScannedFiles}
     */
    public Set<ScannedFile> search(Class<?> annotationClass) {
        return this.get()
                .stream()
                .filter(modFile -> modFile
                        .getAnnotatedClasses()
                        .stream()
                        .anyMatch(clazz -> clazz != null && clazz.contains(annotationClass)))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the list of {@link ScannedFile ScannedFiles} that have the annotation and depend on the id passed in
     * <p>
     * <b>NOTE</b>: if the scanned file's mod-id is equal to the id passed in, the file is automatically included
     *
     * @param annotationClass The annotation to search for
     * @param dependencyId    The dependency mod-id
     * @return The list of {@link ScannedFile ScannedFiles}
     */
    public Set<ScannedFile> search(Class<?> annotationClass, String dependencyId) {
        return this.get()
                .stream()
                .filter(modFile -> modFile
                        .getDependencies()
                        .stream()
                        .anyMatch(dependency -> dependencyId.equals(dependency.getModId()))
                        || dependencyId.equals(modFile.getModId())
                        || (FabricLoader.getInstance().isDevelopmentEnvironment() && (modFile.getModId().isEmpty() || modFile.getModId().startsWith("generated_"))))
                .filter(modFile -> modFile
                        .getAnnotatedClasses()
                        .stream()
                        .anyMatch(clazz -> clazz.contains(annotationClass)))
                .collect(Collectors.toSet());
    }

    private Set<ScannedFile> scan(Collection<ModContainer> mods) {
        Set<ScannedFile> result = new HashSet<>();

        for (ModContainer mod : mods) {
            String modId = mod.getMetadata().getId();
            Collection<Person> authors = mod.getMetadata().getAuthors();

            boolean isExcludedMod = excludedMods.contains(modId);
            boolean isExcludedAuthor = authors
                    .stream()
                    .anyMatch(person -> excludedAuthors.contains(person.getName()));

            if (!isExcludedMod && !isExcludedAuthor) {
                Set<AnnotatedClass> classes = scanEntrypoints(mod.getRootPaths());

                if (!classes.isEmpty()) {
                    VoidLib.LOGGER.info("Found {} annotated classes in mod '{}'", classes.size(), modId);
                    result.add(new ScannedModFile(mod, classes));
                }
            }

            if (!mod.getContainedMods().isEmpty()) {
                scan(mod.getContainedMods());
            }
        }

        try {
            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                try {
                    Path baseDir = FabricLoader.getInstance().getGameDir();

                    if (!baseDir.isAbsolute()) {
                        baseDir = baseDir.toAbsolutePath().getParent();
                    }

                    Path developmentEntrypoint = baseDir.getParent()
                            .resolve("build")
                            .resolve("classes")
                            .resolve("java")
                            .resolve("main");

                    try {
                        List<Path> rootPaths = List.of(developmentEntrypoint);
                        Set<AnnotatedClass> classes = scanEntrypoints(rootPaths);

                        if (!classes.isEmpty()) {
                            VoidLib.LOGGER.info("Found current DEV mod with {} annotated classes", classes.size());

                            result.add(new ScannedModFile(new DevModContainer(rootPaths), classes));
                        }
                    } catch (Exception e) {
                        VoidLib.LOGGER.warn("Could not read 'build/classes/java/main' directory in development environment. Searched path: {}", developmentEntrypoint);
                    }
                } catch (Exception e) {
                    VoidLib.LOGGER.warn("Could not determine development entrypoint (should be 'build/main/java')");
                }
            }
        } catch (Exception e) {
            VoidLib.LOGGER.warn("Could not read developed mod's classes. Ignore this message if you're running tests");
        }

        return result;
    }

    private Set<AnnotatedClass> scanEntrypoints(List<Path> entrypoints) {
        Set<AnnotatedClass> annotatedClasses = new HashSet<>();

        for (Path entrypoint : entrypoints) {
            annotatedClasses.addAll(scanEntrypoint(entrypoint));
        }

        return Collections.unmodifiableSet(annotatedClasses);
    }

    /**
     * Manually scan a directory
     *
     * @param entrypoint Path to the directory to scan
     * @return The list of annotated classes (see {@link AnnotatedClass})
     */
    public Set<AnnotatedClass> scanEntrypoint(Path entrypoint) {
        ModAnnotationFileTreeVisitor visitor = new ModAnnotationFileTreeVisitor(entrypoint);

        return scanAnnotations(visitor);
    }

    private Set<AnnotatedClass> scanAnnotations(ModAnnotationFileTreeVisitor visitor) {
        try {
            Files.walkFileTree(visitor.getBasePath(), visitor);
        } catch (IOException e) {
            VoidLib.LOGGER.error("Could not visit classes of entrypoint '{}'", visitor.getBasePath());
        }

        return visitor.getAnnotatedClasses();
    }
}
