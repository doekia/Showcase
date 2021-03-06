/*
 * Copyright (C) 2011 Moritz Schmale <narrow.m@gmail.com>
 *
 * Showcase is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */

package com.narrowtux.showcase;

import com.narrowtux.dropchest.api.DropChestSuckEvent;

public class DropChestListener extends com.narrowtux.dropchest.api.DropChestListener {
	@Override
	public void onDropChestSuck(DropChestSuckEvent event) {
		if (Showcase.instance.isShowcaseItem(event.getItem())) {
			event.setCancelled(true);
		}
	}
}
