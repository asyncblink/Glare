package com.gmail.mooman219.layout;

public enum HandlerType {
    CONFIG(0, "Config", "plugins/Glare/"),
    DATABASE(1, "Database", "plugins/Glare/"),
    TASK(2, "Task", "plugins/Glare/"),
    MYSQL(3, "mySQL", "plugins/Glare/");

    private final int id;
    private final String name;
    private final String directory;

    HandlerType(int id, String name, String directory) {
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
