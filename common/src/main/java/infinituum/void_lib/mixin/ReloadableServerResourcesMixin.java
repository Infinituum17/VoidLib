package infinituum.void_lib.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import infinituum.void_lib.api.events.ServerReloadEvent;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {
    @ModifyReturnValue(method = "listeners", at = @At("RETURN"))
    private List<PreparableReloadListener> listeners(List<PreparableReloadListener> original) {
        List<PreparableReloadListener> list = new ArrayList<>(original);

        ServerReloadEvent.EVENT.invoker().accept(list::add);

        return Collections.unmodifiableList(list);
    }
}
