package org.multicoder.zombiesplus.mixin;


import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Zombie;
import org.multicoder.zombiesplus.ZombiesPlus;
import org.multicoder.zombiesplus.ai.DestroyBlocks;
import org.multicoder.zombiesplus.ai.DestroyCropGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
@SuppressWarnings("all")
public abstract class ZombieMixin
{
    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        Zombie instance = (Zombie) (Object) this;
        if(!ZombiesPlus.NIGHTMARE_MODE)
        {
            instance.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(instance, Sheep.class,true));
            instance.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(instance, Cow.class,true));
            instance.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(instance, Pig.class,true));
            instance.targetSelector.addGoal(3,new NearestAttackableTargetGoal<>(instance, Chicken.class,true));
            instance.targetSelector.addGoal(3,new DestroyCropGoal(instance,-10,10,true));
            return;
        }
        else
        {
            instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Sheep.class,false));
            instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Cow.class,false));
            instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Pig.class,false));
            instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Chicken.class,false));
            instance.targetSelector.addGoal(4,new DestroyCropGoal(instance,-32,32,false));
            instance.targetSelector.addGoal(3,new DestroyBlocks(instance,-32,32));
            instance.targetSelector.addGoal(4,new PanicGoal(instance,1.1f));
        }
    }
}
