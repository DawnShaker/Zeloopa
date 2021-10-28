
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

import net.mcreator.zeloopa.ZeloopaMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class GUIRaceChooseImperialGuiWindow extends ContainerScreen<GUIRaceChooseImperialGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = GUIRaceChooseImperialGui.guistate;
	public GUIRaceChooseImperialGuiWindow(GUIRaceChooseImperialGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 350;
		this.ySize = 200;
	}
	private static final ResourceLocation texture = new ResourceLocation("zeloopa:textures/gui_race_choose_imperial.png");
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
		this.font.drawString(ms, "Race: Imperial", 8, 9, -16777216);
		this.font.drawString(ms, "Strength +7.5", 8, 29, -16777216);
		this.font.drawString(ms, "Agility + 7.5", 8, 48, -16777216);
		this.font.drawString(ms, "Endurance +5", 8, 66, -16777216);
		this.font.drawString(ms, "Intelligence +5", 8, 84, -16777216);
		this.font.drawString(ms, "Strenght of will +5", 9, 102, -16777216);
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
		this.addButton(new Button(this.guiLeft + 9, this.guiTop + 170, 110, 20, new StringTextComponent("Back to race list"), e -> {
			if (true) {
				ZeloopaMod.PACKET_HANDLER.sendToServer(new GUIRaceChooseImperialGui.ButtonPressedMessage(0, x, y, z));
				GUIRaceChooseImperialGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 285, this.guiTop + 170, 55, 20, new StringTextComponent("Choose"), e -> {
			if (true) {
				ZeloopaMod.PACKET_HANDLER.sendToServer(new GUIRaceChooseImperialGui.ButtonPressedMessage(1, x, y, z));
				GUIRaceChooseImperialGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
