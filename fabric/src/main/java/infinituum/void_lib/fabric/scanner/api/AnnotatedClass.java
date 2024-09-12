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
     * Checks if the current class annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsClassAnnotation(Class<?> annotationClass);

    /**
     * Checks if this {@link AnnotatedClass} has field-level annotations
     *
     * @return {@code true} if the class has one or more field-level annotations, {@code false} otherwise
     */
    boolean hasFieldAnnotations();

    /**
     * Checks if the current field annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsFieldAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedClass} has method-level annotations
     *
     * @return {@code true} if the class has one or more method-level annotations, {@code false} otherwise
     */
    boolean hasMethodAnnotations();

    /**
     * Checks if the current method annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsMethodAnnotation(Class<?> annotationClass);
}
