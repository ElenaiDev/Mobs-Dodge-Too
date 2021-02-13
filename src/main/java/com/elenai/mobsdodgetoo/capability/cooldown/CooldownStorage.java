package com.elenai.mobsdodgetoo.capability.cooldown;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CooldownStorage implements IStorage<ICooldown> {

	@Override
	public NBTBase writeNBT(Capability<ICooldown> capability, ICooldown instance, EnumFacing side) {
		return new NBTTagInt(instance.getCooldown());
	}

	@Override
	public void readNBT(Capability<ICooldown> capability, ICooldown instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getInt());
	}

}
