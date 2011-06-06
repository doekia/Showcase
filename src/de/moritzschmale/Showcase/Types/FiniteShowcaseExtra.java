package de.moritzschmale.Showcase.Types;

import de.moritzschmale.Showcase.ShowcaseExtra;
import de.moritzschmale.Showcase.ShowcasePlayer;

public class FiniteShowcaseExtra implements ShowcaseExtra {
	private int itemAmount = 0;
	private double pricePerItem = 0.0D;
	@Override
	public boolean onDestroy(ShowcasePlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onClick(ShowcasePlayer player) {
		player.sendMessage(itemAmount+"x "+pricePerItem+"$");
	}

	@Override
	public String save() {
		return itemAmount+";"+pricePerItem;
	}

	/**
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}

	/**
	 * @return the itemAmount
	 */
	public int getItemAmount() {
		return itemAmount;
	}

	/**
	 * @param pricePerItem the pricePerItem to set
	 */
	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	/**
	 * @return the pricePerItem
	 */
	public double getPricePerItem() {
		return pricePerItem;
	}

}