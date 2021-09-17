package com.whiteline.wlmobs.init;

import com.whiteline.wlmobs.WLMobs;
import com.whiteline.wlmobs.content.blocks.CheeseBlock;
import com.whiteline.wlmobs.content.entity.passive.Rat;
import com.whiteline.wlmobs.content.items.CheeseItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WLMobs.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WLMobs.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WLMobs.MOD_ID);

    public static FoodProperties CHEESE_FOOD = (new FoodProperties.Builder()).alwaysEat().nutrition(1).saturationMod(2).build();

    public static final RegistryObject<CheeseBlock> CHEESE_BLOCK = Registry.BLOCKS.register("cheese_block",() -> new CheeseBlock(BlockBehaviour.Properties.of(Material.CAKE).sound(SoundType.WOOL)));

    public static final RegistryObject<BlockItem> CHEESE_ITEM_BLOCK = Registry.ITEMS.register("cheese_block",() -> new BlockItem(CHEESE_BLOCK.get(),new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

    public static final RegistryObject<CheeseItem> CHEESE = Registry.ITEMS.register("cheese", () -> new CheeseItem(new Item.Properties().food(CHEESE_FOOD).tab(CreativeModeTab.TAB_FOOD)));

    //public static final RegistryObject<EntityType<Elephant>> ELEPHANT = Registry.ENTITIES.register("elephant", () -> EntityType.Builder.of(Elephant::new, MobCategory.CREATURE).sized(2.0F, 2.0F).build(WLMobs.MOD_ID+"elephant"));
    public static final RegistryObject<EntityType<Rat>> RAT = Registry.ENTITIES.register("rat", () -> EntityType.Builder.of(Rat::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build(WLMobs.MOD_ID+"rat"));

    public static void register(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
    }
}
