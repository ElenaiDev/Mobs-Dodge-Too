package com.elenai.mobsdodgetoo.event;

import com.elenai.mobsdodgetoo.capability.cooldown.CooldownProvider;
import com.elenai.mobsdodgetoo.capability.cooldown.ICooldown;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickEventListener {

	@SubscribeEvent
	public void onEntityTick(TickEvent.WorldTickEvent event) {
		if(event.world.loadedEntityList != null) {
		event.world.loadedEntityList.forEach(e -> {
			if(e instanceof EntityLiving && !(e instanceof EntityPlayer)) {
			ICooldown c = e.getCapability(CooldownProvider.COOLDOWN_CAP, null);
			if(c.getCooldown() > 0) {
				c.decrease(1);
			}}
		});
	}}

}
