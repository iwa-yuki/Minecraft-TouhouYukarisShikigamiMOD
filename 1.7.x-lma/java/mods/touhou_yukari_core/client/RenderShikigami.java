package mods.touhou_yukari_core.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import mods.touhou_yukari_core.*;

public class RenderShikigami extends RenderBiped {

    private static final ResourceLocation shikigamiTexture =
    		new ResourceLocation("textures/entity/steve.png");
    
	private ModelShikigami modelArmorChestplate;
	private ModelShikigami modelArmor;
	private ModelShikigami modelShikigami;
	
	public RenderShikigami()
	{
		this(new ModelShikigami(0.0F), 0.5F);
	}
    
	public RenderShikigami(ModelBiped par1ModelBiped, float par2)
	{
		super(par1ModelBiped, par2);
		
        this.modelBipedMain = (ModelShikigami)this.mainModel;
        this.modelArmorChestplate = (ModelShikigami)this.field_82423_g;
        this.modelArmor = (ModelShikigami)this.field_82425_h;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
//    public void doRender(EntityShikigami entity, double par2, double par4, double par6, float par8, float par9)
//    {
//        GL11.glColor3f(1.0F, 1.0F, 1.0F);
//        ItemStack itemstack = entity.getHeldItem();
//        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;
//
//        if (itemstack != null)
//        {
//            EnumAction enumaction = itemstack.getItemUseAction();
//
//            if (enumaction == EnumAction.block)
//            {
//                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
//            }
//            else if (enumaction == EnumAction.bow)
//            {
//                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
//            }
//        }
//
//        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = entity.isSneaking();
//        double d3 = par4 - (double)entity.yOffset;
//
//        if (entity.isSneaking())
//        {
//            d3 -= 0.125D;
//        }
//
//        super.doRender((EntityLivingBase)entity, par2, d3, par6, par8, par9);
//        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
//        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
//        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
//    }

	
	///////////////////////////////////////////////////////////////////////////
	
    @Override
    protected void func_82421_b()
    {
    	this.field_82423_g = new ModelShikigami(0.8F);
    	this.field_82425_h = new ModelShikigami(0.4F);
    }
    
	@Override
    protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving)
    {
        return shikigamiTexture;
    }
	
//	@Override
//	public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
//    {
//        this.doRender((EntityShikigami)par1EntityLivingBase, par2, par4, par6, par8, par9);
//    }
//	
//	@Override
//    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
//    {
//        this.doRender((EntityShikigami)par1Entity, par2, par4, par6, par8, par9);
//    }
}
