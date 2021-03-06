package ru.gb;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gb.db.Repository;
import ru.gb.dto.Field;
import ru.gb.rest.WeatherRequest;
import ru.gb.rest.WeatherResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller {

    public static void call(String city, String cnt) throws IOException, ParseException, SQLException, ClassNotFoundException {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.requestFromServer(city, cnt);
        mapResponse(weatherRequest, cnt);
    }

    public static void mapResponse(WeatherRequest weatherRequest, String cnt) throws JsonProcessingException, ParseException, SQLException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WeatherResponse weatherResponse = objectMapper.readValue(weatherRequest.getJsonResponse(), WeatherResponse
                .class);

        String jsonArray = objectMapper.writeValueAsString(weatherResponse.getList());
        List<Field> fields = objectMapper.readValue(jsonArray, new TypeReference<List<Field>>() {
        });

        printFields(weatherResponse, fields, cnt);
    }

    public static void printFields(WeatherResponse weatherResponse, List<Field> fields, String cnt) throws ParseException, SQLException, ClassNotFoundException {
        int forecastPeriod = weatherResponse.getCnt();
        System.out.println("Погода в г. " + weatherResponse.getCity().getName() + " на " + forecastPeriod / 8 + " дней:");


        for (int i = 0; i < forecastPeriod; i = i + 8) {
            System.out.print(formatDate(fields.get(i).getDate()) + ": ");

            System.out.print("Температура: " + Math.round(fields.get(i).getMain().getTemp()) + " градусов Цельсия, ");
            System.out.print("Ощущается как: " + Math.round(fields.get(i).getMain().getFeelsLike()) + " градусов Цельсия, ");
            System.out.print("относительная влажность: " + fields.get(i).getMain().getHumidity() + "%, ");
            for (int j = 0; j < fields.get(j).getWeather().length; j++) {
                System.out.println(fields.get(j).getWeather()[j].getDescription());
            }
        }

        Repository.performAddRecords(weatherResponse, fields);

    }

    public static String formatDate(String date) {
        long unixSeconds = Integer.parseInt(date);
        Date dateF = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("E MM.dd");
        return sdf.format(dateF);
        //  System.out.printf("%1$s %2$td %2$tB %2$tY", "Дата: ", new Date(unixSeconds*1000L));

    }

}
