package gb.lesson3;

import java.util.Map;
import java.util.TreeMap;

public class ArrayToMap {
    public static void main(String[] args) {

        final String[] ARRAY = {"cat", "dog", "apple", "robot", "cat", "robot", "apple", "coin", "cookie", "cup", "cat", "pine", "orange", "coin"};

        Map<String, Integer> map = new TreeMap<>();

        for (String array : ARRAY) {
            Integer value = map.getOrDefault(array, 0);
            map.put(array, value + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
