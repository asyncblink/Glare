package com.gmail.mooman219.module.stat.store;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.database.MongoHelper;
import com.gmail.mooman219.handler.database.type.DownloadReason;
import com.gmail.mooman219.handler.database.type.UploadReason;
import com.gmail.mooman219.layout.PlayerData;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PDStat extends PlayerData {
    public PDStat(CDPlayer player) {
        super(player, "stat");
    }

    /**
     * Offline
     */

    public int level = 1;
    public int exp = 0;


    @Override
    public void load(DownloadReason reason, DBObject stat) {
        switch(reason) {
        case LOGIN:
        case QUERY:
        default:
            this.level = MongoHelper.getValue(stat, "level", level);
            this.exp = MongoHelper.getValue(stat, "exp", exp);
            break;
        }
    }

    @Override
    public DBObject save(UploadReason reason) {
        switch(reason) {
        case SAVE:
            return new BasicDBObject()
            .append(getTag() + ".level", level)
            .append(getTag() + ".exp", exp);
        case STATUS:
        default:
            return new BasicDBObject();
        }
    }

    /**
     * Live
     */

    private long lastDamaged = 0l;

    public void setLastDamaged(long time) {
        lastDamaged = time;
    }

    public long getLastDamaged() {
        return lastDamaged;
    }

    @Override
    public void create() {
        lastDamaged = 0l;
    }

    @Override
    public void destroy() {
        lastDamaged = 0l;
    }
}
