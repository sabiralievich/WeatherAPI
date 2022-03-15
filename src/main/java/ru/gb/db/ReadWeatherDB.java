package ru.gb.db;

import java.sql.SQLException;

public class ReadWeatherDB {

    public static void readDB(String city) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM weather WHERE city = " + "'" + city + "'";
        Repository.performReadDB(sqlQuery);
    }
}
