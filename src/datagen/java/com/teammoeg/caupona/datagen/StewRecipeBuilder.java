/*
 * Copyright (c) 2022 TeamMoeg
 *
 * This file is part of Caupona.
 *
 * Caupona is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Caupona is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Caupona. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.caupona.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.teammoeg.caupona.Main;
import com.teammoeg.caupona.data.recipes.IngredientCondition;
import com.teammoeg.caupona.data.recipes.StewBaseCondition;
import com.teammoeg.caupona.data.recipes.StewCookingRecipe;
import com.teammoeg.caupona.data.recipes.baseconditions.FluidTag;
import com.teammoeg.caupona.data.recipes.baseconditions.FluidType;
import com.teammoeg.caupona.data.recipes.baseconditions.FluidTypeType;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;

public class StewRecipeBuilder {
	public static class StewBaseBuilder {
		private StewRecipeBuilder parent;

		public StewBaseBuilder(StewRecipeBuilder parent) {
			super();
			this.parent = parent;
		}

		public StewBaseBuilder tag(ResourceLocation rl) {
			parent.base.add(new FluidTag(rl));
			return this;
		}

		public StewBaseBuilder type(Fluid f) {
			parent.base.add(new FluidType(f));
			return this;
		}

		public StewBaseBuilder only(Fluid f) {
			parent.base.add(new FluidTypeType(f));
			return this;
		}

		public StewRecipeBuilder and() {
			return parent;
		}
	}

	private List<IngredientCondition> allow = new ArrayList<>();
	private List<IngredientCondition> deny = new ArrayList<>();
	private int priority = 0;
	private int time = 200;
	private float density = 0.75f;
	private List<StewBaseCondition> base = new ArrayList<>();
	private Fluid output;
	private ResourceLocation id;

	public StewRecipeBuilder(ResourceLocation id, Fluid out) {
		output = out;
		this.id = id;
	}

	public static StewRecipeBuilder start(Fluid out) {
		return new StewRecipeBuilder(new ResourceLocation(Main.MODID, "cooking/" + out.getRegistryName().getPath()),
				out);
	}

	public IngredientConditionsBuilder<StewRecipeBuilder> require() {
		return new IngredientConditionsBuilder<StewRecipeBuilder>(this, allow, allow, deny);
	}

	public IngredientConditionsBuilder<StewRecipeBuilder> not() {
		return new IngredientConditionsBuilder<StewRecipeBuilder>(this, deny, allow, deny);
	}

	public StewBaseBuilder base() {
		return new StewBaseBuilder(this);
	}

	public StewRecipeBuilder prio(int p) {
		priority = p;
		return this;
	}

	public StewRecipeBuilder special() {
		priority |= 1024;
		return this;
	}

	public StewRecipeBuilder high() {
		priority |= 128;
		return this;
	}

	public StewRecipeBuilder med() {
		priority |= 64;
		return this;
	}

	public StewRecipeBuilder low() {
		return this;
	}

	public StewRecipeBuilder time(int t) {
		time = t;
		return this;
	}

	public StewRecipeBuilder dense(double d) {
		density = (float) d;
		return this;
	}

	public StewCookingRecipe end() {
		return new StewCookingRecipe(id, allow, deny, priority, time, density, base, output);
	}

	public StewCookingRecipe finish(Consumer<? super StewCookingRecipe> csr) {
		StewCookingRecipe r = end();
		csr.accept(r);
		return r;
	}
}
