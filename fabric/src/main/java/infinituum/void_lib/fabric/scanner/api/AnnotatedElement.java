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
}
