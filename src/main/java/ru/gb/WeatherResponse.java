package ru.gb;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.gb.dto.City;
import ru.gb.dto.Field;


public class WeatherResponse {
    @JsonProperty("city")
    private City city;
    @JsonProperty("list")
    private Field[] list;
    @JsonProperty("cnt")
    private int cnt;



    public WeatherResponse() {

    }
    public WeatherResponse(City name, Field[] list) {
        this.city = name;
        this.list = list;
    }


    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setList(Field[] list) {
        this.list = list;
    }

    public Field[] getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
