package infinituum.void_lib.fabric;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.ModAnnotationScanner;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public final class VoidLibFabric implements PreLaunchEntrypoint, ModInitializer {
    @Override
    public void onInitialize() {
        VoidLib.init();
    }

    @Override
    public void onPreLaunch() {
        ModAnnotationScanner.init();
    }
}
