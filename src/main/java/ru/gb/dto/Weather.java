package ru.gb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.gb.WeatherResponse;

public class Weather {
    @JsonProperty("description")
    private String description;

    public Weather() {

    }
    public Weather(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
