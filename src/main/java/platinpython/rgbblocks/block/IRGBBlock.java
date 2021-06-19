package platinpython.rgbblocks.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import platinpython.rgbblocks.tileentity.RGBTileEntity;
import platinpython.rgbblocks.util.registries.TileEntityRegistry;

public interface IRGBBlock {
	public static boolean hasTileEntity(final BlockState state) {
		return true;
	}

	public static TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityRegistry.RGB.get().create();
	}

	public static void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer,
			ItemStack stack) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (stack.hasTag() == true && tileEntity instanceof RGBTileEntity) {
			((RGBTileEntity) tileEntity).setColor(stack.getTag().getInt("color"));
		}
	}

	public static ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		ItemStack stack = new ItemStack(state.getBlock().asItem());
		TileEntity tileEntity = world.getBlockEntity(pos);
		if(tileEntity instanceof RGBTileEntity) {
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("color", ((RGBTileEntity) tileEntity).getColor());
			stack.setTag(tag);
		}
		return stack;
	}	
}
