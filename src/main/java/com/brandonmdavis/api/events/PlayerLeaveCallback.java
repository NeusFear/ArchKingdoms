package com.brandonmdavis.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerLeaveCallback {
	Event<PlayerLeaveCallback> EVENT = EventFactory.createArrayBacked(PlayerLeaveCallback.class,
			(listeners) -> (client, player) -> {
				for (PlayerLeaveCallback listener : listeners) {
					ActionResult result = listener.onPlayerLeave(client, player);
					if (result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult onPlayerLeave(ClientConnection client, ServerPlayerEntity player);
}
