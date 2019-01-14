import database.DataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите дату в формате \"гггг-мм-дд\" (Enter date in format \"yyyy-MM-dd\") : ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String date = reader.readLine();

            DataBase dataBase = new DataBase();
            dataBase.connect(date);
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
