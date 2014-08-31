package mods.touhou_alice_core.AI;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import mods.touhou_alice_core.EntityAliceDoll;

/**
 * ランダムに歩き回る
 */
public class EntityDollAIWander extends EntityDollAIBase
{
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private float speed;

    public EntityDollAIWander(EntityAliceDoll doll)
    {
        super(doll);
        this.speed = 1.0F;
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
        if (this.theDoll.getRNG().nextInt(100) != 0)
        {
            return false;
        }
        if(!this.theDoll.isPatrolMode())
        {
            return false;
        }

        int par1 = 10;
        int par2 = 7;
        int k1 = theDoll.getRNG().nextInt(2 * par1) - par1;
        int l1 = theDoll.getRNG().nextInt(2 * par2) - par2;
        int i2 = theDoll.getRNG().nextInt(2 * par1) - par1;
        k1 += MathHelper.floor_double(theDoll.posX);
        l1 += MathHelper.floor_double(theDoll.posY);
        i2 += MathHelper.floor_double(theDoll.posZ);
        this.xPosition = k1;
        this.yPosition = l1;
        this.zPosition = i2;
        return true;

    }

    public boolean continueExecuting()
    {
        if(this.theDoll.getNavigator().noPath())
        {
            return false;
        }
        if(!this.theDoll.isPatrolMode())
        {
            return false;
        }
        return true;
    }

    public void startExecuting()
    {
        this.theDoll.getNavigator().tryMoveToXYZ(
            this.xPosition, this.yPosition, this.zPosition, this.speed);
    }

    @Override
    public void resetTask()
    {
        this.theDoll.getNavigator().clearPathEntity();
    }
}
