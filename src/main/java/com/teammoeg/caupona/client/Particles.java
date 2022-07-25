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

package com.teammoeg.caupona.client;

import com.teammoeg.caupona.Main;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Particles {
	public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, Main.MODID);

	public static final RegistryObject<SimpleParticleType> STEAM = REGISTER.register("steam",
			() -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SOOT = REGISTER.register("soot_smoke",
			() -> new SimpleParticleType(false));
}
