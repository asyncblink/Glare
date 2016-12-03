package com.gmail.mooman219.module.service.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.frame.text.Chat;
import com.gmail.mooman219.frame.time.TimeHelper;
import com.gmail.mooman219.frame.time.TimeType;
import com.gmail.mooman219.handler.database.CHDatabase;
import com.gmail.mooman219.handler.database.type.DownloadReason;
import com.gmail.mooman219.handler.task.CHTask;
import com.gmail.mooman219.module.service.CCService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Wipe extends CCommand {
    public Wipe() {
        super("wipe", Rank.CONSOLE, "/wipe", Carg.OPTIONAL);
    }

    @Override
    public void processPlayer(final Player sender, final CDPlayer player, final String[] args) {
        if(args.length == 0) {
            CHDatabase.getManager().dropPlayer();
            Bukkit.broadcastMessage("wiped");
        }
    }
}
