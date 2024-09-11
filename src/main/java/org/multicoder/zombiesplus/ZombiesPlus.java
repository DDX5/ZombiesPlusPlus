package org.multicoder.zombiesplus;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


@Mod(ZombiesPlus.MODID)
public class ZombiesPlus
{
    public static final String MODID = "zombiesplusplus";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static boolean NIGHTMARE_MODE = false;
    public static final TagKey<Block> CROPS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "crops"));
    public static final TagKey<Block> DOORS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "doors"));
    public static final TagKey<Block> FENCES_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "fences"));
    public static final TagKey<Block> FENCE_GATES_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "fence_gates"));
    public static final TagKey<Block> LOGS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "logs"));
    public static final TagKey<Block> PLANKS_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "planks"));

    public ZombiesPlus(IEventBus modEventBus, ModContainer modContainer)
    {
        LOG.info("Fetching Configuration File");
        String Dir = FMLLoader.getGamePath().toAbsolutePath().toString() + "\\zombies-plus-plus.properties";
        File F = new File(Dir);
        Properties config = new Properties();
        if(F.exists())
        {
            LOG.info("File Exists, Reading Config");
            try
            {
                config.load(new FileInputStream(F));
                NIGHTMARE_MODE = Boolean.parseBoolean(config.getProperty("Nightmare-Mode"));
            }
            catch (Exception e)
            {
                LOG.error("Exception When Reading Config\nUsing Default Value.",e);
            }
        }
        else
        {
            LOG.info("Creating New Config Using Default Values");
            config.setProperty("Nightmare-Mode",Boolean.toString(false));
            try
            {
                config.store(new FileOutputStream(F),"Zombies++ Config File");
            }
            catch (Exception e)
            {
                LOG.error("Exception When Writing Config\nAttempting Retry Next Time Game Is Loaded",e);
            }
        }
    }
}
