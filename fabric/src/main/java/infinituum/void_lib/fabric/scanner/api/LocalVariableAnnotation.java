package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.MethodLocalVariableAnnotation;
import org.objectweb.asm.Label;
import org.objectweb.asm.TypePath;

/**
 * Interface representing a method's {@link LocalVariableAnnotation}.<br/><br/>
 * Implementation: {@link MethodLocalVariableAnnotation}
 */
public interface LocalVariableAnnotation extends Annotation {
    /**
     * Gets the current {@link LocalVariableAnnotation}'s type-ref
     *
     * @return An integer representing the {@link LocalVariableAnnotation}'s type-ref
     */
    int typeRef();

    /**
     * Gets the current {@link LocalVariableAnnotation}'s {@link TypePath}
     *
     * @return The current {@link LocalVariableAnnotation}'s {@link TypePath}
     */
    TypePath typePath();

    /**
     * Gets the current {@link LocalVariableAnnotation}'s start {@link Label}
     *
     * @return The current {@link LocalVariableAnnotation}'s start {@link Label}
     */
    Label[] start();

    /**
     * Gets the current {@link LocalVariableAnnotation}'s end {@link Label}
     *
     * @return The current {@link LocalVariableAnnotation}'s end {@link Label}
     */
    Label[] end();

    /**
     * Gets the current {@link LocalVariableAnnotation}'s indexes
     *
     * @return The current {@link LocalVariableAnnotation}'s indexes
     */
    int[] index();
}
