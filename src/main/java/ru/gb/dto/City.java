package ru.gb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    @JsonProperty("name")
    private static String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
