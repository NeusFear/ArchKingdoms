package com.brandonmdavis.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerChatCallback {
	Event<PlayerChatCallback> EVENT = EventFactory.createArrayBacked(PlayerChatCallback.class,
			(listeners) -> (server, player, message) -> {
				for (PlayerChatCallback listener : listeners) {
					ActionResult result = listener.onChat(server, player, message);
					if (result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult onChat(MinecraftServer server, ServerPlayerEntity player, String message);
}
