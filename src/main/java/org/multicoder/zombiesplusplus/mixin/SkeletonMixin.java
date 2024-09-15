package org.multicoder.zombiesplusplus.mixin;


import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LongDoorInteractGoal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import org.multicoder.zombiesplusplus.config.ZombiesPlusConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkeletonEntity.class)
@SuppressWarnings("all")
public abstract class SkeletonMixin
{
    @Inject(method = "initGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        if(ZombiesPlusConfig.SKELETON_MODULE)
        {
            AbstractSkeletonEntity instance = (AbstractSkeletonEntity) (Object) this;
            if(!ZombiesPlusConfig.NIGHTMARE_MODE)
            {
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, RabbitEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, BeeEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, WanderingTraderEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, CatEntity.class,true));
                instance.goalSelector.add(4, new LongDoorInteractGoal(instance, false));
                return;
            }
            else
            {
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, RabbitEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, BeeEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, WanderingTraderEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, CatEntity.class,false));
                instance.goalSelector.add(4,new LongDoorInteractGoal(instance,true));
            }
        }
    }
}
