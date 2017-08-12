package net.xalcon.chococraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.xalcon.chococraft.utils.registration.IItemBlockProvider;

public class BlockStrawNest extends Block implements IItemBlockProvider
{
    public final static AxisAlignedBB BOUNDS_EMPTY = new AxisAlignedBB(0, 0, 0, 1, .1875, 1);
    public final static PropertyBool HAS_EGG = PropertyBool.create("egg");

    public BlockStrawNest()
    {
        super(Material.ROCK);
    }

    @Override @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDS_EMPTY;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, HAS_EGG);
    }

    @Override @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HAS_EGG, (meta & 0b0001) == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(HAS_EGG) ? 1 : 0;
    }

    @Override
    public void registerItemModel(Item item)
    {
        ResourceLocation rl = item.getRegistryName();
        assert rl != null;
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(rl, "egg=false"));
    }
}