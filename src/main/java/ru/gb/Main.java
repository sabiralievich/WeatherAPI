package ru.gb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        initialDialog();
    }

    public static void initialDialog() throws IOException, ParseException, SQLException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Для получения прогноза погоды введите 1, или введите q для выхода..");
        String choice = reader.readLine();
        isExit(choice);
            if (choice.equals("1")) {
                getForecast(reader);
            }/* else if (choice.equals("2")) {
                getDB(reader);
            }*/ else initialDialog();

    }

    private static void getDB(BufferedReader reader) throws IOException, SQLException, ClassNotFoundException, ParseException {
        System.out.println("Введите название города (на русском) или введите q для выхода:  ");
        String chosenCity = reader.readLine();
        isExit(chosenCity);
        ReadWeatherDB.readDB(chosenCity);
        initialDialog();
    }

    private static void getForecast(BufferedReader reader) throws IOException, ParseException, SQLException, ClassNotFoundException {

        System.out.print("Введите название города (на английском) или введите q для выхода: ");
        String city = reader.readLine();
        isExit(city);

        System.out.print("Введите период (максимум 5 дней) или введите q для выхода: ");

        String cntString = reader.readLine();
        isExit(cntString);
        int cnt = Integer.parseInt(cntString) * 8;
            cntString = String.valueOf(cnt);
            Controller.call(city, cntString);
            initialDialog();
        }


    private static void isExit(String city) {
        if (city.equals("q") || city.equals("exit")) {
            System.exit(0);
        }
    }


}
