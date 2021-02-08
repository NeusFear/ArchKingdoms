package com.brandonmdavis.kingdoms.modules.warps.commands;

public class WarpCommandManager {

	public static void register() {
		SetWarpCommand.register();
		DeleteWarpCommand.register();
		WarpCommand.register();
		WarpsCommand.register();
	}
}
