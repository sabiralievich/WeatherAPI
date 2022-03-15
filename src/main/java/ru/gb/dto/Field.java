package ru.gb.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
    @JsonProperty("main")
    private General main;

    @JsonProperty("weather")
    private Weather[] weather;

    @JsonProperty("dt")
    private String date;


    public Field() {

    }

    public Field(General main, Weather[] weather) {
        this.main = main;
        this.weather = weather;
    }


    public String getDate() throws ParseException {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public General getMain() {
        return main;
    }

    public void setMain(General main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
}
