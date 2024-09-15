package org.multicoder.zombiesplus.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ZWaveGoal extends Goal
{
    private final Mob mob;
    private final Level world;
    private int TickCounter = 0;

    public ZWaveGoal(Mob mob)
    {
        this.mob = mob;
        this.world = mob.level();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void tick()
    {
        super.tick();
        if(TickCounter == 20)
        {
            List<Creeper> Creepers = world.getNearbyEntities(Creeper.class, TargetingConditions.forNonCombat(),mob, AABB.encapsulatingFullBlocks(mob.blockPosition().offset(-5,0,-5),mob.blockPosition().offset(5,0,5)));
            Creepers.forEach(creeper ->
            {
                mob.targetSelector.getAvailableGoals().forEach(wrappedGoal ->
                {
                    if(wrappedGoal.getGoal() instanceof NearestAttackableTargetGoal goal)
                    {
                        Class<?> Target = goal.target.getClass();
                        creeper.targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(creeper,(Class<? extends LivingEntity>) Target,false));
                    }
                });
            });
            TickCounter = 0;
        }
        else {
            TickCounter++;
        }
    }

    @Override
    public boolean canUse()
    {
        return true;
    }
}
