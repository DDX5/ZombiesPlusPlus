package org.multicoder.zombiesplus.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import org.multicoder.zombiesplus.ZombiesPlus;

public class DestroyCropGoal extends Goal
{
    private final Mob mob;
    private final Level world;
    private BlockPos targetPos;
    private Path path;
    private final int XZDistanceMinus;
    private final int XZDistancePlus;
    private final boolean ShouldDrop;

    public DestroyCropGoal(Mob mob,int zx,int zx2, boolean drops)
    {
        this.mob = mob;
        this.world = mob.level();
        this.XZDistanceMinus = zx;
        this.XZDistancePlus = zx2;
        this.ShouldDrop = drops;
    }

    @Override
    public boolean canUse()
    {
        BlockPos mobPos = mob.blockPosition();
        for (int x = XZDistanceMinus; x <= XZDistancePlus; x++)
        {
            for (int y = -2; y <= 2; y++)
            {
                for (int z = XZDistanceMinus; z <= XZDistancePlus; z++)
                {
                    BlockPos pos = mobPos.offset(x, y, z);
                    if (isTag(pos))
                    {
                        targetPos = pos;
                        path = mob.getNavigation().createPath(pos, 1);
                        return path != null;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse()
    {
        return targetPos != null && isTag(targetPos);
    }

    @Override
    public void start()
    {
        mob.getNavigation().moveTo(path, 1.1);
    }

    @Override
    public void stop()
    {
        targetPos = null;
        path = null;
        mob.getNavigation().stop();
    }

    @Override
    public void tick()
    {
        if (mob.distanceToSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ()) < 2.0)
        {
            world.destroyBlock(targetPos, ShouldDrop);
            targetPos = null;
        }
    }
    private boolean isTag(BlockPos pos)
    {
        return world.getBlockState(pos).is(ZombiesPlus.CROPS_TAG);
    }
}
