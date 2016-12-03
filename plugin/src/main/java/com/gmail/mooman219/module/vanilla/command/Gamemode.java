package com.gmail.mooman219.module.vanilla.command;

import com.gmail.mooman219.bull.CDPlayer;
import com.gmail.mooman219.frame.command.CCommand;
import com.gmail.mooman219.frame.rank.Rank;
import com.gmail.mooman219.module.vanilla.CCVanilla;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Gamemode extends CCommand {
    public Gamemode() {
        super("gamemode", Rank.ADMINISTRATOR, "/Gamemode");
    }

    @Override
    public void processPlayer(Player sender, CDPlayer playerData, String[] args) {
        sender.setGameMode(sender.getGameMode() == GameMode.CREATIVE ? GameMode.ADVENTURE : GameMode.CREATIVE);
        CCVanilla.FRM.GAMEMODE.send(sender, sender.getGameMode().toString());
    }
}
