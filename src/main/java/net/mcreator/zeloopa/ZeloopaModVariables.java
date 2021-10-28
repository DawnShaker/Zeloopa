package net.mcreator.zeloopa;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class ZeloopaModVariables {
	public ZeloopaModVariables(ZeloopaModElements elements) {
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("zeloopa", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("varPlayerStrength", instance.varPlayerStrength);
			nbt.putString("varPlayerRace", instance.varPlayerRace);
			nbt.putDouble("varPlayerAgility", instance.varPlayerAgility);
			nbt.putDouble("varPlayerEndurance", instance.varPlayerEndurance);
			nbt.putDouble("varPlayerIntelligence", instance.varPlayerIntelligence);
			nbt.putDouble("varPlayerWill", instance.varPlayerWill);
			nbt.putDouble("varPlayerStaminaMax", instance.varPlayerStaminaMax);
			nbt.putDouble("varPlayerStaminaCurr", instance.varPlayerStaminaCurr);
			nbt.putDouble("varPlayerManaMax", instance.varPlayerManaMax);
			nbt.putDouble("varPlayerManaCurr", instance.varPlayerManaCurr);
			nbt.putDouble("varPlayerHealthMax", instance.varPlayerHealthMax);
			nbt.putDouble("varPlayerHealthStartBonus", instance.varPlayerHealthStartBonus);
			nbt.putDouble("varPlayerHealthBase", instance.varPlayerHealthBase);
			nbt.putDouble("varPlayerHealthEffect", instance.varPlayerHealthEffect);
			nbt.putDouble("varPlayerHealthLvlUpBonus", instance.varPlayerHealthLvlUpBonus);
			nbt.putDouble("varPlayerHealthCurr", instance.varPlayerHealthCurr);
			nbt.putDouble("varPlayerLvl", instance.varPlayerLvl);
			nbt.putDouble("varPlayerStaminaRegen", instance.varPlayerStaminaRegen);
			nbt.putDouble("varPlayerManaRegen", instance.varPlayerManaRegen);
			nbt.putDouble("varPlayerHealthRegen", instance.varPlayerHealthRegen);
			nbt.putDouble("varPlayerSprintOutcome", instance.varPlayerSprintOutcome);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.varPlayerStrength = nbt.getDouble("varPlayerStrength");
			instance.varPlayerRace = nbt.getString("varPlayerRace");
			instance.varPlayerAgility = nbt.getDouble("varPlayerAgility");
			instance.varPlayerEndurance = nbt.getDouble("varPlayerEndurance");
			instance.varPlayerIntelligence = nbt.getDouble("varPlayerIntelligence");
			instance.varPlayerWill = nbt.getDouble("varPlayerWill");
			instance.varPlayerStaminaMax = nbt.getDouble("varPlayerStaminaMax");
			instance.varPlayerStaminaCurr = nbt.getDouble("varPlayerStaminaCurr");
			instance.varPlayerManaMax = nbt.getDouble("varPlayerManaMax");
			instance.varPlayerManaCurr = nbt.getDouble("varPlayerManaCurr");
			instance.varPlayerHealthMax = nbt.getDouble("varPlayerHealthMax");
			instance.varPlayerHealthStartBonus = nbt.getDouble("varPlayerHealthStartBonus");
			instance.varPlayerHealthBase = nbt.getDouble("varPlayerHealthBase");
			instance.varPlayerHealthEffect = nbt.getDouble("varPlayerHealthEffect");
			instance.varPlayerHealthLvlUpBonus = nbt.getDouble("varPlayerHealthLvlUpBonus");
			instance.varPlayerHealthCurr = nbt.getDouble("varPlayerHealthCurr");
			instance.varPlayerLvl = nbt.getDouble("varPlayerLvl");
			instance.varPlayerStaminaRegen = nbt.getDouble("varPlayerStaminaRegen");
			instance.varPlayerManaRegen = nbt.getDouble("varPlayerManaRegen");
			instance.varPlayerHealthRegen = nbt.getDouble("varPlayerHealthRegen");
			instance.varPlayerSprintOutcome = nbt.getDouble("varPlayerSprintOutcome");
		}
	}

	public static class PlayerVariables {
		public double varPlayerStrength = 15.0;
		public String varPlayerRace = "";
		public double varPlayerAgility = 15.0;
		public double varPlayerEndurance = 15.0;
		public double varPlayerIntelligence = 15.0;
		public double varPlayerWill = 15.0;
		public double varPlayerStaminaMax = 0.0;
		public double varPlayerStaminaCurr = 0;
		public double varPlayerManaMax = 0;
		public double varPlayerManaCurr = 0;
		public double varPlayerHealthMax = 0;
		public double varPlayerHealthStartBonus = 0;
		public double varPlayerHealthBase = 20.0;
		public double varPlayerHealthEffect = 0;
		public double varPlayerHealthLvlUpBonus = 0;
		public double varPlayerHealthCurr = 0;
		public double varPlayerLvl = 1.0;
		public double varPlayerStaminaRegen = 0;
		public double varPlayerManaRegen = 0;
		public double varPlayerHealthRegen = 0;
		public double varPlayerSprintOutcome = 0;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				ZeloopaMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.varPlayerStrength = original.varPlayerStrength;
		clone.varPlayerRace = original.varPlayerRace;
		clone.varPlayerAgility = original.varPlayerAgility;
		clone.varPlayerEndurance = original.varPlayerEndurance;
		clone.varPlayerIntelligence = original.varPlayerIntelligence;
		clone.varPlayerWill = original.varPlayerWill;
		clone.varPlayerStaminaMax = original.varPlayerStaminaMax;
		clone.varPlayerStaminaCurr = original.varPlayerStaminaCurr;
		clone.varPlayerManaMax = original.varPlayerManaMax;
		clone.varPlayerManaCurr = original.varPlayerManaCurr;
		clone.varPlayerHealthMax = original.varPlayerHealthMax;
		clone.varPlayerHealthStartBonus = original.varPlayerHealthStartBonus;
		clone.varPlayerHealthBase = original.varPlayerHealthBase;
		clone.varPlayerHealthEffect = original.varPlayerHealthEffect;
		clone.varPlayerHealthLvlUpBonus = original.varPlayerHealthLvlUpBonus;
		clone.varPlayerHealthCurr = original.varPlayerHealthCurr;
		clone.varPlayerLvl = original.varPlayerLvl;
		clone.varPlayerStaminaRegen = original.varPlayerStaminaRegen;
		clone.varPlayerManaRegen = original.varPlayerManaRegen;
		clone.varPlayerHealthRegen = original.varPlayerHealthRegen;
		clone.varPlayerSprintOutcome = original.varPlayerSprintOutcome;
		if (!event.isWasDeath()) {
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.varPlayerStrength = message.data.varPlayerStrength;
					variables.varPlayerRace = message.data.varPlayerRace;
					variables.varPlayerAgility = message.data.varPlayerAgility;
					variables.varPlayerEndurance = message.data.varPlayerEndurance;
					variables.varPlayerIntelligence = message.data.varPlayerIntelligence;
					variables.varPlayerWill = message.data.varPlayerWill;
					variables.varPlayerStaminaMax = message.data.varPlayerStaminaMax;
					variables.varPlayerStaminaCurr = message.data.varPlayerStaminaCurr;
					variables.varPlayerManaMax = message.data.varPlayerManaMax;
					variables.varPlayerManaCurr = message.data.varPlayerManaCurr;
					variables.varPlayerHealthMax = message.data.varPlayerHealthMax;
					variables.varPlayerHealthStartBonus = message.data.varPlayerHealthStartBonus;
					variables.varPlayerHealthBase = message.data.varPlayerHealthBase;
					variables.varPlayerHealthEffect = message.data.varPlayerHealthEffect;
					variables.varPlayerHealthLvlUpBonus = message.data.varPlayerHealthLvlUpBonus;
					variables.varPlayerHealthCurr = message.data.varPlayerHealthCurr;
					variables.varPlayerLvl = message.data.varPlayerLvl;
					variables.varPlayerStaminaRegen = message.data.varPlayerStaminaRegen;
					variables.varPlayerManaRegen = message.data.varPlayerManaRegen;
					variables.varPlayerHealthRegen = message.data.varPlayerHealthRegen;
					variables.varPlayerSprintOutcome = message.data.varPlayerSprintOutcome;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
