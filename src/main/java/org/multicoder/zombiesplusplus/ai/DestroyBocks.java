package org.multicoder.zombiesplusplus.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.multicoder.zombiesplusplus.ZombiesPlus;

public class DestroyBocks extends Goal
{
    private final MobEntity mob;
    private final World world;
    private BlockPos targetPos;
    private Path path;
    private final int XZDistanceMinus;
    private final int XZDistancePlus;

    public DestroyBocks(MobEntity mob, int zx, int zx2)
    {
        this.mob = mob;
        this.world = mob.getWorld();
        this.XZDistanceMinus = zx;
        this.XZDistancePlus = zx2;
    }

    @Override
    public boolean canStart() {
        BlockPos mobPos = this.mob.getBlockPos();
        for (int x = XZDistanceMinus; x <= XZDistancePlus; x++)
        {
            for (int y = -2; y <= 2; y++)
            {
                for (int z = XZDistanceMinus; z <= XZDistancePlus; z++)
                {
                    BlockPos pos = mobPos.add(x, y, z);
                    if (this.isTag(pos))
                    {
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
    public boolean shouldContinue() {
        return this.targetPos != null && this.isTag(this.targetPos);
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingAlong(this.path, 1.1);
    }

    @Override
    public void stop() {
        this.targetPos = null;
        this.path = null;
    }

    @Override
    public void tick() {
        if (this.mob.squaredDistanceTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) < 2.0) {
            this.world.breakBlock(this.targetPos, false);
            this.targetPos = null;
        }
    }

    private boolean isTag(BlockPos pos)
    {
        return this.world.getBlockState(pos).isIn(ZombiesPlus.PLANKS_TAG)
                || this.world.getBlockState(pos).isIn(ZombiesPlus.LOGS_TAG)
                || this.world.getBlockState(pos).isIn(ZombiesPlus.FENCES_TAG)
                || this.world.getBlockState(pos).isIn(ZombiesPlus.FENCE_GATES_TAG)
                || this.world.getBlockState(pos).isIn(ZombiesPlus.DOORS_TAG);
    }
}
