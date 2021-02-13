package com.elenai.mobsdodgetoo.event;

import com.elenai.mobsdodgetoo.MobsDodgeToo;

import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigEventListener {
	
	/*
	 * Runs whenever the Config needs to be updated on the Client Side.
	 */

	@SubscribeEvent
	public void onConfigChange(OnConfigChangedEvent event) {
		if (event.getModID().equals(MobsDodgeToo.MODID))
			ConfigManager.sync(MobsDodgeToo.MODID, Type.INSTANCE);

	}

}
