package org.multicoder.zombiesplusplus.mixin;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import org.multicoder.zombiesplusplus.ai.DestroyCropGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
@SuppressWarnings("all")
public abstract class ZombieMixin
{
    @Inject(method = "initGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        ZombieEntity instance = (ZombieEntity) (Object) this;
        instance.targetSelector.add(3,new ActiveTargetGoal<>(instance, SheepEntity.class,true));
        instance.targetSelector.add(3,new ActiveTargetGoal<>(instance, CowEntity.class,true));
        instance.targetSelector.add(3,new ActiveTargetGoal<>(instance, PigEntity.class,true));
        instance.targetSelector.add(3,new ActiveTargetGoal<>(instance, ChickenEntity.class,true));
        instance.goalSelector.add(2,new DestroyCropGoal(instance));
    }
}
