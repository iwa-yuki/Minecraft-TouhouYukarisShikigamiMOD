package mods.touhou_yukari_core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityShikigami extends EntityLiving{

	EntityAIJavaScript aiScript;
	
	public EntityShikigami(World par1World) {
		super(par1World);
		
		aiScript = new EntityAIJavaScript(this,new ResourceLocation("js", "test.js"));
        this.tasks.addTask(0, aiScript);
	}
	
	@Override
    protected boolean isAIEnabled()
    {
		return true;
    }
	
	@Override
    protected boolean canDespawn()
    {
		return false;
    }
	
	@Override
    public void onLivingUpdate()
    {
		if(!aiScript.isEnable) {
			this.setDead();
		}
		
		super.onLivingUpdate();
    }
}
