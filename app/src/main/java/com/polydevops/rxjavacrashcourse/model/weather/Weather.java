package com.polydevops.rxjavacrashcourse.model.weather;

/**
 * TODO: Add class header comment.
 */
public class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;

    public Weather(long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
