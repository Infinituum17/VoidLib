package infinituum.void_lib.fabric.scanner;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.ScannedFile;
import infinituum.void_lib.fabric.scanner.impl.ScannedModFile;
import infinituum.void_lib.fabric.utils.DevModContainer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ModAnnotationScanner {
    private static ModAnnotationScanner INSTANCE;
    private final Set<String> excludedMods;
    private final Set<ScannedFile> result;

    private ModAnnotationScanner() {
        Collection<ModContainer> loadedMods = FabricLoader.getInstance().getAllMods();
        this.excludedMods = Set.of("java", "minecraft", "fabricloader", "mixinextras", "void_lib");
        this.result = scan(loadedMods);
    }

    public static ModAnnotationScanner init() {
        if (INSTANCE == null) {
            INSTANCE = new ModAnnotationScanner();
        }

        return INSTANCE;
    }

    public Set<ScannedFile> get() {
        return result;
    }

    private Set<ScannedFile> scan(Collection<ModContainer> mods) {
        long startTime = System.currentTimeMillis();
        VoidLib.LOGGER.info("Scanning jar files for annotations...");
        Set<ScannedFile> result = new HashSet<>();

        for (ModContainer mod : mods) {
            String modId = mod.getMetadata().getId();

            if (!excludedMods.contains(modId)) {
                Set<AnnotatedClass> classes = scanEntrypoints(mod.getRootPaths());

                if (!classes.isEmpty()) {
                    VoidLib.LOGGER.info("Found mod '{}' with {} annotated classes", modId, classes.size());
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
                    Path developmentEntrypoint = FabricLoader.getInstance()
                            .getGameDir()
                            .getParent()
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
                        VoidLib.LOGGER.warn("Could not read 'build/main/java' directory in development environment. Searched path: {}", developmentEntrypoint);
                    }
                } catch (Exception e) {
                    VoidLib.LOGGER.warn("Could not determine development entrypoint (should be 'build/main/java')");
                }
            }
        } catch (Exception e) {
            VoidLib.LOGGER.warn("Could not read developed mod's classes. Ignore this message if you're running tests");
        }

        long endTime = System.currentTimeMillis();

        VoidLib.LOGGER.info("Scan completed in {} ms!", (endTime - startTime));

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
