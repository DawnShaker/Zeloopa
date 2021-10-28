package net.mcreator.zeloopa.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;
import java.util.HashMap;

public class ButtonFinishProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonFinish!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			CharMaxHpFromChoosingRaceProcedure.executeProcedure($_dependencies);
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
