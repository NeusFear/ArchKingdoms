package com.brandonmdavis.kingdoms.modules.data.serverdata;

import com.brandonmdavis.kingdoms.modules.warps.Warp;

import java.util.ArrayList;

public class ServerData {

	private ArrayList<Warp> warps = new ArrayList<>();

	public ServerData() {

	}

	public ServerData(ArrayList<Warp> warps) {
		this.warps = warps;
	}

	public ArrayList<Warp> getWarps() {
		return warps;
	}

	public boolean addWarp(Warp warp) {
		for (Warp warpLoop : warps) {
			if (warpLoop.getName().equalsIgnoreCase(warp.getName())) {
				return false;
			}
		}
		warps.add(warp);
		return false;
	}

	public boolean removeWarp(String name) {
		for (Warp warp : warps) {
			if (warp.getName().equalsIgnoreCase(name)) {
				warps.remove(warp);
				return true;
			}
		}
		return false;
	}

	public Warp getWarp(String name) {
		for (Warp warp : warps) {
			if (warp.getName().equalsIgnoreCase(name)) {
				return warp;
			}
		}
		return null;
	}
}
