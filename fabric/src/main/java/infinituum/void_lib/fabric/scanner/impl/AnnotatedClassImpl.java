package infinituum.void_lib.fabric.scanner.impl;

import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.AnnotatedField;
import infinituum.void_lib.fabric.scanner.api.AnnotatedMethod;
import infinituum.void_lib.fabric.scanner.api.Annotation;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AnnotatedClassImpl implements AnnotatedClass {
    private final List<Annotation> classAnnotations;
    private final Set<AnnotatedField> annotatedFields;
    private final Set<AnnotatedMethod> annotatedMethods;
    private final String className;

    public AnnotatedClassImpl(Path filePath) {
        this.classAnnotations = new ArrayList<>();
        this.annotatedFields = new HashSet<>();
        this.annotatedMethods = new HashSet<>();
        this.className = filePathToJavaPath(filePath);
    }

    private String filePathToJavaPath(Path path) {
        FileSystem fileSystem = path.getFileSystem();

        return path.toString().replace(".class", "").replace(fileSystem.getSeparator(), ".");
    }

    public void add(Annotation annotation) {
        this.classAnnotations.add(annotation);
    }

    public void add(AnnotatedField field) {
        this.annotatedFields.add(field);
    }

    public void add(AnnotatedMethod method) {
        this.annotatedMethods.add(method);
    }

    @Override
    public List<Annotation> getAnnotations() {
        return classAnnotations;
    }

    @Override
    public String getName() {
        return className;
    }

    @Override
    public boolean contains(Class<?> annotationClass) {
        return containsClassAnnotation(annotationClass)
                || containsFieldAnnotation(annotationClass)
                || containsMethodAnnotation(annotationClass);
    }

    @Override
    public Set<AnnotatedField> getAnnotatedFields() {
        return annotatedFields;
    }

    @Override
    public Set<AnnotatedMethod> getAnnotatedMethods() {
        return annotatedMethods;
    }

    @Override
    public boolean is(Class<?> clazz) {
        return clazz.getName().equals(className);
    }

    @Override
    public boolean hasClassAnnotations() {
        return !this.classAnnotations.isEmpty();
    }

    @Override
    public boolean containsClassAnnotation(Class<?> annotationClass) {
        if (!hasClassAnnotations()) {
            return false;
        }

        return this.classAnnotations
                .stream()
                .anyMatch(annotation -> annotation.is(annotationClass));
    }

    @Override
    public boolean hasFieldAnnotations() {
        return !this.annotatedFields.isEmpty();
    }

    @Override
    public boolean containsFieldAnnotation(Class<?> annotationClass) {
        if (!hasFieldAnnotations()) {
            return false;
        }

        return this.annotatedFields
                .stream()
                .anyMatch(annotatedField -> annotatedField.contains(annotationClass));
    }

    @Override
    public boolean hasMethodAnnotations() {
        return !this.annotatedMethods.isEmpty();
    }

    @Override
    public boolean containsMethodAnnotation(Class<?> annotationClass) {
        if (!hasMethodAnnotations()) {
            return false;
        }

        return this.annotatedMethods
                .stream()
                .anyMatch(annotatedMethod -> annotatedMethod.contains(annotationClass));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Name: '").append(className).append("'\n");

        if (hasClassAnnotations()) {
            result.append("Annotations: \n");
            for (var annotation : classAnnotations) {
                result.append(" - ").append(annotation.toString().replace("\n", "\n   ")).append("\n");
            }
        }

        if (hasFieldAnnotations()) {
            result.append("Fields: \n");
            for (var field : annotatedFields) {
                result.append(" - ").append(field.toString().replace("\n", "\n   ")).append("\n");
            }
        }

        if (hasMethodAnnotations()) {
            result.append("Methods: \n");
            for (var method : annotatedMethods) {
                result.append(" - ").append(method.toString().replace("\n", "\n   ")).append("\n");
            }
        }

        return result.toString();
    }
}
