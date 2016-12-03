package com.gmail.mooman219.module.chat.listener;

import com.gmail.mooman219.bull.CDManager;
import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.module.chat.CCChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerPlayer implements Listener{
    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        CDPlayer player = CDManager.get(event.getPlayer());
        event.setJoinMessage(CCChat.FRM.LOGIN.parse(player.getUsername()));
//        player.setOverheadPrefix(player.service().rank.color);
    }

    @EventHandler()
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage(CCChat.FRM.KICK.parse(event.getPlayer().getName()));
    }

    @EventHandler()
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(CCChat.FRM.QUIT.parse(event.getPlayer().getName()));
    }

    @EventHandler()
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(CCChat.FRM.DEATH.parse(event.getEntity().getName()));
    }
}
