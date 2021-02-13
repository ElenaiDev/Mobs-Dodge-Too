package com.elenai.mobsdodgetoo.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttackEventListener {

	@SubscribeEvent
	public void onPlayerAttack(LivingAttackEvent event) {
		if(event.getEntityLiving().getAttackingEntity() instanceof EntityPlayer && !(event.getEntityLiving() instanceof EntityPlayer)) {
		Entity entity = event.getEntityLiving();
		
		double f = 10;
		double motionX = (double) -(MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
		double motionZ = (double) (-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
		
		entity.setVelocity(motionX, 0.2, motionZ);
		}
	}
}
