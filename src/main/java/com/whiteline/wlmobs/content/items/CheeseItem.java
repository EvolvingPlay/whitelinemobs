package com.whiteline.wlmobs.content.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CheeseItem extends Item {
    public CheeseItem(Item.Properties properties){
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(stack,level,entity);

        Random rnd = new Random();

        if(entity.getActiveEffects().toArray().length > 0) {
            int i = rnd.nextInt(entity.getActiveEffects().toArray().length);
            int j = 0;

            boolean rm = false;
            MobEffectInstance instance = null;

            if (!level.isClientSide()) {
                for (MobEffectInstance effect : entity.getActiveEffects()) {
                    instance = effect;
                    if (i == j) {
                        if (rnd.nextInt(10) == 1) {
                            entity.removeEffect(effect.getEffect());
                            rm = true;
                            break;
                        } else {
                            rm = false;
                            break;
                        }
                    }
                    j++;
                }

                if(rm){
                    entity.removeEffect(instance.getEffect());
                }else
                    entity.removeEffect(instance.getEffect());
                    entity.addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration()/2, instance.getAmplifier()));
            }
        }

        return itemstack;
    }
}
