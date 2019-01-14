package database;

import controller.Controller;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DataBaseTest {

    private String url = "http://google.com/";
    private String url403 = "http://www.cuoma.com/404";
    private String url404 = "https://yadi.sk/d/5Spw2WFEEV8mc";

    @Test
    public void query() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, url);
        map.put(2, url403);
        map.put(3, url404);
        Map<Integer, Integer> statusMap = new HashMap<>();
        statusMap.put(1, 200);
        statusMap.put(2, 403);
        statusMap.put(3, 404);
        Controller controller = new Controller();
        assertEquals(statusMap, controller.getStatus(map));
    }
}