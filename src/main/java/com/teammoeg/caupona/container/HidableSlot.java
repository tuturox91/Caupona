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

package com.teammoeg.caupona.container;

import java.util.function.Supplier;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class HidableSlot extends SlotItemHandler {
	Supplier<Boolean> vs;

	public HidableSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Supplier<Boolean> visible) {
		super(itemHandler, index, xPosition, yPosition);
		vs = visible;
	}

	@Override
	public boolean isActive() {
		return vs.get();
	}

	@Override
	public boolean mayPickup(Player playerIn) {
		return vs.get();
	}

}