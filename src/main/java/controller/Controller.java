package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    public synchronized Map<Integer, Integer> getStatus(Map<Integer, String> dbMap) {
        Map<Integer, Integer> httpStatusMap = new HashMap<>();
        try {
            for (Map.Entry entry : dbMap.entrySet()
            ) {
                URL url = new URL(entry.getValue().toString());

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                httpStatusMap.put((Integer) entry.getKey(), urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return httpStatusMap;
    }
}
