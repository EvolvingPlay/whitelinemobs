package com.whiteline.wlmobs.content.blocks;

import com.google.common.collect.Lists;
import com.whiteline.wlmobs.init.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Random;

public class CheeseBlock extends Block {
    public static final IntegerProperty STATE = IntegerProperty.create("state",0,3);

    private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D,1.0D , 15.0D, 8.0D, 15.0D);

    public CheeseBlock(BlockBehaviour.Properties properties){
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STATE,0));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(STATE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext colContext) {
        return SHAPE;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(level.isClientSide){
            if(eat(level,pos,state,player).consumesAction()){
                return InteractionResult.SUCCESS;
            }
        }
        return eat(level,pos,state,player);
    }

    protected static InteractionResult eat(LevelAccessor accessor, BlockPos pos, BlockState state, Player player){
        if(!player.canEat(false)){
            return InteractionResult.PASS;
        }else {
            player.getFoodData().eat(2,0.1F);
            Random rnd = new Random();

            if(player.getActiveEffects().toArray().length > 0) {
                int i = rnd.nextInt(player.getActiveEffects().toArray().length);
                int j = 0;

                boolean rm = false;
                MobEffectInstance instance = null;

                if (!accessor.isClientSide()) {
                    for (MobEffectInstance effect : player.getActiveEffects()) {
                        instance = effect;
                        if (i == j) {
                            if (rnd.nextInt(10) == 1) {
                                player.removeEffect(effect.getEffect());
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
                        player.removeEffect(instance.getEffect());
                    }else
                        player.removeEffect(instance.getEffect());
                        player.addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration() - 1000, instance.getAmplifier()));
                }
            }
            int i = state.getValue(STATE);
            accessor.gameEvent(player, GameEvent.EAT, pos);
            if(i < 3){
                accessor.setBlock(pos,state.setValue(STATE,Integer.valueOf(i+1)),3);
            }else {
                accessor.removeBlock(pos,false);
                accessor.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    public BlockState updateShape(BlockState state_1, Direction direction, BlockState state_2, LevelAccessor accessor, BlockPos pos_1, BlockPos pos_2) {
        return direction == Direction.DOWN && !state_1.canSurvive(accessor, pos_1) ? Blocks.AIR.defaultBlockState() : super.updateShape(state_1, direction, state_2, accessor, pos_1, pos_2);
    }

    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return levelReader.getBlockState(pos.below()).getMaterial().isSolid();
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getOutputSignal(state.getValue(STATE));
    }

    public static int getOutputSignal(int input) {
        return (3 - input) * 4;
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public boolean isPathfindable(BlockState p_51193_, BlockGetter p_51194_, BlockPos p_51195_, PathComputationType p_51196_) {
        return false;
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = Lists.newArrayList();
        list.add(new ItemStack(Registry.CHEESE.get(),4-state.getValue(STATE)));
        return list;
    }
}
