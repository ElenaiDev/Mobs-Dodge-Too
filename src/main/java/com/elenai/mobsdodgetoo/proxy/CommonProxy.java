package com.elenai.mobsdodgetoo.proxy;

import com.elenai.mobsdodgetoo.MobsDodgeToo;
import com.elenai.mobsdodgetoo.event.AttackEventListener;
import com.elenai.mobsdodgetoo.network.PacketHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber(modid = MobsDodgeToo.MODID)
public class CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.registerMessages(MobsDodgeToo.MODID);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new AttackEventListener());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
