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

package com.teammoeg.caupona.items;

import com.teammoeg.caupona.Main;
import com.teammoeg.caupona.container.PortableBrazierContainer;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class PortableBrazierItem extends CPItem implements MenuProvider {

	public PortableBrazierItem(String name, Properties properties) {
		super(name, properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		InteractionResultHolder<ItemStack> ret = super.use(pLevel, pPlayer, pUsedHand);
		if (ret.getResult().consumesAction())
			return ret;
		if (!pPlayer.isShiftKeyDown()) {
			if (pPlayer instanceof ServerPlayer) {
				NetworkHooks.openGui((ServerPlayer) pPlayer, this);
			}
			return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), pLevel.isClientSide);
		}
		return ret;
	}

	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
		return new PortableBrazierContainer(pContainerId, pInventory);
	}

	@Override
	public Component getDisplayName() {
		return new TranslatableComponent("container." + Main.MODID + ".portable_brazier.title");
	}

}
