package net.mcreator.zeloopa.procedures;

import net.minecraftforge.fml.network.NetworkHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.gui.RaceFinallizatoinGui;
import net.mcreator.zeloopa.ZeloopaModVariables;
import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;

import io.netty.buffer.Unpooled;

public class ButtonToChooseArgonianProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonToChooseArgonian!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency x for procedure ButtonToChooseArgonian!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency y for procedure ButtonToChooseArgonian!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency z for procedure ButtonToChooseArgonian!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency world for procedure ButtonToChooseArgonian!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			String _setval = (String) "Argonian";
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerRace = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 22.5;
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerStrength = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 22.5;
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerAgility = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 22.5;
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerEndurance = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 17.5;
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerIntelligence = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) 20;
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerWill = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			Entity _ent = entity;
			if (_ent instanceof ServerPlayerEntity) {
				BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
				NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("RaceFinallizatoin");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						return new RaceFinallizatoinGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
