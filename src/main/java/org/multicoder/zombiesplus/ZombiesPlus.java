package org.multicoder.zombiesplus;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multicoder.zombiesplus.config.ZombiesPlusConfig;


@Mod(ZombiesPlus.MODID)
public class ZombiesPlus
{
    public static final String MODID = "zombiesplusplus";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final TagKey<Block> CROPS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "crops"));
    public static final TagKey<Block> DOORS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "doors"));
    public static final TagKey<Block> FENCES_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "fences"));
    public static final TagKey<Block> FENCE_GATES_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "fence_gates"));
    public static final TagKey<Block> LOGS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "logs"));
    public static final TagKey<Block> PLANKS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "planks"));

    public ZombiesPlus(IEventBus modEventBus, ModContainer modContainer)
    {
        ZombiesPlusConfig.FetchConfig();
    }
}
