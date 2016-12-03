package com.gmail.mooman219.module.chat.listener;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.module.service.api.DataCreateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenerData implements Listener{
    @EventHandler()
    public void onCreation(DataCreateEvent event) {
        CDPlayer player = event.getPlayer();
        player.setDisplayName(player.service().rank.color + player.getUsername());
    }
}
