package infinituum.void_lib.fabric.scanner.api;

import infinituum.void_lib.fabric.scanner.impl.ScannedModFile;
import net.fabricmc.loader.api.metadata.ModDependency;

import java.util.Collection;
import java.util.Set;

/**
 * Interface that represents a {@link ScannedFile}.<br/><br/>
 * Implementation: {@link ScannedModFile}
 */
public interface ScannedFile {
    /**
     * Gets the current {@link ScannedFile}'s mod identifier
     *
     * @return The current {@link ScannedFile}'s mod identifier
     */
    String getModId();

    /**
     * Gets the current {@link ScannedFile}'s dependencies
     *
     * @return A collection of {@link ModDependency}
     */
    Collection<ModDependency> getDependencies();

    /**
     * Gets the current {@link ScannedFile}'s mod display name
     *
     * @return The current {@link ScannedFile}'s mod display name
     */
    String getModDisplayName();

    /**
     * Gets the current {@link ScannedFile}'s mod annotated classes
     *
     * @return The current {@link ScannedFile}'s mod annotated classes
     */
    Set<AnnotatedClass> getAnnotatedClasses();
}
