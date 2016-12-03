package com.gmail.mooman219.frame.rank;

import com.gmail.mooman219.frame.text.Chat;

public enum Rank {
    /**
     * Using 10's for donor/staff levels just in case
     * more ranks need to be added and you cannot edit
     * the database.
     */
    //
    REGULAR(0, 0, 0, Chat.GRAY, ""),
    //
    TRIAL(1, 1, 0, Chat.GRAY, ""),
    BETA(2, 2, 0, Chat.GRAY, Chat.DARK_GREEN + "*"),
    //
    PREMIUM_1(3, 3, 0, Chat.GRAY, Chat.GREEN + "P"),
    PREMIUM_2(4, 4, 0, Chat.GRAY, Chat.YELLOW + "P"),
    PREMIUM_3(5, 5, 0, Chat.GRAY, Chat.AQUA + "*" + Chat.BOLD + "P"),
    PREMIUM_4(7, 6, 0, Chat.WHITE, Chat.DARK_AQUA + "**" + Chat.BOLD + "P"),
    PREMIUM_5(8, 7, 0, Chat.WHITE, Chat.PURPLE + "***" + Chat.BOLD + "P"),
    //
    BUILDER(9, 0, 10, Chat.WHITE, "" + Chat.PINK + Chat.BOLD + "BLDR"),
    MODERATOR(10, 0, 20, Chat.WHITE, "" + Chat.WHITE + Chat.BOLD + "MOD"),
    ADMINISTRATOR(11, 0, 30, Chat.GOLD, "" + Chat.RED + Chat.BOLD + "ADMIN"),
    OPERATOR(12, 0, 40, Chat.GOLD, "" + Chat.DARK_RED + Chat.BOLD + "OP"),
    CONSOLE(100, 0, 0, "" + Chat.BLUE + Chat.BOLD, "CONSOLE");

    public final String color;
    public final String tag;
    public final int index;
    public final int donorLevel;
    public final int staffLevel;

    Rank(int index, int donorLevel, int staffLevel, Chat color, String tag) {
        this(index, donorLevel, staffLevel, "" + color, tag);
    }

    Rank(int index, int donorLevel, int staffLevel, String color, String tag) {
        this.index = index;
        this.color = color;
        this.donorLevel = donorLevel;
        this.staffLevel = staffLevel;
        this.tag = tag;
    }


    public static Rank getRank(int index) {
        if(index >= Rank.values().length){
            return Rank.REGULAR;
        }
        return Rank.values()[index];
    }

    public static Rank getRank(int donorRank, int staffRank){
        if(staffRank != 0) {
            return getRankFromStaff(staffRank);
        }
        return getRankFromDonor(donorRank);
    }

    public static Rank getRankFromDonor(int level) {
        for (Rank r : Rank.values()) {
            if (r.donorLevel == level) {
                return r;
            }
        }
        return Rank.REGULAR;
    }

    public static Rank getRankFromStaff(int level) {
        for (Rank r : Rank.values()) {
            if (r.staffLevel == level) {
                return r;
            }
        }
        return Rank.REGULAR;
    }
}
