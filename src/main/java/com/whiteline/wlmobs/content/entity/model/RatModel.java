package com.whiteline.wlmobs.content.entity.model;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RatModel<T extends Entity> extends AgeableListModel<T>{
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;

	public RatModel(ModelPart rt) {
		super(true, 0, 0);

		this.body = rt.getChild("body");
		this.head = rt.getChild("head");
		this.tail = rt.getChild("tail");
		this.leg1 = rt.getChild("leg1");
		this.leg2 = rt.getChild("leg2");
		this.leg3 = rt.getChild("leg3");
		this.leg4 = rt.getChild("leg4");
	}

	public static LayerDefinition createBodyMesh(){
		CubeDeformation deformation = new CubeDeformation(0.0F);
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.addBox("main",-2.0F, -3.0F, -3.0F, 4, 4, 3, deformation,40, 16)
				.addBox("ear1",1.0F, -5.0F, -1.0F, 3, 3, 1, deformation, 29, 14)
				.addBox("ear2",-4.0F, -5.0F, -1.0F, 3, 3, 1, deformation, 29, 14)
				.addBox("nose",-1.5F, -1.0F, -5.0F, 3, 2, 2, deformation, 19, 22)
				.addBox("hat1",-1.0F, -6.0F, -2.5F, 2, 3, 2, deformation, 56, 8)
				.addBox("hat2",-1.5F, -7.0F, -3.0F, 3, 2, 3, deformation, 43, 8), PartPose.offset(0.0F, 21.0F, -2.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().addBox("main",-2.5F, -3.0F, -3.0F, 5, 5, 8,deformation,0, 1), PartPose.offset(0.0F, 21.0F, 1.0F));

		partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().addBox("main",0.0F, -2.5F, 0.0F, 0, 3, 6,deformation,20, 0), PartPose.offset(0.0F, 20.5F, 6.0F));

		partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().addBox("main",-1.01F, 0.0F, -2.0F, 2, 1, 3,deformation,0, 14), PartPose.offset(1.5F, 23.0F, 5.0F));

		partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().addBox("main",-1.01F, 0.0F, -2.0F, 2, 1, 3,deformation,0, 14), PartPose.offset(-1.5F, 23.0F, 5.0F));

		partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create()
				.addBox("main",-1.01F, 0.0F, -2.0F, 2, 1, 3,deformation,0, 14)
				.addBox("spoon",-2.01F, 0.5F, -3.0F, 8, 0, 3,deformation,41, 2), PartPose.offset(-1.5F, 23.0F, -1.0F));

		partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().addBox("main",-1.01F, 0.0F, -2.0F, 2, 1, 3,deformation,0, 14), PartPose.offset(1.5F, 23.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 64,32);
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.leg1, this.leg2, this.leg3, this.leg4, this.tail);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = headPitch * ((float)Math.PI / 180F);
		if(!entity.isSwimming()) {
			this.body.xRot = 0;
			this.leg1.xRot = Mth.cos(limbSwing ) * limbSwingAmount;
			this.leg2.xRot = Mth.cos(limbSwing  + 0.3F) * limbSwingAmount;
			this.leg4.xRot = Mth.cos(limbSwing  + (float) Math.PI + 0.3F) * limbSwingAmount;
			this.leg3.xRot = Mth.cos(limbSwing  + (float) Math.PI) * limbSwingAmount;
			this.tail.yRot = ((float) Math.PI / 10F) * Mth.cos(limbSwing) * limbSwingAmount;

			this.body.y = 21F - Mth.abs(Mth.cos(limbSwing));
			this.leg1.y = 23F - Mth.abs(Mth.cos(limbSwing));
			this.leg2.y = 23F - Mth.abs(Mth.cos(limbSwing));
			this.leg1.z = 5F;
			this.leg2.z = 5F;
			this.leg3.y = 23F - Mth.abs(Mth.cos(limbSwing));
			this.leg4.y = 23F - Mth.abs(Mth.cos(limbSwing));
			this.tail.y = 20.5F - Mth.abs(Mth.cos(limbSwing));
			this.head.y = 21F - Mth.abs(Mth.cos(limbSwing));
			this.head.z = -2F;
		}else {
			this.body.xRot = -0.79F;
			this.leg1.xRot = Mth.cos(ageInTicks) * limbSwingAmount + 0.79F;
			this.leg2.xRot = Mth.cos(ageInTicks+ 0.3F) * limbSwingAmount + 0.79F;
			this.leg4.xRot = Mth.cos(ageInTicks+ (float) Math.PI + 0.3F) * limbSwingAmount;
			this.leg3.xRot = Mth.cos(ageInTicks+ (float) Math.PI) * limbSwingAmount;
			this.tail.yRot = ((float) Math.PI / 10F) * Mth.cos(ageInTicks) * limbSwingAmount;

			this.leg1.y = 25F;
			this.leg2.y = 25F;
			this.leg1.z = 3F;
			this.leg2.z = 3F;
			this.leg3.y = 22F;
			this.leg4.y = 22F;
			this.tail.y = 23.5F;
			this.head.y = 20F;
			this.head.z = -1F;
		}
	}

}