package com.teammoeg.caupona.worldgen;

import com.teammoeg.caupona.CPBlocks;
import com.teammoeg.caupona.Main;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class CPFeatures {
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> WALNUT = FeatureUtils.register(Main.MODID+":walnut",
			Feature.TREE, createStraightBlobTree(CPBlocks.WALNUT_LOG,CPBlocks.WALNUT_LEAVE,4,2,0,2).ignoreVines().build());
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FIG = FeatureUtils.register(Main.MODID+":fig",
			Feature.TREE, createStraightBlobBush(CPBlocks.FIG_LOG,CPBlocks.FIG_LEAVE,4,2,0,2).ignoreVines().build());
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> WOLFBERRY = FeatureUtils.register(Main.MODID+":wolfberry",
			Feature.TREE, createStraightBlobBush(CPBlocks.WOLFBERRY_LOG,CPBlocks.WOLFBERRY_LEAVE,4,2,0,2).ignoreVines().build());
	public static final FoliagePlacerType<BushFoliagePlacer> bfp=new FoliagePlacerType<>(BushFoliagePlacer.CODEC);
	static {
		bfp.setRegistryName(new ResourceLocation(Main.MODID,"bush_foliage_placer"));
	}
	public CPFeatures() {
	}
	public static void init() {
		
	};
	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leave,
			int height, int randA, int randB, int foliage) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new StraightTrunkPlacer(height, randA, randB), BlockStateProvider.simple(leave),
				new BlobFoliagePlacer(ConstantInt.of(foliage), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1));
	}
	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobBush(Block log, Block leave,
			int height, int randA, int randB, int foliage) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new StraightTrunkPlacer(height, randA, randB), BlockStateProvider.simple(leave),
				new BushFoliagePlacer(ConstantInt.of(foliage), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1));
	}
}
