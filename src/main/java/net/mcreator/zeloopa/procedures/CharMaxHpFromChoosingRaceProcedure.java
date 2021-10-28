package net.mcreator.zeloopa.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.ZeloopaModVariables;
import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;

public class CharMaxHpFromChoosingRaceProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure CharMaxHpFromChoosingRace!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength) > 15)) {
			{
				double _setval = (double) ((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength) - 15) * 0.2);
				entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.varPlayerHealthStartBonus = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength) < 15)) {
			{
				double _setval = (double) ((15 - ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength)) * 0.2);
				entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.varPlayerHealthStartBonus = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
