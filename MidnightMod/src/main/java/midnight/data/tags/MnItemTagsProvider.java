package midnight.data.tags;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Function;

public class MnItemTagsProvider extends TagsProvider<Item> {
    private final Function<ITag.INamedTag<Block>, ITag.Builder> builderGetter;

    @SuppressWarnings("deprecation") // We need Registry.ITEM. Sorry Forge...
    public MnItemTagsProvider(DataGenerator gen, MnBlockTagsProvider blockTags) {
        super(gen, Registry.ITEM);
        this.builderGetter = blockTags::getBuilder;
    }

    @Override
    protected void registerTags() {
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    protected ITag.Builder getBuilder(ITag.INamedTag<Item> namedTag) {
        return tagToBuilder.computeIfAbsent(namedTag.getId(), id -> new ITag.Builder());
    }

    protected void copy(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag) {
        ITag.Builder itemBuilder = getBuilder(itemTag);
        ITag.Builder blockBuilder = builderGetter.apply(blockTag);
        blockBuilder.streamEntries().forEach(itemBuilder::add);
    }

    @Override
    public String getName() {
        return "Midnight - Item tags";
    }
}