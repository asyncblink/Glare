package com.gmail.mooman219.layout;

public enum ModuleType {
    BUFF(0, "Buff", "plugins/Glare/"),
    CHAT(1, "Chat", "plugins/Glare/"),
    DAMAGE(2, "Damage", "plugins/Glare/"),
    DISCIPLINE(3, "Discipline", "plugins/Glare/"),
    GRAVEYARD(4, "Graveyard", "plugins/Glare/"),
    ITEM(5, "Item", "plugins/Glare/"),
    LOGIN(6, "Login", "plugins/Glare/"),
    MINERAL(7, "Mineral", "plugins/Glare/"),
    MOUNT(8, "Mount", "plugins/Glare/"),
    PET(9, "Pet", "plugins/Glare/"),
    REGION(10, "Region", "plugins/Glare/"),
    SERVICE(11, "Service", "plugins/Glare/"),
    SHOP(12, "Shop", "plugins/Glare/"),
    STAT(13, "Stat", "plugins/Glare/"),
    VANILLA(14, "Vanilla", "plugins/Glare/"),
    WORLD(15, "World", "plugins/Glare/"),
    SCOREBOARD(16, "Scoreboard", "plugins/Glare");

    private final int id;
    private final String name;
    private final String directory;

    ModuleType(int id, String name, String directory) {
        this.id = id;
        this.name = name;
        this.directory = directory;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getCast() {
        return "[" + name + "] ";
    }
}
