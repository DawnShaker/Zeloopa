package net.mcreator.zeloopa.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.ZeloopaModVariables;
import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;

public class CharSprintOutcomeProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure CharSprintOutcome!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerLvl) * 0.15);
			entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.varPlayerSprintOutcome = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
