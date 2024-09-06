package infinituum.void_lib.fabric.scanner.impl;

import infinituum.void_lib.fabric.scanner.api.ParameterAnnotation;

public final class MethodParameterAnnotation extends ModAnnotation implements ParameterAnnotation {
    private final int parameter;

    public MethodParameterAnnotation(String name, int parameter) {
        super(name);
        this.parameter = parameter;
    }

    @Override
    public int getParameter() {
        return parameter;
    }
}
