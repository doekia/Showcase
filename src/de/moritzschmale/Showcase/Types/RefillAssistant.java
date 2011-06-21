package de.moritzschmale.Showcase.Types;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.narrowtux.Assistant.Assistant;
import com.narrowtux.Assistant.AssistantPage;

import de.moritzschmale.Showcase.ShowcaseItem;
import de.moritzschmale.Showcase.ShowcaseMain;
import de.moritzschmale.Showcase.ShowcasePlayer;

public class RefillAssistant extends Assistant {
	public ShowcaseItem showcase;
	public FiniteShowcaseExtra extra;
	public RefillAssistant(Player p, final ShowcaseItem showcase) {
		super(p);
		this.showcase = showcase;
		extra = (FiniteShowcaseExtra)showcase.getExtra();
		
		AssistantPage page = new AssistantPage(this){
			@Override
			public boolean onPageInput(String text){
				int value = 0;
				try{
					value = Integer.valueOf(text);
				} catch(Exception e){
					value = 0;
				}
				if(value==0){
					return false;
				}
				ShowcasePlayer player = ShowcasePlayer.getPlayer(getPlayer());
				Material mat = showcase.getMaterial();
				short data = showcase.getData();
				int amountAtPlayer = player.getAmountOfType(mat, data);
				int amountAtShowcase = extra.getItemAmount();
				if(value>amountAtPlayer){
					value = amountAtPlayer;
				}
				if(value<0&&value*-1>amountAtShowcase){
					value = -amountAtShowcase;
				}
				if(value>0&&value<=amountAtPlayer){
					player.remove(mat, data, value);
					extra.setItemAmount(amountAtShowcase+value);
					sendMessage(formatLine(ChatColor.YELLOW.toString()+value+ChatColor.WHITE+" items added."));
				} else if(value<0&&value*-1<=amountAtShowcase){
					player.addItems(mat, data, -value);
					extra.setItemAmount(amountAtShowcase+value);
					sendMessage(formatLine(ChatColor.YELLOW.toString()+(-value)+ChatColor.WHITE+" items removed."));
				}
				updateText();
				addPage(this);
				return true;
			}
		};
		setTitle("Refill Assistant");
		page.setTitle("");
		addPage(page);
		updateText();
	}
	
	@Override
	public void onAssistantCancel(){
		sendMessage(formatLine("Refill done."));
	}

	@Override
	public void onAssistantFinish(){
		sendMessage(formatLine("Refill done."));
	}
	
	public void updateText(){
		ShowcasePlayer player = ShowcasePlayer.getPlayer(getPlayer());
		Material mat = showcase.getMaterial();
		short data = showcase.getData();
		String text = "";
		text+=ChatColor.YELLOW.toString()+extra.getItemAmount()+ChatColor.WHITE+" of "+ChatColor.YELLOW+ShowcaseMain.getName(mat, data)+ChatColor.WHITE+" in showcase.\n";
		text+="One item costs "+ChatColor.YELLOW+extra.getPricePerItem()+ChatColor.WHITE+".\n";
		text+="You have got "+ChatColor.YELLOW+player.getAmountOfType(mat, data)+ChatColor.WHITE+" items.\n";
		text+="Type in a "+ChatColor.YELLOW+"positive amount (e.g. 10)"+ChatColor.WHITE+" to add items.\n";
		text+="Type in a "+ChatColor.YELLOW+"negative amount (e.g. -6)"+ChatColor.WHITE+" to remove items.\n";
		text+="Type in "+ChatColor.YELLOW+"0 or go away"+ChatColor.WHITE+" to leave the assistant.";
		for(AssistantPage page:getPages()){
			page.setText(text);
		}
	}
}
