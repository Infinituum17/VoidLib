package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.AnnotatedFieldImpl;

/**
 * Interface that represents an {@link AnnotatedField}.<br/><br/>
 * Implementation: {@link AnnotatedFieldImpl}
 */
public interface AnnotatedField extends AnnotatedElement {
    /**
     * Gets the name of the class that contains this {@link AnnotatedField}
     *
     * @return The class name
     */
    String getClassName();

    /**
     * Gets this {@link AnnotatedField} descriptor
     *
     * @return The descriptor
     */
    String getDescriptor();

    /**
     * Checks if this {@link AnnotatedField} has some annotations
     *
     * @return {@code true} if the {@link AnnotatedField} contains one or more annotations, {@code false} otherwise
     */
    boolean hasAnnotations();
}
