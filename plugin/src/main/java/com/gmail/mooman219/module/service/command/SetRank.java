package com.gmail.mooman219.module.service.command;

import com.gmail.mooman219.bull.CDManager;
import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.frame.text.Chat;
import com.gmail.mooman219.handler.database.CHDatabase;
import com.gmail.mooman219.handler.database.type.DownloadReason;
import com.gmail.mooman219.handler.task.CHTask;
import com.gmail.mooman219.module.region.CCRegion;
import com.gmail.mooman219.module.region.RegionManager;
import com.gmail.mooman219.module.region.store.FastRegion;
import com.gmail.mooman219.module.service.CCService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetRank extends CCommand {
    public SetRank() {
        super("setrank", Rank.ADMINISTRATOR, "/Setrank (Player) (Rank)", Carg.STRING);
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
        FastRegion region = RegionManager.getRegion(args[0]);
        if(region != null) {
            RegionManager.setRegion(sender.getLocation(), region);
            CCRegion.FRM.MODIFIED.send(sender, region.getID());
        } else {
            CCRegion.MSG.NONEXISTS.send(sender);
        }
    }
}
