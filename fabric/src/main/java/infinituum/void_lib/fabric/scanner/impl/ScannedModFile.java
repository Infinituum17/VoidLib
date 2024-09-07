package infinituum.void_lib.fabric.scanner.impl;

import infinituum.void_lib.fabric.scanner.api.AnnotatedClass;
import infinituum.void_lib.fabric.scanner.api.ScannedFile;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public record ScannedModFile(ModContainer modContainer, Set<AnnotatedClass> classes) implements ScannedFile {
    @Override
    public String getModId() {
        if (modContainer.getMetadata() != null) {
            return modContainer.getMetadata().getId();
        }

        return "";
    }

    @Override
    public Collection<ModDependency> getDependencies() {
        if (modContainer.getMetadata() != null) {
            return modContainer.getMetadata().getDependencies();
        }

        return List.of();
    }

    @Override
    public String getModDisplayName() {
        if (modContainer.getMetadata() != null) {
            return modContainer.getMetadata().getName();
        }

        return "";
    }

    @Override
    public Set<AnnotatedClass> getAnnotatedClasses() {
        return classes;
    }
}
