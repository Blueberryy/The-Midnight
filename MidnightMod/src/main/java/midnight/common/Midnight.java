package midnight.common;

import midnight.MidnightInfo;
import midnight.MidnightMod;
import midnight.api.IMidnight;
import midnight.api.IMidnightInfo;
import midnight.api.event.MidnightInitEvent;
import midnight.api.event.MidnightPostInitEvent;
import midnight.api.event.MidnightPreInitEvent;
import midnight.client.MidnightClient;
import midnight.common.world.dimension.MnDimensions;
import midnight.core.plugin.PluginManager;
import midnight.data.MidnightData;
import midnight.server.MidnightServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class of the Midnight. From here the complete mod is controlled. A few subclasses of this class exist, each one
 * for a different runtime variant of the Midnight:
 * <ul>
 * <li>
 * {@link MidnightClient} is the client-side proxy of this class. It handles additional client-only loading and
 * delegates certain method calls to appropriate client-only classes.
 * </li>
 * <li>
 * {@link MidnightServer} is the server-side proxy of this class. It does not very much other than existing and
 * delegating everything to the {@link Midnight} class itself.
 * </li>
 * <li>
 * {@link MidnightData} is the data-generator-only proxy of this class. This is a subclass of {@link MidnightClient}
 * that is only used when generating data. It prevents the unnecessary initialization of certain client-only things,
 * such as rendering and the loading of plugins.
 * </li>
 * </ul>
 */
public abstract class Midnight implements IMidnight {
    public static final Logger LOGGER = LogManager.getLogger("Midnight");

    private final PluginManager pluginManager = new PluginManager();



    /*
     * INITIALIZATION HANDLERS
     */

    /**
     * Called on pre-initialization, in the constructor of the {@link MidnightMod} class.
     */
    public void preInit() {
        if (!MidnightInfo.DATAGEN) {
            EVENT_BUS.start(); // Start the event bus manually when we're available
            pluginManager.loadPlugins();
            EVENT_BUS.post(new MidnightPreInitEvent(this, getRuntimeDist()));
        }
        MnDimensions.init();
    }

    /**
     * Called on initialization, in the {@link FMLCommonSetupEvent} handler.
     */
    public void init() {
        EVENT_BUS.post(new MidnightInitEvent(this, getRuntimeDist()));
    }

    /**
     * Called on post-initialization, in the {@link FMLLoadCompleteEvent} handler.
     */
    public void postInit() {
        EVENT_BUS.post(new MidnightPostInitEvent(this, getRuntimeDist()));
    }



    /*
     * PLUGIN MANAGER
     */

    /**
     * Returns the {@link PluginManager} of the current Midnight runtime.
     */
    public PluginManager getPluginManager() {
        return pluginManager;
    }





    @Override
    public final IMidnightInfo getInfo() {
        return MidnightInfo.INSTANCE;
    }

    /**
     * Returns the current {@link Midnight} instance, as in {@link MidnightMod#MIDNIGHT}. Prefer using this over using
     * {@link MidnightMod#MIDNIGHT} itself.
     */
    public static Midnight get() {
        return MidnightMod.MIDNIGHT;
    }

    /**
     * Create a {@link ResourceLocation} with {@link MidnightInfo#MODID midnight} as default namespace.
     * <ul>
     * <li>{@code "minecraft:path"} will yield {@code minecraft:path}</li>
     * <li>{@code "midnight:path"} will yield {@code midnight:path}</li>
     * <li>{@code "path"} will yield {@code midnight:path} (unlike the {@link ResourceLocation#ResourceLocation(String) ResourceLocation} constructor will yield {@code minecraft:path})</li>
     * </ul>
     *
     * @param path The resource path.
     * @return The created {@link ResourceLocation} instance.
     */
    public static ResourceLocation resLoc(String path) {
        int colon = path.indexOf(':');
        if (colon >= 0) {
            return new ResourceLocation(path.substring(0, colon), path.substring(colon + 1));
        }
        return new ResourceLocation(MidnightInfo.MODID, path);
    }

    /**
     * Create a stringified {@link ResourceLocation} with {@link MidnightInfo#MODID midnight} as default namespace. See
     * {@link #resLoc}.
     *
     * @param path The resource path.
     * @return The created resource id.
     */
    public static String resStr(String path) {
        if (path.indexOf(':') >= 0) {
            return path;
        }
        return MidnightInfo.MODID + ":" + path;
    }
}
