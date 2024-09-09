package org.multicoder.zombiesplusplus.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.multicoder.zombiesplusplus.ZombiesPlusPlus;

public class DestroyCropGoal extends Goal
{
    private final MobEntity mob;
    private final World world;
    private BlockPos targetPos;
    private Path path;

    public DestroyCropGoal(MobEntity mob) {
        this.mob = mob;
        this.world = mob.getWorld();
    }

    @Override
    public boolean canStart()
    {
        BlockPos mobPos = this.mob.getBlockPos();
        for (int x = -10; x <= 10; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -10; z <= 10; z++) {
                    BlockPos pos = mobPos.add(x, y, z);
                    if (this.isCrop(pos)) {
                        this.targetPos = pos;
                        this.path = this.mob.getNavigation().findPathTo(pos, 1);
                        return this.path != null;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinue()
    {
        return this.targetPos != null && this.isCrop(this.targetPos);
    }

    @Override
    public void start()
    {
        this.mob.getNavigation().startMovingAlong(this.path, 1.0);
    }

    @Override
    public void stop() {
        this.targetPos = null;
        this.path = null;
    }

    @Override
    public void tick() {
        if (this.mob.squaredDistanceTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) < 2.0) {
            this.world.breakBlock(this.targetPos, true);
            this.targetPos = null;
        }
    }

    private boolean isCrop(BlockPos pos)
    {
        return this.world.getBlockState(pos).isIn(ZombiesPlusPlus.CROPS_TAG);
    }
}
