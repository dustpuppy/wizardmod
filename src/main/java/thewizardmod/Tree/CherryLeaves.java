package thewizardmod.Tree;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CherryLeaves extends BlockLeaves
{
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    protected boolean leavesFancy;

    public CherryLeaves()
    {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    	}


    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    @Nullable

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(StartupCommon.blockCherrySapling);
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!worldIn.isRemote)
        {
            int i = this.getSaplingDropChance(state);

            if (fortune > 0)
            {
                i -= 2 << fortune;

                if (i < 10)
                {
                    i = 10;
                }
            }

            if (worldIn.rand.nextInt(i) == 0)
            {
                Item item = this.getItemDropped(state, worldIn.rand, fortune);
                spawnAsEntity(worldIn, pos, new ItemStack(item, 1, this.damageDropped(state)));
            }

            i = this.getFruitDropChance();

            if (fortune > 0)
            {
                i -= 10 << fortune;

                if (i < 40)
                {
                    i = 40;
                }
            }

            this.dropFruit(worldIn, pos, state, i);
        }
    }

    protected void dropFruit(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
    	int i = new Random().nextInt(chance);
    	if (i == 0){
    		spawnAsEntity(worldIn, pos, new ItemStack(thewizardmod.items.StartupCommon.cherry));
    	}
    }

    protected int getFruitDropChance()
    {
        return 80;
    }

    protected int getSaplingDropChance(IBlockState state)
    {
        return 20;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
    	return Blocks.LEAVES.isOpaqueCube(state);
    }

    public BlockRenderLayer getBlockLayer()
    {
    	return Blocks.LEAVES.getBlockLayer();
    }

    public boolean isVisuallyOpaque()
    {
        return false;
    }


    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
		setGraphicsLevel(!isOpaqueCube(blockState));
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

    @Override public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return true; }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DECAYABLE, (meta & 0x4) == 0).withProperty(CHECK_DECAY, (meta & 0x8) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0 ;

		if (!state.getValue(DECAYABLE).booleanValue()) {
			i |= 4;
		}

		if (state.getValue(CHECK_DECAY).booleanValue()) {
			i |= 8;
		}
		
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CHECK_DECAY, DECAYABLE });
	}


	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, 0)));
	}


	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}




}
