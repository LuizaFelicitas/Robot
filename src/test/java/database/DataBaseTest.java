package database;

import controller.Controller;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DataBaseTest {

    String url = "http://google.com/";

    @Test
    public void query() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, url);
        map.put(2, url);
        map.put(3, url);
        Map<Integer, Integer> statusMap = new HashMap<>();
        statusMap.put(1, 200);
        statusMap.put(2, 200);
        statusMap.put(3, 200);
        Controller controller = new Controller();
        assertEquals(statusMap, controller.getStatus(map));
    }
}