package infinituum.void_lib.fabric.scanner;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.asm.ModAnnotationClassVisitor;
import infinituum.void_lib.fabric.scanner.impl.AnnotatedClassImpl;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class ModAnnotationFileTreeVisitor extends SimpleFileVisitor<Path> {
    private final Path basePath;
    private final Set<AnnotatedClass> annotatedClasses;
    private final String directory;

    public ModAnnotationFileTreeVisitor(Path basePath) {
        this(basePath, null);
    }

    public ModAnnotationFileTreeVisitor(Path basePath, String directory) {
        this.basePath = basePath;
        this.directory = directory;
        this.annotatedClasses = new HashSet<>();
    }

    public Path getBasePath() {
        return basePath;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) {
        if (filePath.toString().endsWith(".class")) {
            AnnotatedClass clazz = getClassAnnotations(filePath);

            if (clazz != null) {
                annotatedClasses.add(clazz);
            }
        }

        return FileVisitResult.CONTINUE;
    }

    private AnnotatedClass getClassAnnotations(Path filePath) {
        if (directory == null || filePath.startsWith(directory)) {
            ClassReader classReader;

            try (InputStream fileStream = Files.newInputStream(filePath)) {
                classReader = new ClassReader(fileStream);
            } catch (IOException exception) {
                VoidLib.LOGGER.error("Could not instantiate a ClassReader for class '{}': {}", filePath.getFileName(), exception);
                return null;
            }

            AnnotatedClassImpl annotatedModClass = new AnnotatedClassImpl(basePath.relativize(filePath));
            ModAnnotationClassVisitor classVisitor = new ModAnnotationClassVisitor(annotatedModClass);

            classReader.accept(classVisitor, 0);

            if (!annotatedModClass.getAnnotations().isEmpty()
                    || !annotatedModClass.getAnnotatedFields().isEmpty()
                    || !annotatedModClass.getAnnotatedMethods().isEmpty()) {
                return annotatedModClass;
            }
        }

        return null;
    }

    public Set<AnnotatedClass> getAnnotatedClasses() {
        return Collections.unmodifiableSet(annotatedClasses);
    }
}
