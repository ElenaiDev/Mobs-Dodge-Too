package com.elenai.mobsdodgetoo.capability;

import com.elenai.mobsdodgetoo.MobsDodgeToo;
import com.elenai.mobsdodgetoo.capability.cooldown.CooldownProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation COOLDOWN_CAP = new ResourceLocation(MobsDodgeToo.MODID, "cooldown");


	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityLiving && !(event.getObject() instanceof EntityPlayer)) {
			if (!event.getObject().hasCapability(CooldownProvider.COOLDOWN_CAP, null)) {
				event.addCapability(COOLDOWN_CAP, new CooldownProvider());
			}
		}
	}
}
