package com.elenai.mobsdodgetoo.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elenai.mobsdodgetoo.ModConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
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
		
		
		
		List<String> mobs = new ArrayList<String>();
		Collections.addAll(mobs, ModConfig.mobs);
		mobs.forEach(m -> {
			
			String[] fields = m.split(";");
			if(fields.length < 5) {
				return;
			}
			
			String entityId = fields[0];
			double meleeChance = Double.valueOf(fields[1]);
			int meleeCooldown = Integer.valueOf(fields[2]);
			double rangedChance = Double.valueOf(fields[3]);
			int rangedCooldown = Integer.valueOf(fields[4]);
			
			if(fields.length > 5) {
				ResourceLocation soundId = new ResourceLocation(fields[5]);
			}
			if(fields.length > 6) {
				ResourceLocation particleTexture = new ResourceLocation(ModConfig.texturePath + fields[6]);
			}
			
			if(EntityList.getKey(entity).equals(new ResourceLocation(entityId)) && meleeChance <= entity.world.rand.nextDouble()) {
				event.setCanceled(true);
				double f = 0.8;
				double motionX = (double) -(MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
				double motionZ = (double) (-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f);
				
				entity.setVelocity(motionX, 0.25, motionZ);
			}
		});
		}
	}
}
