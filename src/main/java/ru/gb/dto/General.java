package ru.gb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class General {
    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("feels_like")
    private Double feelsLike;

    public General() {

    }

    public General(Double temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
