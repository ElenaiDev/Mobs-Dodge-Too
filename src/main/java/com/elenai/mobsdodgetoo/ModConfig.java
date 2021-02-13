package com.elenai.mobsdodgetoo;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MobsDodgeToo.MODID, name = "Mobs Dodge Too", type = Type.INSTANCE)
public class ModConfig {

	public static String[] mobs = {"minecraft:zombie;0.5;1;0.5;1", "minecraft:villager;0.5;1;0.5;1"};
	public static String texturePath = "rebirthofthemobs/assets/textures/";

}
