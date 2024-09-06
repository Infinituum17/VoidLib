package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.MethodInstructionAnnotation;
import org.objectweb.asm.TypePath;

/**
 * Interface representing a method's {@link InstructionAnnotation}.<br/><br/>
 * Implementation: {@link MethodInstructionAnnotation}
 */
public interface InstructionAnnotation extends Annotation {
    /**
     * Gets the current {@link InstructionAnnotation}'s type-ref
     *
     * @return An integer representing the {@link InstructionAnnotation}'s type-ref
     */
    int getTypeRef();

    /**
     * Gets the current {@link InstructionAnnotation}'s {@link TypePath}.
     *
     * @return The current {@link InstructionAnnotation}'s {@link TypePath}
     */
    TypePath getTypePath();
}
