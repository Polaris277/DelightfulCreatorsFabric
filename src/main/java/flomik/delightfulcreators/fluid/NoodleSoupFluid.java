package flomik.delightfulcreators.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.WorldView;

public abstract class NoodleSoupFluid extends ModFluidsTemplate {

    @Override
    public Fluid getStill() {
        return ModFluidsRegister.STILL_NOODLE_SOUP;
    }

    @Override
    public Fluid getFlowing() {
        return ModFluidsRegister.FLOWING_NOODLE_SOUP;
    }

    @Override
    public Item getBucketItem() {
        return ModFluidsRegister.NOODLE_SOUP_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModFluidsRegister.NOODLE_SOUP_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 2;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 30;
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    public static class Flowing extends NoodleSoupFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

    }

    public static class Still extends NoodleSoupFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
