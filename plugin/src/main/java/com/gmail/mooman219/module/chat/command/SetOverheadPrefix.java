package com.gmail.mooman219.module.chat.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.module.chat.CCChat;
import org.bukkit.entity.Player;

public class SetOverheadPrefix extends CCommand {
    public SetOverheadPrefix() {
        super("setoverheadprefix", Rank.ADMINISTRATOR, "/SetOverheadPrefix (prefix)", Carg.STRING);
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
//        playerData.setOverheadPrefix(args[0]);
//        CCChat.FRM.SETOVERHEAD.send(sender, playerData.getOverheadPrefix() + playerData.getUsername() + playerData.getOverheadSuffix());
    }
}
