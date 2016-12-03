package com.gmail.mooman219.module.chat.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.frame.text.TextHelper;
import org.bukkit.entity.Player;

public class Reply extends CCommand {
    public Reply() {
        super("reply", Rank.REGULAR, "/Reply (Message)", Carg.MESSAGE);
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
        playerData.chat("@ " + TextHelper.merge(args, 0));
    }
}
