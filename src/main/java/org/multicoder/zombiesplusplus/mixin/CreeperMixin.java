package org.multicoder.zombiesplusplus.mixin;


import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import org.multicoder.zombiesplusplus.ai.DestroyCropGoal;
import org.multicoder.zombiesplusplus.config.ZombiesPlusConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
@SuppressWarnings("all")
public abstract class CreeperMixin
{
    @Inject(method = "initGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        if(ZombiesPlusConfig.CREEPER_MODULE)
        {
            CreeperEntity instance = (CreeperEntity) (Object) this;
            if(!ZombiesPlusConfig.NIGHTMARE_MODE)
            {
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, CowEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, SheepEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, ChickenEntity.class,false));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, BeeEntity.class,false));
                instance.goalSelector.add(3,new DestroyCropGoal(instance,-5,5,true));
                return;
            }
            else
            {
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, CowEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, SheepEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, ChickenEntity.class,true));
                instance.targetSelector.add(4,new ActiveTargetGoal<>(instance, BeeEntity.class,true));
                instance.goalSelector.add(3,new DestroyCropGoal(instance,-15,15,false));
            }
        }
    }
}
