package com.elenai.mobsdodgetoo.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {

	private static int nextId = 0;
	public static SimpleNetworkWrapper instance;

	public static void registerMessages(String channelName) {
		instance = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		
		// Server Side Logic
		//instance.registerMessage(ExampleMessage.Handler.class, ExampleMessage.class, nextId++, Side.SERVER);


		
		// Client Side Logic
		//instance.registerMessage(ExampleMessage.Handler.class, ExampleMessage.class, nextId++, Side.CLIENT);




	}	
}
