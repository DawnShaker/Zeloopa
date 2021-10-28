package net.mcreator.zeloopa.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;

public class ButtonToCloseCharGuiListProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonToCloseCharGuiList!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
