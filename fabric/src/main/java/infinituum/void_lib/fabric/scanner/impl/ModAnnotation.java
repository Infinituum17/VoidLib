package infinituum.void_lib.fabric.scanner.impl;

import infinituum.void_lib.fabric.scanner.api.Annotation;

import java.util.HashMap;
import java.util.Map;

public class ModAnnotation implements Annotation {
    private final String className;
    private final Map<String, Object> fields;

    public ModAnnotation(String className) {
        this.className = className;
        this.fields = new HashMap<>();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean is(Class<?> clazz) {
        return clazz.getName().equals(className);
    }

    @Override
    public Map<String, Object> getFields() {
        return fields;
    }

    @Override
    public boolean hasFields() {
        return !fields.isEmpty();
    }

    @Override
    public boolean containsField(String fieldName) {
        return fields.containsKey(fieldName);
    }

    @Override
    public Object getField(String fieldName) {
        return fields.get(fieldName);
    }

    public void addField(String key, Object value) {
        this.fields.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Name: ").append(className).append("\n");

        if (hasFields()) {
            result.append("Fields: \n");
            for (var fieldEntry : fields.entrySet()) {
                result.append(" - '")
                        .append(fieldEntry.getKey())
                        .append("' : '")
                        .append(fieldEntry.getValue())
                        .append("'\n");
            }
        }

        return result.toString();
    }
}
