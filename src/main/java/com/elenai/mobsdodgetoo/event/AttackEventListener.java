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
		
		/*	TODO:
		 * 	Make Dodge Direction Random (Left, Right or Backwards)
		 * 	Intelligently Check if dodging would collide with lava, or fall of a ledge. (Scan Block position, may need to be Client-Side)
		 * 	Add Config Checker to see if the mob can dodge and their chance, refer to Armor Weights in ED2 for reference.
		 * 	Add Config for below fields. We can use a String array to determine each entity, separate each value in each String with a semicolon
		 * 	and then check the length (or check value[x] != null) in order to see if the optional values are added)	
		 * 
		 * Fields:
		 *		Entity (modname:entity)
		 *		Chance to Melee Dodge (Decimal value between 0 and 1)
		 *		Melee Dodge Cooldown in seconds
		 *		Chance to Ranged Dodge (Decimal value between 0 and 1)
		 *		Ranged Dodge Cooldown in seconds
		 *		Sound (Optional) Specify a path to a sound event to play for this mob’s dodge.
		 *		Particle (optional) Uses a defined particle effect to use for this mob’s dodges, pulled from (rebirthofthemobs/assets/textures/)
         *
		 *		Restrictions (optional) list of modname:potioneffects that prevent all mobs from dodging while they are affected by this potion effect
		 */
		double f = 10;
		double motionX = (double) -(MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
		double motionZ = (double) (-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
		
		entity.setVelocity(motionX, 0.2, motionZ);
		}
	}
}
