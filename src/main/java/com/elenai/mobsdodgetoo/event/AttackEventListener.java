package com.elenai.mobsdodgetoo.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elenai.mobsdodgetoo.MobsDodgeToo;
import com.elenai.mobsdodgetoo.ModConfig;
import com.elenai.mobsdodgetoo.capability.cooldown.CooldownProvider;
import com.elenai.mobsdodgetoo.capability.cooldown.ICooldown;
import com.jcraft.jorbis.Block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class AttackEventListener {

	@SubscribeEvent
	public void onPlayerAttack(LivingAttackEvent event) {
		if (event.getEntityLiving().getAttackingEntity() instanceof EntityPlayer) {
			Entity entity = event.getEntityLiving();

			/*
			 * Restrictions (optional) list of modname:potioneffects that prevent all mobs
			 * from dodging while they are affected by this potion effect
			 */

			List<String> mobs = new ArrayList<String>();
			Collections.addAll(mobs, ModConfig.mobs);
			mobs.forEach(m -> {

				String[] fields = m.split(";");
				if (fields.length < 5) {
					return;
				}

				String entityId = fields[0];
				double meleeChance = Double.valueOf(fields[1]);
				int meleeCooldown = Integer.valueOf(fields[2]);
				double rangedChance = Double.valueOf(fields[3]);
				int rangedCooldown = Integer.valueOf(fields[4]);

				if (fields.length > 5) {
					ResourceLocation soundId = new ResourceLocation(fields[5]);
				}
				if (fields.length > 6) {
					ResourceLocation particleTexture = new ResourceLocation(MobsDodgeToo.MODID, fields[6]);
				}

				ICooldown c = entity.getCapability(CooldownProvider.COOLDOWN_CAP, null);

				if (EntityList.getKey(entity).equals(new ResourceLocation(entityId))
						&& meleeChance >= entity.world.rand.nextDouble() && c.getCooldown() <= 0) {

					double f = 0.6;

					
					 if (entity.world.rand.nextDouble() <= 0.33) { dodgeLeft(entity, f, event, meleeCooldown, 1); } else if
					 (entity.world.rand.nextDouble() >= 0.66) { dodgeRight(entity, f, event, meleeCooldown, 1); } else {
					 dodgeBack(entity, f, event, meleeCooldown, 1); }
					 
				}
			});
		}

	}

	
	/*
	 * NORTH: Forwards = nZ, Backwards = pZ, Left = nX, Right = pX
	 * EAST: Forwards = pX, Backwards = nX, Left = nZ, Right = pZ
	 * SOUTH: Forwards = pZ, Backwards = nZ, Left = pX, Right = nX
	 * WEST: Forwards = nX, Backwards = pX, Left = pZ, Right = nZ
	 */
	
	public void dodgeRight(Entity entity, double f, LivingAttackEvent event, int cooldown, int attempt) {

		List<String> blocks = new ArrayList<String>();
		Collections.addAll(blocks, ModConfig.blockBlacklist);
		boolean canDodge = true;
		for (String b : blocks) {
			for (int i = 0; i < 5; ++i) {
				for (int h = -1; h < entity.height; ++h) {
					switch (entity.getHorizontalFacing()) {
					case NORTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case EAST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case SOUTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(-i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case WEST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, -i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					default:
						canDodge = false;
						break;
					}
				}
			}
		}
		if (canDodge) {
			ICooldown c = entity.getCapability(CooldownProvider.COOLDOWN_CAP, null);
			event.setCanceled(true);
			c.set(cooldown);

			entity.setVelocity(
					(double) -(MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
							* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f),
					0.25, (double) (-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
							* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f));
		} else {
			if(attempt <= 3) {
				dodgeLeft(entity, f, event, cooldown, attempt + 1);
			}
		}

	}

	public void dodgeLeft(Entity entity, double f, LivingAttackEvent event, int cooldown, int attempt) {
		
		List<String> blocks = new ArrayList<String>();
		Collections.addAll(blocks, ModConfig.blockBlacklist);
		boolean canDodge = true;
		for (String b : blocks) {
			for (int i = 0; i < 5; ++i) {
				for (int h = -1; h < entity.height; ++h) {
					switch (entity.getHorizontalFacing()) {
					case NORTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(-i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case EAST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, -i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case SOUTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case WEST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					default:
						canDodge = false;
						break;
					}
				}
			}
		}
		
		if (canDodge) {
			ICooldown c = entity.getCapability(CooldownProvider.COOLDOWN_CAP, null);
			event.setCanceled(true);
			c.set(cooldown);
		entity.setVelocity(
				(double) (MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f),
				0.25, (double) -(-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f));
		}  else {
			if(attempt <= 3) {
				dodgeBack(entity, f, event, cooldown, attempt + 1);
			}
	}
	}

	public void dodgeBack(Entity entity, double f, LivingAttackEvent event, int cooldown, int attempt) {

		List<String> blocks = new ArrayList<String>();
		Collections.addAll(blocks, ModConfig.blockBlacklist);
		boolean canDodge = true;
		for (String b : blocks) {
			for (int i = 0; i < 5; ++i) {
				for (int h = -1; h < entity.height; ++h) {
					switch (entity.getHorizontalFacing()) {
					case NORTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case EAST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(-i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case SOUTH:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(0, h, -i))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					case WEST:
						if (entity.getEntityWorld().getBlockState(entity.getPosition().add(i, h, 0))
								.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b))) {
							canDodge = false;
						}
						break;
					default:
						canDodge = false;
						break;
					}
				}
			}
		}
		
		if (canDodge) {
			ICooldown c = entity.getCapability(CooldownProvider.COOLDOWN_CAP, null);
			event.setCanceled(true);
			c.set(cooldown);
			entity.setVelocity(
					(double) -(-MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI)
							* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f),
					0.25, (double) -(MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI)
							* MathHelper.cos(1 / 180.0F * (float) Math.PI) * f));
		}  else {
			if(attempt <= 3) {
				dodgeRight(entity, f, event, cooldown, attempt + 1);
			}
	}
		
	}

}
