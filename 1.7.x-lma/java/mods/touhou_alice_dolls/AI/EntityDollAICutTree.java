package mods.touhou_alice_dolls.AI;

import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.pathfinding.PathNavigate;

import mods.touhou_alice_core.AI.EntityDollAIBase;
import mods.touhou_alice_core.EntityAliceDoll;

import java.util.*;
import java.util.regex.*;

public class EntityDollAICutTree extends EntityDollAIBase
{
    private int counter;
    private Block logBlock;
    private int blockStrength;
    private int cutX, cutY, cutZ;
    private int targetX, targetY, targetZ;
    private boolean targetLockon;

    public static String logBlockRegex;
    public static String leavesBlockRegex;
    public static int cutRange;
    public static double cutSpeed;
    
    public EntityDollAICutTree(EntityAliceDoll doll)
    {
        super(doll);
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if(!theDoll.isEnable())
        {
            return false;
        }
        if(theDoll.isStandbyMode() || theDoll.isRideonMode())
        {
            return false;
        }

        int dollposX = MathHelper.floor_double(theDoll.posX);
        int dollposY = MathHelper.floor_double(theDoll.posY + (double)theDoll.getEyeHeight());
        int dollposZ = MathHelper.floor_double(theDoll.posZ);
        int logCount = 0;
        int leavesCount = 0;
        Pattern logPattern = Pattern.compile(logBlockRegex);
        Pattern leavesPattern = Pattern.compile(leavesBlockRegex);
        Matcher logMatcher, leavesMatcher;
        targetX = cutRange;
        targetY = 0;
        targetZ = cutRange;
        targetLockon = false;

        for(int dx=-cutRange; dx<=cutRange; ++dx)
        {
            for(int dz=-cutRange; dz<=cutRange; ++dz)
            {
                int dy = 0;
                int distanceToBlock = Math.abs(dx)+Math.abs(dz);
                if(distanceToBlock > cutRange)
                {
                    continue;
                }
                Block b = theWorld.getBlock(dollposX+dx, dollposY+dy, dollposZ+dz);
                if(b==null)
                {
                    continue;
                }
                String blockName = getBlockName(b);

                //原木ブロックを探索
                logMatcher = logPattern.matcher(blockName);
                if(logMatcher.find())
                {
                    if(Math.abs(targetX)+Math.abs(targetZ) >distanceToBlock)
                    {
                        //原木ブロックが木の一部かどうか判定
                        while(dollposY+dy > 0)
                        {
                            //根元のブロックを見つける
                            Block cb = theWorld.getBlock(dollposX+dx, dollposY+dy-1, dollposZ+dz);
                            if(cb != b)
                            {
                                break;
                            }
                            --dy;
                        }
                        int lcount = 0;
                        int bcount = 0;
                        while(dollposY+dy < theWorld.getHeight())
                        {
                            //梢のブロックまで隣接する葉ブロックの数を数える
                            Block cb = theWorld.getBlock(dollposX+dx, dollposY+dy, dollposZ+dz);
                            if(cb != b)
                            {
                                break;
                            }
                            
                            Block[] nb = new Block[4];
                            nb[0] = theWorld.getBlock(dollposX+dx+1, dollposY+dy, dollposZ+dz);
                            nb[1] = theWorld.getBlock(dollposX+dx-1, dollposY+dy, dollposZ+dz);
                            nb[2] = theWorld.getBlock(dollposX+dx, dollposY+dy, dollposZ+dz+1);
                            nb[3] = theWorld.getBlock(dollposX+dx, dollposY+dy, dollposZ+dz-1);
                            for(int i=0; i<4; ++i)
                            {
                                Block lb = nb[i];
                                if(lb!=null)
                                {
                                    String lName = getBlockName(lb);
                                    leavesMatcher = leavesPattern.matcher(lName);
                                    
                                    if(leavesMatcher.find())
                                    {
                                        ++bcount;
                                    }
                                }
                            }
                            ++lcount;
                            ++dy;
                        }
                        //System.out.println(String.format("%d,%d",bcount,lcount));
                        if(bcount >= 4)
                        {
                            targetX = dx;
                            targetY = dy-1;
                            targetZ = dz;
                            targetLockon = true;
                            logCount = bcount;
                            leavesCount = lcount;
                            logBlock = b;
                        }
                    }
                }
            }
        }
        targetX += dollposX;
        targetY += dollposY;
        targetZ += dollposZ;
        
        // 出力文字列の作成
        StringBuffer msg = new StringBuffer(theDoll.getDollName() + " : ");

        if(!targetLockon)
        {
            msg.append("No target");
            theDoll.chatMessage(msg.toString(),2);
            return false;
        }
        else
        {
            msg.append("Target[");
            msg.append(logCount);
            msg.append(",");
            msg.append(leavesCount);
            msg.append("]");
            theDoll.chatMessage(msg.toString(),1);
        }


        theDoll.teleportToXYZ((double)(targetX)+0.5D, (double)(targetY+1), (double)(targetZ)+0.5D, 0D);
        theDoll.getNavigator().clearPathEntity();

        return true;
    }
    
    public void startExecuting()
    {
        counter = 0;
    }
    
    public boolean continueExecuting()
    {
        if(!theDoll.isEnable())
        {
            return false;
        }
        if(theDoll.isStandbyMode() || theDoll.isRideonMode())
        {
            return false;
        }
        if(Math.abs(MathHelper.floor_double(theDoll.posX-targetX))
           + Math.abs(MathHelper.floor_double(theDoll.posY-targetY))
           + Math.abs(MathHelper.floor_double(theDoll.posZ-targetZ)) > cutRange)
        {
            return false;
        }
        if(targetLockon == false)
        {
            return false;
        }
        return true;
    }
    
    @Override
    public void resetTask()
    {
        targetLockon = false;
    }

    @Override
    public void updateTask()
    {
        if(counter == 0)
        {
            //伐採するブロックの検索
            boolean isCutting = false;
            for(int dx=-cutRange; dx<=cutRange; ++dx)
            {
                for(int dz=-cutRange; dz<=cutRange; ++dz)
                {
                    if(Math.abs(dx)+Math.abs(dz) > cutRange)
                    {
                        continue;
                    }
                    if(theWorld.getBlock(targetX+dx,
                                           targetY,
                                           targetZ+dz) == logBlock)
                    {
                        cutX = targetX+dx;
                        cutY = targetY;
                        cutZ = targetZ+dz;
                        isCutting = true;
                    }
                    if(isCutting)
                    {
                        break;
                    }
                }
                if(isCutting)
                {
                    break;
                }
            }
            if(!isCutting)
            {
                cutX = targetX;
                cutY = targetY;
                cutZ = targetZ;
                targetY--;
                if(theWorld.getBlock(targetX, targetY, targetZ) == logBlock)
                {
                    isCutting = true;
                }
                else
                {
                    isCutting = false;
                    targetLockon = false;
                    return;
                }
            }

            Block b = theWorld.getBlock(cutX, cutY, cutZ);
            blockStrength = b == null ? 0 : MathHelper.floor_double(1.0 + 30.0*b.getBlockHardness(theWorld, cutX, cutY, cutZ)/cutSpeed);
            blockStrength = blockStrength < 0 ? 0 : blockStrength;
        }
        //System.out.println("counter="+counter+", blockStrength="+blockStrength);
        if(counter >= blockStrength)
        {
            Block b = theWorld.getBlock(cutX, cutY, cutZ);
            if(b != null)
            {
                theWorld.func_147480_a(cutX, cutY, cutZ, true);
            }
            counter = 0;
            return;
        }
        if(counter%4 == 0)
        {
            Block b = theWorld.getBlock(targetX, targetY, targetZ);
            if(b != null)
            {
                SoundType stepsound = b.stepSound;
                theDoll.playSound(stepsound.getBreakSound(), (stepsound.getVolume() + 1.0f) / 8f, stepsound.getPitch() * 0.5f);
            }
        }
        if(!theDoll.isSwingInProgress)
        {
            theDoll.swingItem();
        }
        this.theDoll.getLookHelper().setLookPosition(
            (double)(cutX) + 0.5D,
            (double)(cutY) + 0.5D,
            (double)(cutZ) + 0.5D,
            20.0F, (float)this.theDoll.getVerticalFaceSpeed());
        counter++;
    }
    
    private String getBlockName(Block b)
    {
        if(b == null)
        {
            return "";
        }
    
        String blockName = b.getUnlocalizedName();
        if(blockName == null)
        {
            blockName = String.format("Block%d", Block.getIdFromBlock(b));
        }
        else
        {
            int dot = blockName.indexOf(".");
            if(dot != -1)
            {
                blockName = blockName.substring(dot+1);
            }
        }
        return blockName;
    }
}
