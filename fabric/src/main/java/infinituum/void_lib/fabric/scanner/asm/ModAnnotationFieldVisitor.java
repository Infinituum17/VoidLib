package infinituum.void_lib.fabric.scanner.asm;

import infinituum.void_lib.fabric.scanner.impl.AnnotatedClassImpl;
import infinituum.void_lib.fabric.scanner.impl.AnnotatedFieldImpl;
import infinituum.void_lib.fabric.scanner.impl.ModAnnotation;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public final class ModAnnotationFieldVisitor extends FieldVisitor {
    private final AnnotatedClassImpl clazz;
    private final AnnotatedFieldImpl field;

    public ModAnnotationFieldVisitor(FieldVisitor visitor, AnnotatedClassImpl clazz, String descriptor, String name) {
        super(Opcodes.ASM9, visitor);
        this.clazz = clazz;
        this.field = new AnnotatedFieldImpl(clazz.getName(), descriptor, name);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        AnnotationVisitor nextVisitor = super.visitAnnotation(descriptor, visible);

        Type annotationType = Type.getType(descriptor);
        ModAnnotation annotation = new ModAnnotation(annotationType.getClassName());

        field.add(annotation);

        return new ModAnnotationVisitor(nextVisitor, annotation);
    }

    @Override
    public void visitEnd() {
        if (!field.getAnnotations().isEmpty()) {
            clazz.add(field);
        }

        super.visitEnd();
    }
}
