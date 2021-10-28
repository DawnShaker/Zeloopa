package net.mcreator.zeloopa.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.mcreator.zeloopa.ZeloopaModVariables;
import net.mcreator.zeloopa.ZeloopaMod;

import java.util.Map;
import java.util.HashMap;

public class CharStaminaIssuesProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ZeloopaMod.LOGGER.warn("Failed to load dependency entity for procedure CharStaminaIssues!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength) <= 0)) {
			entity.setSprinting((false));
		}
		if (((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaCurr) > 0) && (entity.isSprinting()))) {
			{
				double _setval = (double) (((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaCurr)
						- ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerSprintOutcome));
				entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.varPlayerStaminaCurr = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			if ((((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaCurr) <= ((entity
							.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaMax))) {
				{
					double _setval = (double) (((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaCurr)
							+ ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStaminaRegen));
					entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.varPlayerStaminaCurr = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
