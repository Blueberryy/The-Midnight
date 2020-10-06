package midnight.api;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.eventbus.api.IEventBus;

import midnight.api.plugin.MidnightEventSubscriber;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

/**
 * The main interface of the Midnight mod. Get all Midnight-related information and APIs from here. You may obtain an
 * instance of this class by calling {@link #get()}.
 *
 * @author Shadew
 * @since 0.6.0
 */
public interface IMidnight {
    /**
     * The {@link IEventBus} for Midnight-related events. Register to this event bus using {@link
     * MidnightEventSubscriber}.
     */
    IEventBus EVENT_BUS = BusBuilder.builder().startShutdown().build();

    /**
     * Returns the {@link Dist} the Midnight is running on.
     */
    Dist getRuntimeDist();

    /**
     * Returns the mod and build info of the Midnight.
     */
    IMidnightInfo getInfo();

    /**
     * Get the public {@link IMidnight} instance, if available. When not available, this method throws a {@link
     * MidnightNotInstalledException}.
     *
     * @return The {@link IMidnight} instance.
     * @throws MidnightNotInstalledException When the midnight is not installed.
     * @throws RuntimeException              When the midnight could not be obtained for some other reason.
     */
    @Nonnull
    static IMidnight get() {
        try {
            Class<?> modClass = Class.forName("midnight.MidnightMod");
            Field midnightField = modClass.getField("MIDNIGHT");
            midnightField.setAccessible(true);
            Object midnight = midnightField.get(null);
            if (midnight == null) {
                throw new RuntimeException("Field 'MidnightMod.MIDNIGHT' is null! What implementation is used?");
            }
            return (IMidnight) midnight;
        } catch (ClassNotFoundException exc) {
            throw new MidnightNotInstalledException("The Midnight is not avaiable! Make sure you only interface with the Midnight from a plugin.");
        } catch (NoSuchFieldException exc) {
            throw new RuntimeException("Couldn't find field 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        } catch (IllegalAccessException exc) {
            throw new RuntimeException("Couldn't access field 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        } catch (ClassCastException exc) {
            throw new RuntimeException("Field 'MidnightMod.MIDNIGHT' is not an 'IMidnight'! What implementation is used?", exc);
        } catch (Throwable exc) {
            throw new RuntimeException("Unable to get field value of 'MidnightMod.MIDNIGHT'! What implementation is used?", exc);
        }
    }
}
