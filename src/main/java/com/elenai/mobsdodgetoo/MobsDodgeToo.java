package com.elenai.elenaidodge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elenai.elenaidodge.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ElenaiDodge.MODID, name = ElenaiDodge.NAME, version = ElenaiDodge.VERSION)
public class ElenaiDodge
{
    public static final String MODID = "mobsdodgetoo";
    public static final String NAME = "Mobs Dodge Too";
    public static final String VERSION = "1.0";
    @Mod.Instance
	public static ElenaiDodge INSTANCE;

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
