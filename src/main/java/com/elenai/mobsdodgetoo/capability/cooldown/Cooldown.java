package com.elenai.mobsdodgetoo.capability.cooldown;

public class Cooldown implements ICooldown {

	private int cooldown = 0;

	@Override
	public void increase(int cooldown) {
		this.cooldown += cooldown;
	}

	@Override
	public void decrease(int cooldown) {
		this.cooldown -= cooldown;
	}

	@Override
	public void set(int cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public int getCooldown() {
		return this.cooldown;
	}

}
