package gb.lesson3.phonebook;

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();

        phonebook.add("Ivanov", "+79152466781");
        phonebook.add("Petrov", "+79463574146");
        phonebook.add("Ivanov", "+79746952143");
        phonebook.add("Sidorov", "+76413543214");
        phonebook.get("Ivanov");
        phonebook.get("ivan");
    }
}
