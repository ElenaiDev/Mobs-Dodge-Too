package com.elenai.mobsdodgetoo.capability.cooldown;

public interface ICooldown {

	public void increase(int cooldown);
	public void decrease(int cooldown);
	public void set(int cooldown);

	public int getCooldown();
}
