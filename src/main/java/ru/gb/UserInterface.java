package ru.gb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

public class UserInterface {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите название города: ");
        String city = reader.readLine();
        System.out.print("Введите период (максимум 5 дней): ");
        String cntString = reader.readLine();
        int cnt = Integer.parseInt(cntString) * 8;
        cntString = String.valueOf(cnt);
        Controller.call(city, cntString);

    }
}
