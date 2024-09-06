package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.MethodParameterAnnotation;

/**
 * Interface that represents a method's {@link ParameterAnnotation}.<br/><br/>
 * Implementation: {@link MethodParameterAnnotation}
 */
public interface ParameterAnnotation extends Annotation {
    /**
     * Gets the current {@link ParameterAnnotation}'s parameter index
     *
     * @return The current {@link ParameterAnnotation}'s parameter index
     */
    int getParameter();
}
