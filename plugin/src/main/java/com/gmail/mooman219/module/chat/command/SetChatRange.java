package com.gmail.mooman219.module.chat.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.command.Carg;
import com.gmail.mooman219.frame.math.NumberHelper;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.module.chat.CCChat;
import com.gmail.mooman219.module.chat.store.ConfigChat;
import org.bukkit.entity.Player;

public class SetChatRange extends CCommand {
    private final CCChat module;
    public SetChatRange(CCChat module) {
        super("setchatrange", Rank.OPERATOR, "/SetChatRange (Range)", Carg.INT);
        this.module = module;
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
        int oldRange = ConfigChat.getData().radius;
        ConfigChat.getData().radius = NumberHelper.toInt(Math.pow(Integer.parseInt(args[0]), 2));
        module.configChat.save();
        CCChat.FRM.SETRANGE.send(sender, oldRange, ConfigChat.getData().radius);

    }
}
