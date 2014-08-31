package mods.touhou_yukari_core.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelShikigami extends ModelBiped {
	
    public ModelShikigami(float par1)
    {
    	this(par1, 0.0F, 64, 64);
    }
    
    public ModelShikigami(float par1, float par2, int par3, int par4)
    {
        this.textureWidth = par3;
        this.textureHeight = par4;
        
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, par1);
        this.bipedHead.setRotationPoint(0.0F, 6.0F + par2, 0.0F);
        
        this.bipedHeadwear = new ModelRenderer(this, 24, 0);
        this.bipedHeadwear.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, par1 + 0.3F);
        this.bipedHeadwear.setRotationPoint(0.0F, 6.0F + par2, 0.0F);
        
        ModelRenderer rightear = new ModelRenderer(this, 48, 0);
        rightear.addBox(-2F, -2F, -1F, 2, 2, 1);
        rightear.setRotationPoint(0F, -6F, 0F);
        setRotation(rightear, 0F, 0F, -0.7853982F);
        this.bipedHeadwear.addChild(rightear);
        
        ModelRenderer leftear = new ModelRenderer(this, 48, 0);
        leftear.addBox(0F, 0F, -1F, 2, 2, 1);
        leftear.setRotationPoint(0F, -6F, 0F);
        setRotation(leftear, 0F, 0F, -0.7853982F);
        this.bipedHeadwear.addChild(leftear);
        
        this.bipedBody = new ModelRenderer(this, 0, 12);
        this.bipedBody.addBox(-3.0F, 0.0F, -1.5F, 6, 8, 3, par1);
        this.bipedBody.setRotationPoint(0.0F, 6.0F + par2, 0.0F);
        
        ModelRenderer skirt1 = new ModelRenderer(this, 0, 29);
        skirt1.addBox(-3F, 0F, -2F, 6, 1, 4);
        skirt1.setRotationPoint(0F, 6F, 0F);
        setRotation(skirt1, 0F, 0F, 0F);
        this.bipedBody.addChild(skirt1);
        
        ModelRenderer skirt2 = new ModelRenderer(this, 0, 34);
        skirt2.addBox(-4F, 0F, -3F, 8, 3, 6);
        skirt2.setRotationPoint(0F, 7F, 0F);
        setRotation(skirt2, 0F, 0F, 0F);
        this.bipedBody.addChild(skirt2);
        
        ModelRenderer skirt3 = new ModelRenderer(this, 0, 43);
        skirt3.addBox(-5F, 0F, -4F, 10, 5, 8);
        skirt3.setRotationPoint(0F, 10F, 0F);
        setRotation(skirt3, 0F, 0F, 0F);
        this.bipedBody.addChild(skirt3);
        
        ModelRenderer chest = new ModelRenderer(this, 0, 24);
        chest.addBox(-3F, 1.2F, -1F, 6, 3, 2);
        chest.setRotationPoint(0F, 0F, 0F);
        setRotation(chest, -0.4F, 0F, 0F);
        this.bipedBody.addChild(chest);
        
        ModelRenderer tail1 = new ModelRenderer(this, 38, 12);
        tail1.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail1.setRotationPoint(0F, 6F, 2F);
        setRotation(tail1, 0.4833219F, 0F, -0.4363323F);
        this.bipedBody.addChild(tail1);
        
        ModelRenderer tail2 = new ModelRenderer(this, 38, 12);
        tail2.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail2.setRotationPoint(0F, 6F, 2F);
        setRotation(tail2, 0.4833219F, 0F, 2.617994F);
        this.bipedBody.addChild(tail2);
        
        ModelRenderer tail3 = new ModelRenderer(this, 38, 12);
        tail3.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail3.setRotationPoint(0F, 6F, 1F);
        setRotation(tail3, 0.4833219F, 0F, 1.745329F);
        this.bipedBody.addChild(tail3);
        
        ModelRenderer tail4 = new ModelRenderer(this, 38, 12);
        tail4.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail4.setRotationPoint(0F, 6F, 2F);
        setRotation(tail4, 0.4833219F, 0F, -2.617994F);
        this.bipedBody.addChild(tail4);
        
        ModelRenderer tail5 = new ModelRenderer(this, 38, 12);
        tail5.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail5.setRotationPoint(0F, 6F, 2F);
        setRotation(tail5, 0.4833219F, 0F, -1.745329F);
        this.bipedBody.addChild(tail5);
        
        ModelRenderer tail6 = new ModelRenderer(this, 38, 12);
        tail6.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail6.setRotationPoint(0F, 6F, 2F);
        setRotation(tail6, 0.8179294F, 0F, 0.9599311F);
        this.bipedBody.addChild(tail6);
        
        ModelRenderer tail7 = new ModelRenderer(this, 38, 12);
        tail7.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail7.setRotationPoint(0F, 6F, 2F);
        setRotation(tail7, 0.9294653F, 0F, 3.141593F);
        this.bipedBody.addChild(tail7);
        
        ModelRenderer tail8 = new ModelRenderer(this, 38, 12);
        tail8.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail8.setRotationPoint(0F, 6F, 2F);
        setRotation(tail8, 0.8179294F, 0F, -0.9599311F);
        this.bipedBody.addChild(tail8);
        
        ModelRenderer tail9 = new ModelRenderer(this, 38, 12);
        tail9.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
        tail9.setRotationPoint(0F, 6F, 2F);
        setRotation(tail9, 0.4833219F, 0F, 0.4363323F);
        this.bipedBody.addChild(tail9);
        
        this.bipedRightArm = new ModelRenderer(this, 30, 12);
        this.bipedRightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 7, 2, par1);
        this.bipedRightArm.setRotationPoint(-3.0F, 7.0F + par2, 0.0F);
        
        this.bipedLeftArm = new ModelRenderer(this, 30, 12);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 7, 2, par1);
        this.bipedLeftArm.setRotationPoint(3.0F, 7.0F + par2, 2.0F);
        
        this.bipedRightLeg = new ModelRenderer(this, 18, 12);
        this.bipedRightLeg.addBox(-3.0F, 0.0F, -1.5F, 3, 9, 3, par1);
        this.bipedRightLeg.setRotationPoint(-0.0F, 15.0F + par2, 0.0F);
        
        this.bipedLeftLeg = new ModelRenderer(this, 18, 12);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(0.0F, 0.0F, -1.5F, 3, 9, 3, par1);
        this.bipedLeftLeg.setRotationPoint(0.0F, 12.0F + par2, 0.0F);
    }
    
    /**
     * 回転角の設定
     * @param model 設定対象のボックス
     * @param x X軸回転角
     * @param y Y軸回転角
     * @param z Z軸回転角
     */
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
//        model.rotateAngleX = x / (180F / (float)Math.PI);
//        model.rotateAngleY = y / (180F / (float)Math.PI);
//        model.rotateAngleZ = z / (180F / (float)Math.PI);
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.bipedHead.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 3.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 3.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 3.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 3.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 15.0F;
            this.bipedLeftLeg.rotationPointY = 15.0F;
            this.bipedHead.rotationPointY = 6.0F;
            this.bipedHeadwear.rotationPointY = 6.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        this.bipedHead.render(par7);
        this.bipedBody.render(par7);
        this.bipedRightArm.render(par7);
        this.bipedLeftArm.render(par7);
        this.bipedRightLeg.render(par7);
        this.bipedLeftLeg.render(par7);
        this.bipedHeadwear.render(par7);
    }

}
