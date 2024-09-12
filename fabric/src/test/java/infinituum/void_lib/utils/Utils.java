package infinituum.void_lib.utils;

import infinituum.void_lib.fabric.scanner.ModAnnotationFileTreeVisitor;
import infinituum.void_lib.fabric.scanner.ModAnnotationScanner;
import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Set;

public class Utils {
    private static final String cwd = System.getProperty("user.dir");
    private static final Path basePackagePath = Path.of("infinituum").resolve("void_lib");
    private static final boolean debug = true;

    public static Set<AnnotatedClass> setupEnv() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method scanAnnotations = ModAnnotationScanner.class.getDeclaredMethod("scanAnnotations", ModAnnotationFileTreeVisitor.class);
        scanAnnotations.setAccessible(true);

        Path testingPath = Path.of(cwd).resolve("build/classes/java/test");
        ModAnnotationFileTreeVisitor visitor = new ModAnnotationFileTreeVisitor(
                testingPath,
                testingPath.resolve(basePackagePath)
                        .resolve("examples/annotated_classes")
                        .toString());

        Set<AnnotatedClass> annotatedClasses = (Set<AnnotatedClass>) scanAnnotations.invoke(ModAnnotationScanner.init(), visitor);

        if (debug) {
            logAnnotatedClasses(annotatedClasses);
        }

        return annotatedClasses;
    }

    public static void logAnnotatedClasses(@NotNull Set<AnnotatedClass> annotatedClasses) {
        for (var annotatedClass : annotatedClasses) {
            System.out.println(annotatedClass);
        }
    }
}
