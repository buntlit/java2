package gb.lesson3.phonebook;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {
    private Map<String, ArrayList<String>> phonebook = new TreeMap<>();

    public void get(String getStr) {
        System.out.println(phonebook.getOrDefault(getStr, null));
    }

    public void add(String name, String number) {
        ArrayList<String> list = new ArrayList();
        if (phonebook.get(name) != null) {
            for (String suppStr : phonebook.get(name)) {
                list.add(suppStr);
            }
        }
        list.add(number);
        phonebook.put(name, list);
    }
}
