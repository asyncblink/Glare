package com.gmail.mooman219.bull;

import com.gmail.mooman219.core.Loader;
import com.gmail.mooman219.module.service.CCService;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.HashMap;
import java.util.UUID;

public class CDManager implements Listener {

    private static HashMap<UUID, CDPlayer> players = new HashMap<>();


    public static CDPlayer getSafe(Player player) {
        if(player == null) {
            return null;
        }
        return !players.containsKey(player.getUniqueId()) ? null : players.get(player.getUniqueId());
    }

    public static CDPlayer get(Player player) {
        CDPlayer ret = getSafe(player);
        if(ret == null) {
            CCService.MSG.DATAERROR.kick(player);
            Loader.warning("Invalid data on player '" + player.getName() + "'.");
        }
        return ret;
    }

    public static void set(AsyncPlayerPreLoginEvent event, CDPlayer cplayer) {
        if (event.getLoginResult() != null) {
            players.put(event.getUniqueId(), new CDPlayer(event.getName()));
/*            Player player = Bukkit.getPlayer(event.getUniqueId());
            EntityPlayer handle = ((CraftPlayer)player).getHandle();
            handle.livePlayer = cplayer;*/
        } else {
            throw new IllegalArgumentException("Unable to bind CDPlayer to login for '" + cplayer.getUsername() + "'.");
        }
    }

}
