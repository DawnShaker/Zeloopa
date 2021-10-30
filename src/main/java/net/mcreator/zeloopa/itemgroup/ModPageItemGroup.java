
package net.mcreator.zeloopa.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.zeloopa.item.DrakoniteIngotItem;
import net.mcreator.zeloopa.ZeloopaModElements;

@ZeloopaModElements.ModElement.Tag
public class ModPageItemGroup extends ZeloopaModElements.ModElement {
	public ModPageItemGroup(ZeloopaModElements instance) {
		super(instance, 66);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmod_page") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(DrakoniteIngotItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
