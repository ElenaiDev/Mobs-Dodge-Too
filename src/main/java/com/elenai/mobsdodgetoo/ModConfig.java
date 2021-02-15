package com.elenai.mobsdodgetoo;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MobsDodgeToo.MODID, name = "Mobs Dodge Too", type = Type.INSTANCE)
public class ModConfig {

	public static String[] mobs = {"minecraft:zombie;0.5;1;0.5;1", "minecraft:villager;0.5;1;0.5;1"};
	public static String[] warpMobs = {"minecraft:zombie;0.5;1;0.5;1", "minecraft:villager;0.5;1;0.5;1"};

	// Blocks the enemies won't dodge if they fear they will collide with them
	public static String[] blockBlacklist = {"minecraft:lava", "minecraft:obsidian"};
}
