package infinituum.void_lib.api.events;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.function.Consumer;

public interface ClientReloadEvent {
    /**
     * See {@link #registerReloadableResource(PreparableReloadListener)}
     */
    Event<Consumer<ClientReloadEvent>> EVENT = EventFactory.createConsumerLoop();

    /**
     * Invoked on the client when registering reloadable resources.
     *
     * @param listener A {@link PreparableReloadListener} that will be called to reload a client resource.
     */
    void registerReloadableResource(PreparableReloadListener listener);
}
