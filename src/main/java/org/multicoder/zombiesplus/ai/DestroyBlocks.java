package org.multicoder.zombiesplus.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import org.multicoder.zombiesplus.ZombiesPlus;

import java.util.concurrent.atomic.AtomicBoolean;

public class DestroyBlocks extends Goal
{
    private final Mob mob;
    private final Level world;
    private BlockPos targetPos;
    private Path path;
    private final int XZDistanceMinus;
    private final int XZDistancePlus;

    public DestroyBlocks(Mob mob, int zx, int zx2)
    {
        this.mob = mob;
        this.world = mob.level();
        this.XZDistanceMinus = zx;
        this.XZDistancePlus = zx2;
    }

    @Override
    public boolean canUse() {
        BlockPos mobPos = this.mob.blockPosition();
        for (int x = XZDistanceMinus; x <= XZDistancePlus; x++)
        {
            for (int y = -2; y <= 2; y++)
            {
                for (int z = XZDistanceMinus; z <= XZDistancePlus; z++)
                {
                    BlockPos pos = mobPos.offset(x, y, z);
                    if (this.isTag(pos))
                    {
                        this.targetPos = pos;
                        this.path = this.mob.getNavigation().createPath(pos, 1);
                        return this.path != null;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && this.isTag(this.targetPos);
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, 1.1);
    }

    @Override
    public void stop() {
        this.targetPos = null;
        this.path = null;
    }

    @Override
    public void tick() {
        if (this.mob.distanceToSqr(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ()) < 2.0) {
            this.world.destroyBlock(this.targetPos, false);
            this.targetPos = null;
        }
    }

    private boolean isTag(BlockPos pos)
    {
        return this.world.getBlockState(pos).is(ZombiesPlus.PLANKS_TAG)
                || this.world.getBlockState(pos).is(ZombiesPlus.LOGS_TAG)
                || this.world.getBlockState(pos).is(ZombiesPlus.FENCES_TAG)
                || this.world.getBlockState(pos).is(ZombiesPlus.FENCE_GATES_TAG)
                || this.world.getBlockState(pos).is(ZombiesPlus.DOORS_TAG);
    }
}
