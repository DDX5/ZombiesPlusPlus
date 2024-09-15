package org.multicoder.zombiesplusplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multicoder.zombiesplusplus.config.ZombiesPlusConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ZombiesPlus implements ModInitializer
{
    public static final String MODID = "zombiesplusplus";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static boolean NIGHTMARE_MODE = false;
    public static final TagKey<Block> CROPS_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "crops"));
    public static final TagKey<Block> DOORS_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "doors"));
    public static final TagKey<Block> FENCES_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "fences"));
    public static final TagKey<Block> FENCE_GATES_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "fence_gates"));
    public static final TagKey<Block> LOGS_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "logs"));
    public static final TagKey<Block> PLANKS_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "planks"));

    @Override
    public void onInitialize()
    {
        ZombiesPlusConfig.FetchConfig();
    }
}
