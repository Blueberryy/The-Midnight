package midnight.common.item.group;

import midnight.common.block.MnBlocks;
import midnight.common.item.MnItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.*;
import java.util.function.Supplier;

/**
 * This class holds all the item groups for the Midnight's creative tabs, and for categorizing recipes in folders during
 * data generation.
 *
 * @author Shadew
 * @version 0.6.0
 * @since 0.6.0
 */
public class MnItemGroup extends ItemGroup {
    public static final MnItemGroup BLOCKS = new MnItemGroup("blocks", () -> MnBlocks.NIGHT_GRASS_BLOCK).categories(
        MnItemCategory.SEDIMENTAL,
        MnItemCategory.LOGS,
        MnItemCategory.PLANKS,
        MnItemCategory.SHROOM_STEMS,
        MnItemCategory.UNCATEGORIZED
    );
    public static final MnItemGroup DECOR = new MnItemGroup("decor", () -> MnBlocks.NIGHT_GRASS).categories(
        MnItemCategory.LEAVES,
        MnItemCategory.SHROOM_CAPS,
        MnItemCategory.SAPLINGS,
        MnItemCategory.COMMON_PLANTS,
        MnItemCategory.UNCATEGORIZED
    );
    public static final MnItemGroup MISC = new MnItemGroup("misc", () -> MnItems.DARK_STICK).categories(
        MnItemCategory.COMMON_ITEMS,
        MnItemCategory.UNCATEGORIZED
    );

    private final ITextComponent translationKey;
    private final Supplier<IItemProvider> icon;

    private final List<MnItemCategory> categories = new ArrayList<>();

    public MnItemGroup(String label, Supplier<IItemProvider> icon) {
        super(label);
        translationKey = new TranslationTextComponent("itemGroup.midnight." + label);
        this.icon = icon;
    }

    @Override
    public ITextComponent getTranslationKey() {
        return translationKey;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(icon.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fill(NonNullList<ItemStack> list) {
        Set<Item> doneItems = new HashSet<>();
        for(MnItemCategory cat : categories) { // Meow
            cat.fill(this, list, doneItems);
        }

        for(Item item : Registry.ITEM) {
            if(!doneItems.contains(item)) {
                item.fillItemGroup(this, list);
            }
        }
    }

    // Purr                     // Mieow
    public MnItemGroup categories(MnItemCategory... cats) {
        categories.addAll(Arrays.asList(cats /* Mreow */));
        return this;   // Murreow
    } // Meow
}
