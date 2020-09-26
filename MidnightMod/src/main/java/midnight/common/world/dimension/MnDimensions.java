package midnight.common.world.dimension;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = "midnight")
public final class MnDimensions {
    public static final ResourceLocation MIDNIGHT_ID = new ResourceLocation("midnight:midnight");
    public static final ModDimension MIDNIGHT_DIMENSION = ModDimension.withFactory(MidnightDimension::new);
    private static DimensionType midnight;

    public static void registerDimensions(IForgeRegistry<ModDimension> registry) {
        registry.registerAll(
            MIDNIGHT_DIMENSION.setRegistryName(MIDNIGHT_ID)
        );
    }

    private MnDimensions() {
    }

    @SubscribeEvent
    public static void onRegisterDimensions(RegisterDimensionsEvent event) {
        midnight = DimensionManager.registerOrGetDimension(MIDNIGHT_ID, MIDNIGHT_DIMENSION, null, false);
    }

    public static DimensionType midnight() {
        return midnight;
    }
}
