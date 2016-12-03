package com.gmail.mooman219.module.chat.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.frame.text.TextHelper;
import org.bukkit.entity.Player;

public class Message extends CCommand {
    public Message() {
        super("message", Rank.REGULAR, "/Message (Player) (Message)", Carg.STRING, Carg.MESSAGE);
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
        playerData.chat("@" + args[0] + TextHelper.merge(args, 1));
    }
}
