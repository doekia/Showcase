package de.moritzschmale.Showcase;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShowcasePlayer {
	private String player;
	private ShowcaseType requestedType = ShowcaseType.NONE;
	private Block requestedBlock = null;
	private int dialogState = 0;
	private ItemStack requestedItem = null;
	private double requestedPrice = 0;
	private static Map<String,ShowcasePlayer> instances = new HashMap<String, ShowcasePlayer>();
	private ShowcasePlayer(String player){
		this.player = player;
	}
	
	public static ShowcasePlayer getPlayer(String name){
		if(instances.containsKey(name)){
			return instances.get(name);
		} else {
			ShowcasePlayer player = new ShowcasePlayer(name);
			instances.put(name, player);
			return player;
		}
	}
	
	public static ShowcasePlayer getPlayer(Player player){
		return getPlayer(player.getName());
	}
	
	public Player getPlayer(){
		return ShowcaseMain.instance.getServer().getPlayer(player);
	}
	
	public boolean hasPermission(String node, boolean adminMethod){
		return ShowcaseMain.hasPermission(getPlayer(), node, adminMethod);
	}

	/**
	 * @param requestedType the requestedType to set
	 */
	public void setRequestedType(ShowcaseType requestedType) {
		this.requestedType = requestedType;
	}

	/**
	 * @return the requestedType
	 */
	public ShowcaseType getRequestedType() {
		return requestedType;
	}

	/**
	 * @param requestedBlock the requestedBlock to set
	 */
	public void setRequestedBlock(Block requestedBlock) {
		this.requestedBlock = requestedBlock;
	}

	/**
	 * @return the requestedBlock
	 */
	public Block getRequestedBlock() {
		return requestedBlock;
	}

	/**
	 * @param dialogState the dialogState to set
	 */
	public void setDialogState(int dialogState) {
		this.dialogState = dialogState;
	}

	/**
	 * @return the dialogState
	 */
	public int getDialogState() {
		return dialogState;
	}
	
	public void resetDialog(){
		dialogState = 0;
		requestedBlock = null;
		requestedType = ShowcaseType.NONE;
		requestedItem = null;
		requestedPrice = 0;
	}
	
	public void sendMessage(String message){
		for(String line:message.split("\n")){
			getPlayer().sendMessage(line);
		}
	}

	/**
	 * @param requestedItem the requestedItem to set
	 */
	public void setRequestedItem(ItemStack requestedItem) {
		this.requestedItem = requestedItem;
	}

	/**
	 * @return the requestedItem
	 */
	public ItemStack getRequestedItem() {
		return requestedItem;
	}

	/**
	 * @param requestedPrice the requestedPrice to set
	 */
	public void setRequestedPrice(double requestedPrice) {
		this.requestedPrice = requestedPrice;
	}

	/**
	 * @return the requestedPrice
	 */
	public double getRequestedPrice() {
		return requestedPrice;
	}
	
	public int getAmountOfType(Material mat, short data){
		Inventory inv = getPlayer().getInventory();
		int ret = 0;
		for(int i = 0; i<inv.getSize();i++){
			ItemStack stack = inv.getItem(i);
			if(stack.getType().equals(mat)&&stack.getDurability()==data){
				ret+=stack.getAmount();
			}
		}
		return ret;
	}

	public void remove(Material mat, short data, int amount) {
		Inventory inv = getPlayer().getInventory();
		for(int i = 0; i<inv.getSize();i++){
			ItemStack stack = inv.getItem(i);
			if(stack.getType().equals(mat)&&stack.getDurability()==data){
				if(stack.getAmount()>=amount){
					stack.setAmount(stack.getAmount()-amount);
					inv.setItem(i, stack);
					return;
				} else {
					amount-=stack.getAmount();
					inv.setItem(i, null);
				}
			}
		}
	}
}
