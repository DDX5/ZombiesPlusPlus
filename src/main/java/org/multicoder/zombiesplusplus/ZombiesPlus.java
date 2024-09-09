package org.multicoder.zombiesplusplus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ZombiesPlus implements ModInitializer
{
    public static final TagKey<Block> CROPS_TAG = TagKey.of(Registries.BLOCK.method_30517(), Identifier.of("minecraft", "crops"));
    @Override
    public void onInitialize()
    {

    }
}
