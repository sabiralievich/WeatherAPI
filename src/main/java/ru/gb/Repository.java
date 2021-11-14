package ru.gb;

import ru.gb.dto.Field;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Repository {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    public static void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:weather.db");
        statement = connection.createStatement();
    }
    public static void performCreateDB() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS weather (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date STRING, city STRING, temp DOUBLE, feels_like DOUBLE, humidity INTEGER, description STRING);");
        statement.close();
    }
    public static void performAddRecords(WeatherResponse weatherResponse, List<Field> fields) throws SQLException, ParseException, ClassNotFoundException {
        establishConnection();
        performCreateDB();

        preparedStatement = connection.prepareStatement("INSERT INTO weather (date, city, temp, feels_like, humidity, description) VALUES (?,?,?,?,?,?)");
        preparedStatement.setString(2, weatherResponse.getCity().getName());
        for(Field f : fields) {
            String date = f.getDate();
            preparedStatement.setString(1, date);
            Double temp = f.getMain().getTemp();
            preparedStatement.setDouble(3, temp);
            Double feelsLike = f.getMain().getFeelsLike();
            preparedStatement.setDouble(4, feelsLike);
            int humidity = f.getMain().getHumidity();
            preparedStatement.setInt(5, humidity);
            for (int i = 0; i < f.getWeather().length; i++) {
                String description = f.getWeather()[i].getDescription();
                preparedStatement.setString(6, description);
            }
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
        System.out.println("Данные занесены в БД!");
    }

    public static void performReadDB(String sqlRequest) throws SQLException, ClassNotFoundException {
        establishConnection();
        statement.executeUpdate(sqlRequest);
    }
}
