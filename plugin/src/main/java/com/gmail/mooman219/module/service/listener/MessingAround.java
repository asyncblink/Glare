package com.gmail.mooman219.module.service.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class MessingAround implements Listener {
    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        event.getProjectile().setVelocity(event.getProjectile().getVelocity().multiply(1.5));
    }
/*
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        CDPlayer playerData = CDPlayer.get(event.getPlayer());
        playerData.getSidebar().addKey("po3", Chat.YELLOW +"»" + Chat.GOLD + " Stats", 10);
        playerData.getSidebar().addKey("mp", Chat.DARK_AQUA + "" + Chat.BOLD + "MP" + Chat.DARK_AQUA + " 1234567", 8);
        playerData.getSidebar().addKey("po2", Chat.YELLOW +"»" + Chat.GOLD + " Region", 7);
        playerData.getSidebar().addKey("po1", Chat.YELLOW +"»" + Chat.GOLD + " Other", 4);
        playerData.getSidebar().addKey("memory", "Memory: Init", 3);
        playerData.getSidebar().addKey("tps", "TPS: Init", 2);
        playerData.getSidebar().addKey("mob", "Mobs: Init", 1);

        playerData.getTab().set(0, 0, new TextTabItem(Chat.DARK_AQUA + "" + Chat.BOLD + "Welcome:"));
        playerData.getTab().set(1, 0, new TextTabItem(Chat.GOLD + "" + Chat.BOLD + "Gold:");
        playerData.getTab().set(2, 0, new TextTabItem("Online:");
        playerData.getTab().set(3, 0, new TextTabItem(Chat.DARK_GREEN + "" + Chat.BOLD + "C" + Chat.GOLD + "" + Chat.BOLD + "RPG");

        playerData.getTab().update();
    }

    @EventHandler
    public void onSecond(TickSecondSyncEvent event){
        double memUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
        double tps = Math.min(20,  Math.round(MinecraftServer.currentTPS * 10D) / 10.0D);
        for(Player player : Bukkit.getOnlinePlayers()) {
            int mobs = player.getWorld().getEntities().size();
            CDPlayer.get(player).getSidebar().modifyName("memory", "Memory: " + Chat.GREEN + (int)memUsed);
            CDPlayer.get(player).getSidebar().modifyName("tps", "TPS: " + Chat.GREEN + tps);
            CDPlayer.get(player).getSidebar().modifyName("mob", "Mobs: " + Chat.GREEN + mobs);
        }
    }*/

    // Old research

    /**
    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        playerData.player.setAllowFlight(true);
    }

    @EventHandler()
    public void onSneak(PlayerToggleSneakEvent event) {
        PlayerData playerData = PlayerManager.getPlayerData(event.getPlayer());
        if(!playerData.service.hasLanded) {
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(0).setY(-1));
        }
    }

    @EventHandler()
    public void onFlight(PlayerToggleFlightEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            PlayerData playerData = PlayerManager.getPlayerData(event.getPlayer());
            event.setCancelled(true);
            if(playerData.service.hasLanded) {
                playerData.service.hasLanded = false;
                playerData.service.scoreboard.modifyKeyName("onground", "On Ground:" + Chat.RED + " No");
                event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().setY(0.0).multiply(0.44).setY(0.6));
                //event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.2).add(new Vector(0f,.2f,0f)));
                WorldHelper.playEffect(playerData.player.getLocation(), Effect.STEP_SOUND, 49);
            } else {
                event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(0).setY(-2));
            }
        }
    }

    @EventHandler(ignoreCancelled = false)
    public void onMove(PlayerMoveEvent event) {
        PlayerData playerData = PlayerManager.getPlayerData(event.getPlayer());
        double yVelocity = playerData.player.getVelocity().getY();
        playerData.service.scoreboard.modifyKeyName("yv", "YV: " + (int)(yVelocity * 100));
        if(!playerData.service.hasLanded) {
            if(yVelocity < 0 && yVelocity > -0.08f && isGroundBelow(playerData.player.getLocation().clone())) {
                playerData.service.scoreboard.modifyKeyName("onground", "On Ground:" + Chat.GREEN + " Yes");
                playerData.service.hasLanded = true;
            }
        }
    }

    public boolean isGroundBelow(Location origin) {
        Location loc = origin.clone();
        if(loc.getBlock().getTypeId() != 0 || loc.subtract(0, 1, 0).getBlock().getTypeId() != 0 || loc.subtract(0, 1, 0).getBlock().getTypeId() != 0) {
            return true;
        } else {
            loc = origin.clone();
            loc.setX((loc.getX() < loc.getBlockX() + 0.5d) ? loc.getBlockX() - 1 : loc.getBlockX());
            loc.setZ((loc.getZ() < loc.getBlockZ() + 0.5d) ? loc.getBlockZ() - 1 : loc.getBlockZ());
            return (loc.getBlock().getTypeId() != 0 || loc.subtract(0, 1, 0).getBlock().getTypeId() != 0 || loc.subtract(0, 1, 0).getBlock().getTypeId() != 0);
        }
    }
    /**/
}
