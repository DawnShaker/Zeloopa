
package net.mcreator.zeloopa.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.zeloopa.itemgroup.ModPageItemGroup;
import net.mcreator.zeloopa.ZeloopaModElements;

@ZeloopaModElements.ModElement.Tag
public class MalachiteDustItem extends ZeloopaModElements.ModElement {
	@ObjectHolder("zeloopa:malachite_dust")
	public static final Item block = null;
	public MalachiteDustItem(ZeloopaModElements instance) {
		super(instance, 186);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ModPageItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("malachite_dust");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
