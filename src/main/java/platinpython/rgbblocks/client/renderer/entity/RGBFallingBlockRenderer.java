package platinpython.rgbblocks.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import platinpython.rgbblocks.RGBBlocks;
import platinpython.rgbblocks.entity.RGBFallingBlockEntity;
import platinpython.rgbblocks.util.registries.BlockRegistry;

public class RGBFallingBlockRenderer extends EntityRenderer<RGBFallingBlockEntity> {
	private final ItemRenderer itemRenderer;

	public RGBFallingBlockRenderer(Context context) {
		super(context);
		this.shadowRadius = 0.5f;
		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	public void render(RGBFallingBlockEntity fallingBlockEntity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource renderTypeBuffer, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.5D, 0.0D);
		ItemStack stack = new ItemStack(BlockRegistry.RGB_CONCRETE_POWDER.get());
		stack.getOrCreateTag().putInt("color", fallingBlockEntity.getColor());
		itemRenderer.renderStatic(stack, TransformType.NONE, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, renderTypeBuffer, fallingBlockEntity.getId());
		matrixStackIn.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(RGBFallingBlockEntity fallingBlockEntity) {
		return new ResourceLocation(RGBBlocks.MOD_ID, "concrete_powder");
	}
}
