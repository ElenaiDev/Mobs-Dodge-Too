package com.elenai.mobsdodgetoo.capability.cooldown;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CooldownProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(ICooldown.class)
	public static final Capability<ICooldown> COOLDOWN_CAP = null;
	
	private ICooldown instance = COOLDOWN_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == COOLDOWN_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == COOLDOWN_CAP ? COOLDOWN_CAP.<T> cast (this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return COOLDOWN_CAP.getStorage().writeNBT(COOLDOWN_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		COOLDOWN_CAP.getStorage().readNBT(COOLDOWN_CAP, this.instance, null, nbt);
	}

}
