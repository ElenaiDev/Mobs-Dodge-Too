package com.elenai.mobsdodgetoo.proxy;

import com.elenai.mobsdodgetoo.MobsDodgeToo;
import com.elenai.mobsdodgetoo.capability.CapabilityHandler;
import com.elenai.mobsdodgetoo.capability.cooldown.Cooldown;
import com.elenai.mobsdodgetoo.capability.cooldown.CooldownStorage;
import com.elenai.mobsdodgetoo.capability.cooldown.ICooldown;
import com.elenai.mobsdodgetoo.event.AttackEventListener;
import com.elenai.mobsdodgetoo.event.ConfigEventListener;
import com.elenai.mobsdodgetoo.event.TickEventListener;
import com.elenai.mobsdodgetoo.network.PacketHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
		CapabilityManager.INSTANCE.register(ICooldown.class, new CooldownStorage(), Cooldown::new);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ConfigEventListener());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new AttackEventListener());
		MinecraftForge.EVENT_BUS.register(new TickEventListener());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
