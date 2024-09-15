package org.multicoder.zombiesplus.mixin;


import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import org.multicoder.zombiesplus.ai.DestroyBlocks;
import org.multicoder.zombiesplus.ai.DestroyCropGoal;
import org.multicoder.zombiesplus.config.ZombiesPlusConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkeleton.class)
@SuppressWarnings("all")
public abstract class SkeletonMixin
{
    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        if(ZombiesPlusConfig.SKELETON_MODULE)
        {
            Skeleton instance = (Skeleton) (Object) this;
            if(!ZombiesPlusConfig.NIGHTMARE_MODE)
            {
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Rabbit.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Bee.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, WanderingTrader.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Cat.class,true));
                instance.goalSelector.addGoal(4,new OpenDoorGoal(instance,false));
                return;
            }
            else
            {
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Rabbit.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Bee.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, WanderingTrader.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Cat.class,false));
                instance.goalSelector.addGoal(4,new OpenDoorGoal(instance,true));
            }
        }
    }
}
