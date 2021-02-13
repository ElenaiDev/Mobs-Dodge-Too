package com.elenai.mobsdodgetoo.network.message;

import com.elenai.mobsdodgetoo.MobsDodgeToo;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class VelocityMessageToClient implements IMessage {
	
	/*
	 * A Message to allow player.setVelocity to be run from the server
	 */

	private double motionX, motionY, motionZ;
	private int entityId;

	private boolean messageValid;

	public VelocityMessageToClient() {
		this.messageValid = false;
	}

	public VelocityMessageToClient(double motionX, double motionY, double motionZ, int entityId) {
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		this.entityId = entityId;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.motionX = buf.readDouble();
			this.motionY = buf.readDouble();
			this.motionZ = buf.readDouble();
			this.entityId = buf.readInt();

		} catch (IndexOutOfBoundsException ioe) {
			MobsDodgeToo.LOG.error("Error occured whilst networking!", ioe);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid) {
			return;
		}
		buf.writeDouble(motionX);
		buf.writeDouble(motionY);
		buf.writeDouble(motionZ);
		buf.writeInt(entityId);
	}

	public static class Handler implements IMessageHandler<VelocityMessageToClient, IMessage> {

		@Override
		public IMessage onMessage(VelocityMessageToClient message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(VelocityMessageToClient message, MessageContext ctx) {
				Minecraft.getMinecraft().world.getEntityByID(message.entityId).setVelocity(message.motionX, message.motionY, message.motionZ);
		}
	}
}
