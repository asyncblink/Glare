package com.gmail.mooman219.handler.database;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.core.Loader;
import com.gmail.mooman219.handler.database.type.DownloadReason;
import com.gmail.mooman219.handler.database.type.UploadReason;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.Callable;

class PlayerDownloader implements Callable<CDPlayer> {
    public final UUID playerUUID;
    public final DownloadReason reason;

    public PlayerDownloader(UUID playerUUID, DownloadReason reason) {
        this.playerUUID = playerUUID;
        this.reason = reason;
    }

    @Override
    public CDPlayer call() {
        try {
            CDPlayer player;
            DBObject playerObject;
            switch(reason) {
            case LOGIN:
                player = new CDPlayer(playerUUID);
                playerObject = findPlayerObject(playerUUID, true);
                Loader.info(CHDatabase.getCast() + "[DOWN] ["+reason.name()+"] [" + (playerObject != null ? "FOUND" : "NULL") + "] : " + Bukkit.getPlayer(playerUUID).getName() );
                if(playerObject == null) {
                    createPlayerObject(playerUUID);
                    CHDatabase.getManager().uploadPlayer(player, UploadReason.SAVE, false, false);
                }
                if(player.load(reason, playerObject)) {
                    Loader.warning(CHDatabase.getCast() + "[DOWN] Unable to load playerdata for '" + player.getUsername() + "'");
                    return null;
                }
                return player;
            case QUERY:
                playerObject = findPlayerObject(playerUUID, false);
                Loader.info(CHDatabase.getCast() + "[DOWN] ["+reason.name()+"] [" + (playerObject != null ? "FOUND" : "NULL") + "] : " + playerUUID);
                if(playerObject != null) {
                    player = new CDPlayer(playerUUID);
                    player.load(reason, playerObject);
                    return player;
                } else {
                    return null;
                }
            default:
                return null;
            }
        } catch(Exception e) {
            Loader.warning("Something has gone wrong during " + username + "'s download request.");
            Loader.warning(CHDatabase.getCast() + "Currently" + (CHDatabase.getManager().isConnected() ? " " : " not ") + "connected to database.");
            e.printStackTrace();
        }
        return null;
    }

    public DBObject findPlayerObject(UUID playerUUID, boolean caseSensitive) {
        BasicDBObject query = caseSensitive ? new BasicDBObject("uuid", playerUUID) : new BasicDBObject("username", Bukkit.getPlayer(playerUUID).getName().toLowerCase());
        return CHDatabase.getManager().getUsers().findOne(query);
    }

    public DBObject createPlayerObject(UUID playerUUID) {
        BasicDBObject playerObject = new BasicDBObject()
        .append("uuid", username)
        .append("username", );
        CHDatabase.getManager().getUsers().insert(playerObject);
        return playerObject;
    }
}
