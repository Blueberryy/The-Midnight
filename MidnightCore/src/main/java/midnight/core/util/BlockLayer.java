package midnight.core.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

/**
 * Replacement for the removed {@code BlockRenderLayer}. We use this in block builders to dynamically set the block
 * render layer and delegate that to the {@link RenderTypeLookup} when available.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
public enum BlockLayer {
    SOLID,
    CUTOUT,
    CUTOUT_MIPPED,
    TRANSLUCENT;

    /**
     * Returns the corresponding {@link RenderType} for this render layer.
     */
    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType() {
        switch (this) {
            default:
            case SOLID:
                return RenderType.getSolid();
            case CUTOUT:
                return RenderType.getCutout();
            case CUTOUT_MIPPED:
                return RenderType.getCutoutMipped();
            case TRANSLUCENT:
                return RenderType.getTranslucent();
        }
    }

    /**
     * Returns the render layer corresponding to the specified {@link RenderType}, or {@link #SOLID} if not found.
     */
    @OnlyIn(Dist.CLIENT)
    public static BlockLayer getFromRenderType(RenderType type) {
        if (type == RenderType.getSolid()) return SOLID;
        if (type == RenderType.getCutout()) return CUTOUT;
        if (type == RenderType.getCutoutMipped()) return CUTOUT_MIPPED;
        if (type == RenderType.getTranslucent()) return TRANSLUCENT;
        return SOLID;
    }
}
