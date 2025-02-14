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

package com.teammoeg.caupona.blocks;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class CPHorizontalTileBlock<V extends BlockEntity> extends CPHorizontalBlock implements CPTileBlock<V> {
	private final RegistryObject<BlockEntityType<V>> te;

	public CPHorizontalTileBlock(RegistryObject<BlockEntityType<V>> te, Properties p_54120_) {
		super(p_54120_);
		this.te = te;
	}

	@Override
	public RegistryObject<BlockEntityType<V>> getTile() {
		return te;
	}

}
