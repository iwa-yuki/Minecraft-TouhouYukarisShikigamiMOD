package mods.touhou_alice_core.AI;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.entity.player.EntityPlayer;
import mods.touhou_alice_core.EntityAliceDoll;
import net.minecraft.util.MathHelper;

/**
 * 持ち主を追いかける
 */
public class EntityDollAIFollowOwner extends EntityDollAIBase
{
    private EntityPlayer theOwner;
    private float speed;
    private float maxDist;
    private float minDist;
    private PathNavigate pathfinder;
    private boolean avoidsWater;
    private int counter;

    public EntityDollAIFollowOwner(EntityAliceDoll doll)
    {
        super(doll);
        this.speed = 1.0F;
        this.minDist = 4.0f;
        this.maxDist = 2.5f;
        this.pathfinder = doll.getNavigator();
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        EntityPlayer owner = this.theDoll.getOwnerEntity();

        if (owner == null)
        {
            return false;
        }
        if (!this.theDoll.isFollowMode())
        {
            return false;
        }
        if (this.theDoll.getDistanceSqToEntity(owner) < (double)(this.minDist * this.minDist))
        {
            return false;
        }
        
        this.theOwner = owner;
        return true;
    }

    public boolean continueExecuting()
    {
        if(this.pathfinder.noPath())
        {
            return false;
        }
        if(this.theDoll.getDistanceSqToEntity(this.theOwner) < (double)(this.maxDist * this.maxDist))
        {
            return false;
        }
        if(!this.theDoll.isFollowMode())
        {
            return false;
        }
        return true;
    }

    public void startExecuting()
    {
        this.counter = 0;
        this.avoidsWater = this.theDoll.getNavigator().getAvoidsWater();
        this.theDoll.getNavigator().setAvoidsWater(false);
    }

    public void resetTask()
    {
        this.theOwner = null;
        this.pathfinder.clearPathEntity();
        this.theDoll.getNavigator().setAvoidsWater(this.avoidsWater);
    }

    public void updateTask()
    {
        this.theDoll.getLookHelper().setLookPositionWithEntity(
            this.theOwner, 10.0F, (float)this.theDoll.getVerticalFaceSpeed());

        if (--this.counter <= 0)
        {
            this.counter = 10;

            if (!this.pathfinder.tryMoveToEntityLiving(
                    this.theOwner, this.speed))
            {
                if (this.theDoll.getDistanceSqToEntity(this.theOwner) >= 144.0D)
                {
                    int var1 = MathHelper.floor_double(this.theOwner.posX)-2;
                    int var2 = MathHelper.floor_double(this.theOwner.posZ)-2;
                    int var3 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

                    for (int var4 = 0; var4 <= 4; ++var4)
                    {
                        for (int var5 = 0; var5 <= 4; ++var5)
                        {
                        	if ((World.doesBlockHaveSolidTopSurface(this.theWorld, var1 + var4, var3 - 1, var2 + var5)) && (!this.theWorld.getBlock(var1 + var4, var3, var2 + var5).isNormalCube()) && (!this.theWorld.getBlock(var1 + var4, var3 + 1, var2 + var5).isNormalCube()))
                            {
                                this.theDoll.setLocationAndAngles((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.theDoll.rotationYaw, this.theDoll.rotationPitch);
                                this.pathfinder.clearPathEntity();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
