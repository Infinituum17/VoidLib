package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.MethodTypeAnnotation;
import org.objectweb.asm.TypePath;

/**
 * Interface that represents a method's {@link TypeAnnotation}.<br/><br/>
 * Implementation: {@link MethodTypeAnnotation}
 */
public interface TypeAnnotation extends Annotation {
    /**
     * Gets the current {@link TypeAnnotation}'s type-ref
     *
     * @return An integer representing the {@link TypeAnnotation}'s type-ref
     */
    int getTypeRef();

    /**
     * Gets the current {@link TypeAnnotation}'s {@link TypePath}
     *
     * @return The current {@link TypeAnnotation}'s {@link TypePath}
     */
    TypePath getTypePath();
}
