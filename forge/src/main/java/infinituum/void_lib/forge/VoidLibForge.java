package infinituum.void_lib.forge;

import infinituum.void_lib.VoidLib;
import net.minecraftforge.fml.common.Mod;

@Mod(VoidLib.MOD_ID)
public final class VoidLibForge {
    public VoidLibForge() {
        VoidLib.init();
    }
}
