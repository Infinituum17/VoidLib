package infinituum.void_lib.fabric.scanner.api;

import java.util.List;

/**
 * Interface that represents a generic {@link AnnotatedElement}
 */
public interface AnnotatedElement {
    /**
     * Gets the annotations of the current {@link AnnotatedElement}
     *
     * @return The list of annotations
     */
    List<Annotation> getAnnotations();

    /**
     * Gets the name of the current {@link AnnotatedElement}
     *
     * @return The name of the current {@link AnnotatedElement}
     */
    String getName();

    /**
     * Checks if the current {@link AnnotatedElement} contains a specific annotation class
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean contains(Class<?> annotationClass);
}
