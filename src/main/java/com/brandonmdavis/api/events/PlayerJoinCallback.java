package com.brandonmdavis.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerJoinCallback {
	Event<PlayerJoinCallback> EVENT = EventFactory.createArrayBacked(PlayerJoinCallback.class,
			(listeners) -> (connection, player) -> {
				for (PlayerJoinCallback listener : listeners) {
					ActionResult result = listener.onPlayerJoin(connection, player);
					if (result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult onPlayerJoin(ClientConnection connection, ServerPlayerEntity player);
}
