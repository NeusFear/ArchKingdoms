package com.brandonmdavis.api.mixin;

import com.brandonmdavis.api.events.PlayerJoinCallback;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public abstract class MixinPlayerManager {

	@Inject(method = "onPlayerConnect", at = @At(value= "HEAD"), cancellable = true)
	public void onPlayerConnect(ClientConnection clientConnection, ServerPlayerEntity serverPlayerEntity, CallbackInfo ci) {
		ActionResult result = PlayerJoinCallback.EVENT.invoker().onPlayerJoin(clientConnection, serverPlayerEntity);
		if (result == ActionResult.FAIL) {
			ci.cancel();
		}
	}

	@Redirect(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"))
	public void sendJoinMessage(PlayerManager playerManager, Text text, MessageType messageType, UUID uUID) {

	}
}
