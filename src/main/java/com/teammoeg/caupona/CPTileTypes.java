package com.teammoeg.caupona;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;
import com.teammoeg.caupona.blocks.BowlTileEntity;
import com.teammoeg.caupona.blocks.others.CPSignTileEntity;
import com.teammoeg.caupona.blocks.pot.StewPotTileEntity;
import com.teammoeg.caupona.blocks.stove.KitchenStoveT1;
import com.teammoeg.caupona.blocks.stove.KitchenStoveT2;
import com.teammoeg.caupona.blocks.stove.KitchenStoveTileEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CPTileTypes {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, Main.MODID);

	public static final RegistryObject<BlockEntityType<StewPotTileEntity>> STEW_POT = REGISTER.register("stew_pot",
			makeType((p,s) -> new StewPotTileEntity(p,s), () -> CPBlocks.stew_pot));
	public static final RegistryObject<BlockEntityType<KitchenStoveTileEntity>> STOVE1 = REGISTER.register("kitchen_stove_basic",
			makeType((p,s) -> new KitchenStoveT1(p,s), () -> CPBlocks.stove1));
	public static final RegistryObject<BlockEntityType<KitchenStoveTileEntity>> STOVE2 = REGISTER.register("kitchen_stove_fast",
			makeTypes((p,s) -> new KitchenStoveT2(p,s), () -> new Block[]{CPBlocks.stove2,CPBlocks.stove3,CPBlocks.stove4,CPBlocks.stove5}));
	public static final RegistryObject<BlockEntityType<BowlTileEntity>> BOWL = REGISTER.register("bowl",
			makeType((p,s) -> new BowlTileEntity(p,s), () -> CPBlocks.bowl));
	public static final RegistryObject<BlockEntityType<CPSignTileEntity>> SIGN = REGISTER.register("sign",
			makeTypes((p,s) -> new CPSignTileEntity(p,s), () -> CPBlocks.signs.toArray(new Block[0])));
	
	private static <T extends BlockEntity> Supplier<BlockEntityType<T>> makeType(BlockEntitySupplier<T> create,
			Supplier<Block> valid) {
		return () -> new BlockEntityType<>(create,ImmutableSet.of(valid.get()), null);
	}
	private static <T extends BlockEntity> Supplier<BlockEntityType<T>> makeTypes(BlockEntitySupplier<T> create,
			Supplier<Block[]> valid) {
		return () -> new BlockEntityType<>(create,ImmutableSet.copyOf(valid.get()), null);
	}
}