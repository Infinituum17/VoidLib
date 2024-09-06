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
     * Checks if this {@link AnnotatedMethod} has some annotations
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasAnnotations();

    /**
     * Checks if this {@link AnnotatedMethod} has some {@link TypeAnnotation}s
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasTypeAnnotations();

    /**
     * Checks if this {@link AnnotatedMethod} has some {@link TryCatchAnnotation}s
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasTryCatchAnnotations();

    /**
     * Checks if this {@link AnnotatedMethod} has some {@link ParameterAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasParameterAnnotations();

    /**
     * Checks if this {@link AnnotatedMethod} has some {@link InstructionAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasInstructionAnnotations();

    /**
     * Checks if this {@link AnnotatedMethod} has some {@link LocalVariableAnnotation}
     *
     * @return {@code true} if the {@link AnnotatedMethod} contains one or more annotations, {@code false} otherwise
     */
    boolean hasLocalVariableAnnotations();
}
