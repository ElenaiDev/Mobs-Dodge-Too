package com.elenai.mobsdodgetoo.proxy;

import com.elenai.mobsdodgetoo.MobsDodgeToo;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = MobsDodgeToo.MODID, value = Side.SERVER)
public class ServerProxy extends CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
