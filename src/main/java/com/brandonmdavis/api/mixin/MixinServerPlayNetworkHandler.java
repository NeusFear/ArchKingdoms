package com.brandonmdavis.api.mixin;

import com.brandonmdavis.api.events.PlayerChatCallback;
import com.brandonmdavis.api.events.PlayerLeaveCallback;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.MessageType;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler implements ServerPlayPacketListener {

	@Shadow public ServerPlayerEntity player;

	@Shadow @Final public ClientConnection connection;

	@Shadow @Final private MinecraftServer server;

	@Inject(method = "onDisconnected", at = @At("HEAD"), cancellable = true)
	public void onDisconnect(Text text, CallbackInfo ci) {
		ActionResult result = PlayerLeaveCallback.EVENT.invoker().onPlayerLeave(this.connection, this.player);
		if (result == ActionResult.FAIL) {
			ci.cancel();
		}
	}

	@Redirect(method = "onDisconnected", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"))
	public void onDisconnect(PlayerManager playerManager, Text text, MessageType messageType, UUID uUID) {

	}

	@Redirect(method = "method_31286", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"))
	public void onGameMessage(PlayerManager playerManager, Text text, MessageType messageType, UUID uUID) {
		String messageText = text.getString().split(this.player.getDisplayName().asString() + "> ", 2)[1];
		PlayerChatCallback.EVENT.invoker().onChat(this.server, this.player, messageText);
	}

}
