package de.moritzschmale.Showcase.Types;

import de.moritzschmale.Showcase.ShowcaseExtra;
import de.moritzschmale.Showcase.ShowcaseItem;
import de.moritzschmale.Showcase.ShowcasePlayer;

public class InfiniteShowcaseExtra implements ShowcaseExtra {

	private double price;
	private ShowcaseItem showcase = null;
	
	@Override
	public boolean onDestroy(ShowcasePlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(ShowcasePlayer player) {
		player.sendMessage("Price: "+price);
	}

	@Override
	public String save() {
		return String.valueOf(price);
	}

	@Override
	public void setShowcaseItem(ShowcaseItem item) {
		showcase = item;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

}