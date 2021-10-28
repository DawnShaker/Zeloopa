
package net.mcreator.zeloopa.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.zeloopa.ZeloopaModVariables;
import net.mcreator.zeloopa.ZeloopaMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class RaceFinallizatoinGuiWindow extends ContainerScreen<RaceFinallizatoinGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = RaceFinallizatoinGui.guistate;
	public RaceFinallizatoinGuiWindow(RaceFinallizatoinGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 350;
		this.ySize = 200;
	}
	private static final ResourceLocation texture = new ResourceLocation("zeloopa:textures/race_finallizatoin.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "" + (int) ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerStrength) + "", 111, 42, -16777216);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerAgility) + "", 111, 60, -16777216);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerEndurance) + "", 111, 78, -16777216);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerIntelligence) + "", 111, 96, -16777216);
		this.font.drawString(ms, "" + (int) ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerWill) + "", 111, 114, -16777216);
		this.font.drawString(ms, "Strenght:", 52, 42, -16777216);
		this.font.drawString(ms, "Agility:", 57, 61, -16777216);
		this.font.drawString(ms, "Endurance:", 47, 78, -16777216);
		this.font.drawString(ms, "Intelligence:", 32, 96, -16777216);
		this.font.drawString(ms, "Willpower:", 47, 113, -16777216);
		this.font.drawString(ms, "Race:", 72, 24, -16777216);
		this.font.drawString(ms, "", 111, 24, -16777216);
		this.font.drawString(ms, "" + ((entity.getCapability(ZeloopaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeloopaModVariables.PlayerVariables())).varPlayerRace) + "", 111, 24, -16777216);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 282, this.guiTop + 168, 55, 20, new StringTextComponent("Finish"), e -> {
			if (true) {
				ZeloopaMod.PACKET_HANDLER.sendToServer(new RaceFinallizatoinGui.ButtonPressedMessage(0, x, y, z));
				RaceFinallizatoinGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 12, this.guiTop + 168, 110, 20, new StringTextComponent("Back to race list"), e -> {
			if (true) {
				ZeloopaMod.PACKET_HANDLER.sendToServer(new RaceFinallizatoinGui.ButtonPressedMessage(1, x, y, z));
				RaceFinallizatoinGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
