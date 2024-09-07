package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.AnnotatedMethodImpl;

import java.util.List;

/**
 * Interface that represents an {@link AnnotatedMethod}.<br/><br/>
 * Implementation: {@link AnnotatedMethodImpl}
 */
public interface AnnotatedMethod extends AnnotatedElement {
    /**
     * Gets the name of the class that contains this {@link AnnotatedMethod}
     *
     * @return The class name
     */
    String getClassName();

    /**
     * Gets this {@link AnnotatedMethod} descriptor
     *
     * @return The descriptor
     */
    String getDescriptor();

    /**
     * Gets the current method {@link TypeAnnotation}s
     *
     * @return The list of {@link TypeAnnotation}
     */
    List<TypeAnnotation> getTypeAnnotations();

    /**
     * Gets the current method {@link TryCatchAnnotation}s
     *
     * @return The list of {@link TryCatchAnnotation}
     */
    List<TryCatchAnnotation> getTryCatchAnnotations();

    /**
     * Gets the current method {@link ParameterAnnotation}s
     *
     * @return The list of {@link ParameterAnnotation}
     */
    List<ParameterAnnotation> getParameterAnnotations();

    /**
     * Gets the current method {@link InstructionAnnotation}s
     *
     * @return The list of {@link InstructionAnnotation}
     */
    List<InstructionAnnotation> getInstructionAnnotations();

    /**
     * Gets the current method {@link LocalVariableAnnotation}s
     *
     * @return The list of {@link LocalVariableAnnotation}
     */
    List<LocalVariableAnnotation> getLocalVariableAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some annotations
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some {@link TypeAnnotation}s
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasTypeAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some {@link TryCatchAnnotation}s
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasTryCatchAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some {@link ParameterAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasParameterAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some {@link InstructionAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasInstructionAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod} has some {@link LocalVariableAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasLocalVariableAnnotations();

    /**
     * Checks if the current {@link AnnotatedMethod}'s method annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedMethod}'s type annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsTypeAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedMethod}'s parameter annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsParameterAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedMethod}'s instruction annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsInstructionAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedMethod}'s try-catch annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsTryCatchAnnotation(Class<?> annotationClass);

    /**
     * Checks if the current {@link AnnotatedMethod}'s local-variable annotations contain one annotation of the type passed in
     *
     * @param annotationClass The annotation class
     * @return {@code true} if it contains at least one annotation of the type passed in, {@code false} otherwise
     */
    boolean containsLocalVariableAnnotation(Class<?> annotationClass);
}
