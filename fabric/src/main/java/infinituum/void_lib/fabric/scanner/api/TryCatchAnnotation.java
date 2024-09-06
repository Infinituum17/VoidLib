package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.MethodTryCatchAnnotation;
import org.objectweb.asm.TypePath;

/**
 * Interface that represents a method's {@link TryCatchAnnotation}.<br/><br/>
 * Implementation: {@link MethodTryCatchAnnotation}
 */
public interface TryCatchAnnotation extends Annotation {
    /**
     * Gets the current {@link TryCatchAnnotation}'s type-ref
     *
     * @return An integer representing the {@link TryCatchAnnotation}'s type-ref
     */
    int getTypeRef();

    /**
     * Gets the current {@link TryCatchAnnotation}'s {@link TypePath}.
     *
     * @return The current {@link TryCatchAnnotation}'s {@link TypePath}
     */
    TypePath getTypePath();
}
