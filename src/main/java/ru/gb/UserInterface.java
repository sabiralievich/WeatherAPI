package ru.gb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class UserInterface {
    public static void main(String[] args) throws IOException, ParseException {

           BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
           String city = reader.readLine();
           String cntString = reader.readLine();
           int cnt = Integer.parseInt(cntString) * 8;
           cntString = String.valueOf(cnt);
           Controller.call(city, cntString);

    }
}
