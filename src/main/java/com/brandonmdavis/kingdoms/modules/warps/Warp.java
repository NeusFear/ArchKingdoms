package com.brandonmdavis.kingdoms.modules.warps;

public class Warp {

	private String name;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

	public Warp(String name, double x, double y, double z, float pitch, float yaw) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public String getName() {
		return name;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}
}
