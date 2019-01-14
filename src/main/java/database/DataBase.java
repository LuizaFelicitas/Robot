package database;

import controller.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataBase {

    public void connect(String date) {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
        try {
            FileInputStream fileInputStream = new java.io.FileInputStream
                    (new java.io.File(path + "/config.properties"));
            Properties prop = new Properties();
            prop.load(fileInputStream);

            String driver = prop.getProperty("Driver");
            String url = prop.getProperty("url");
            String login = prop.getProperty("login");
            String password = prop.getProperty("password");

            Class.forName(driver);
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Controller controller = new Controller();
                Map<Integer, Integer> statusMap = controller.getStatus(query(connection, date));

                update(connection, statusMap);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private synchronized Map<Integer, String> query(Connection connection, String date) {
        Map<Integer, String> id_url = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM templ WHERE date >= '" + formatDate(date) + "' AND status IS NULL;");
            while (resultSet.next()) {
                id_url.put(resultSet.getInt("id"), resultSet.getString("url"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id_url;
    }

    private synchronized void update(Connection connection, Map<Integer, Integer> statusMap) {
        try (Statement statement = connection.createStatement()) {
            for (Map.Entry entry : statusMap.entrySet()
            ) {
                int status = (int) entry.getValue();
                int id = (int) entry.getKey();
                statement.execute("UPDATE templ SET status = " + status + " WHERE id = " + id + ";");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Date formatDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(simpleDateFormat.parse(date).getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return Date.valueOf(LocalDate.now());
    }
}


