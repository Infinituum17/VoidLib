package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.ModAnnotation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Interface that represents an {@link Annotation}.<br/><br/>
 * Implementation: {@link ModAnnotation}
 */
public interface Annotation {
    /**
     * Gets the current {@link Annotation}'s name
     *
     * @return The annotation name
     */
    String getName();

    /**
     * Gets the current {@link Annotation}'s fields
     *
     * @return The annotation fields
     */
    Map<String, Object> getFields();

    /**
     * Checks if the current {@link Annotation} has one or more fields
     *
     * @return {@code true} if the current {@link Annotation} has one or more fields
     */
    boolean hasFields();

    /**
     * Checks if the current {@link Annotation} contains one specific field
     *
     * @param fieldName The field name
     * @return {@code true} if the current {@link Annotation} has one or more fields
     */
    boolean containsField(String fieldName);

    /**
     * Gets the specified field's value
     *
     * @param fieldName The field name
     * @return An Object that represent any field value
     */
    @Nullable
    Object getField(String fieldName);

    /**
     * Gets the current {@link Annotation}'s class
     *
     * @return The current {@link Annotation}'s class
     */
    Class<?> get();

    /**
     * Checks if the current {@link Annotation} class is of the type passed in
     *
     * @param clazz The class type
     * @return {@code true} if the current {@link Annotation} is of the type passed in, {@code false} otherwise
     */
    boolean is(Class<?> clazz);
}
