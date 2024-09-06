package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.AnnotatedClassImpl;

import java.util.Set;

/**
 * Interface that represents an {@link AnnotatedClass}.<br/>
 * Note: Annotated Classes don't always contain class-level annotations, but can contain field-level or method-level annotations.<br/><br/>
 * Implementation: {@link AnnotatedClassImpl}
 */
public interface AnnotatedClass extends AnnotatedElement {
    /**
     * Gets the annotated class fields
     *
     * @return The annotated class fields
     */
    Set<AnnotatedField> getAnnotatedFields();

    /**
     * Gets the annotated class methods
     *
     * @return The annotated class methods
     */
    Set<AnnotatedMethod> getAnnotatedMethods();

    /**
     * Gets the class corresponding to the {@link AnnotatedClass}
     *
     * @return The class corresponding to the {@link AnnotatedClass}
     */
    Class<?> get();

    /**
     * Utility method to check if the current {@link AnnotatedClass} is of the type passed in
     *
     * @param clazz The class type to check
     * @return {@code true} if the two classes are of the same type, {@code false} otherwise
     */
    boolean is(Class<?> clazz);

    /**
     * Checks if this {@link AnnotatedClass} has class-level annotations
     *
     * @return {@code true} if the class has one or more class-level annotations, {@code false} otherwise
     */
    boolean hasClassAnnotations();

    /**
     * Checks if this {@link AnnotatedClass} has field-level annotations
     *
     * @return {@code true} if the class has one or more field-level annotations, {@code false} otherwise
     */
    boolean hasFieldAnnotations();

    /**
     * Checks if this {@link AnnotatedClass} has method-level annotations
     *
     * @return {@code true} if the class has one or more method-level annotations, {@code false} otherwise
     */
    boolean hasMethodAnnotations();
}
