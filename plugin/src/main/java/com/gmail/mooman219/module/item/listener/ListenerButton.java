package com.gmail.mooman219.module.item.listener;

import com.gmail.mooman219.module.item.api.ItemHelper;
import com.gmail.mooman219.module.item.api.event.ButtonClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenerButton implements Listener {
    @EventHandler()
    public void onClick(ButtonClickEvent event) {
        if(ItemHelper.getItemMeta(event.getButton()).getDisplayName().contains("Equipment")) {
            event.getClicker().openInventory(event.getClicker().item().getEquipment());
        }
    }
}