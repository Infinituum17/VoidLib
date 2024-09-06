package infinituum.void_lib.fabric;

import infinituum.void_lib.VoidLib;
import infinituum.void_lib.fabric.scanner.ModAnnotationScanner;
import net.fabricmc.api.ModInitializer;

public final class VoidLibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModAnnotationScanner.init();
        VoidLib.init();
    }
}
