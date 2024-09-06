package infinituum.void_lib.fabric.scanner;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.ScannedFile;
import infinituum.void_lib.fabric.scanner.impl.ScannedModFile;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ModAnnotationScanner {
    private static ModAnnotationScanner INSTANCE;
    private final Set<ScannedFile> result;

    private ModAnnotationScanner() {
        Collection<ModContainer> loadedMods = FabricLoader.getInstance().getAllMods();
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
        Set<ScannedFile> result = new HashSet<>();

        for (ModContainer mod : mods) {
            Set<AnnotatedClass> classes = scanEntrypoints(mod.getRootPaths());
            ScannedModFile file = new ScannedModFile(mod, classes);

            result.add(file);

            if (!mod.getContainedMods().isEmpty()) {
                scan(mod.getContainedMods());
            }
        }

        return result;
    }

    private Set<AnnotatedClass> scanEntrypoints(List<Path> entrypoints) {
        Set<AnnotatedClass> annotatedClasses = new HashSet<>();

        for (Path entrypoint : entrypoints) {
            ModAnnotationFileTreeVisitor visitor = new ModAnnotationFileTreeVisitor(entrypoint);

            annotatedClasses.addAll(scanAnnotations(visitor));
        }

        return Collections.unmodifiableSet(annotatedClasses);
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
