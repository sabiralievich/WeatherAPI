package ru.gb.db;

import ru.gb.rest.WeatherResponse;
import ru.gb.dto.Field;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        for (Field f : fields) {
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
        System.out.println("???????????? ???????????????? ?? ????!");
    }

    public static void performReadDB(String sqlRequest) throws SQLException, ClassNotFoundException {
        establishConnection();
        //statement.executeUpdate(sqlRequest);
        ResultSet resultSet = statement.executeQuery(sqlRequest);

        System.out.printf("%-8s %-18s %-13s %-10s %-12s %-12s %s",
                "ID", "DATE", "CITY", "TEMP,??C", "FEELS,??C", "HUMIDITY", "DESCRIPTION");
        System.out.println();
        while (resultSet.next()) {
            System.out.printf("%-4d %-17s %-20s %-10s %-13s %-10d %s",
                    resultSet.getInt(1),
                    formatDate(resultSet.getString(2)),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            );
            System.out.println();
        }

    }

    public static String formatDate(String date) {
        long unixSeconds = Integer.parseInt(date);
        Date dateF = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(dateF);
        //  System.out.printf("%1$s %2$td %2$tB %2$tY", "????????: ", new Date(unixSeconds*1000L));

    }
}
