package com.elenai.mobsdodgetoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elenai.mobsdodgetoo.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MobsDodgeToo.MODID, name = MobsDodgeToo.NAME, version = MobsDodgeToo.VERSION)
public class MobsDodgeToo
{
    public static final String MODID = "mobsdodgetoo";
    public static final String NAME = "Mobs Dodge Too";
    public static final String VERSION = "1.0";
    @Mod.Instance
	public static MobsDodgeToo INSTANCE;

	public static final Logger LOG = LogManager.getLogger("MobsDodgeToo");

	@SidedProxy(clientSide = "com.elenai.mobsdodgetoo.proxy.ClientProxy", serverSide = "com.elenai.elenaidodge.proxy.ServerProxy")
	private static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
