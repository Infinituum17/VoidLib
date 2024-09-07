package infinituum.void_lib.fabric.utils;

import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.ModOrigin;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DevModContainer implements ModContainer {
    private final List<Path> rootPaths;

    public DevModContainer(List<Path> rootPaths) {
        this.rootPaths = rootPaths;
    }

    @Override
    public ModMetadata getMetadata() {
        return null;
    }

    @Override
    public List<Path> getRootPaths() {
        return this.rootPaths;
    }

    @Override
    public ModOrigin getOrigin() {
        return null;
    }

    @Override
    public Optional<ModContainer> getContainingMod() {
        return Optional.empty();
    }

    @Override
    public Collection<ModContainer> getContainedMods() {
        return List.of();
    }

    @Override
    @Deprecated
    public Path getRootPath() {
        return null;
    }

    @Override
    @Deprecated
    public Path getPath(String file) {
        return null;
    }
}
