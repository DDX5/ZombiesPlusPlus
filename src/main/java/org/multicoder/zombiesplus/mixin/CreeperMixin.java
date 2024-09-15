package org.multicoder.zombiesplus.mixin;


import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import org.multicoder.zombiesplus.ai.DestroyCropGoal;
import org.multicoder.zombiesplus.config.ZombiesPlusConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
@SuppressWarnings("all")
public abstract class CreeperMixin
{
    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void Inject(CallbackInfo ci)
    {
        if(ZombiesPlusConfig.CREEPER_MODULE)
        {
            Creeper instance = (Creeper) (Object) this;
            if(!ZombiesPlusConfig.NIGHTMARE_MODE)
            {
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Cow.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Sheep.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Chicken.class,false));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Bee.class,false));
                instance.goalSelector.addGoal(3,new DestroyCropGoal(instance,-5,5,true));
                return;
            }
            else
            {
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Cow.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Sheep.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Chicken.class,true));
                instance.targetSelector.addGoal(4,new NearestAttackableTargetGoal<>(instance, Bee.class,true));
                instance.goalSelector.addGoal(3,new DestroyCropGoal(instance,-15,15,false));
            }
        }
    }
}
