package com.brandonmdavis.kingdoms.modules.kingdom;

public class KingdomHome {

	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

	public KingdomHome(double x, double y, double z, float pitch, float yaw) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public void update(KingdomHome home) {
		this.x = home.x;
		this.y = home.y;
		this.z = home.z;
		this.pitch = home.pitch;
		this.yaw = home.yaw;
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
