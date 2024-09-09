package org.multicoder.zombiesplusplus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZombiesPlusPlus implements ModInitializer
{
    public static final TagKey<Block> CROPS_TAG = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "crops"));

    @Override
    public void onInitialize() 
    {

    }
}
