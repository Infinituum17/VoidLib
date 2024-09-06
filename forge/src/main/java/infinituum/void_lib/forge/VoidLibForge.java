package infinituum.void_lib.forge;

import dev.architectury.platform.forge.EventBuses;
import infinituum.void_lib.VoidLib;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VoidLib.MOD_ID)
public final class VoidLibForge {
    public VoidLibForge() {
        EventBuses.registerModEventBus(VoidLib.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        VoidLib.init();
    }
}
