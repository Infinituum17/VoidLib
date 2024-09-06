package infinituum.void_lib.fabric.scanner.impl;

import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.ScannedFile;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;

import java.util.Collection;
import java.util.Set;

public record ScannedModFile(ModContainer modContainer, Set<AnnotatedClass> classes) implements ScannedFile {
    @Override
    public String getModId() {
        return modContainer.getMetadata().getId();
    }

    @Override
    public Collection<ModDependency> getDependencies() {
        return modContainer.getMetadata().getDependencies();
    }

    @Override
    public String getModDisplayName() {
        return modContainer.getMetadata().getName();
    }

    @Override
    public Set<AnnotatedClass> getAnnotatedClasses() {
        return classes;
    }
}
