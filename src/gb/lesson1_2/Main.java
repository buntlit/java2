package gb.lesson1_2;

public class Main {
    public static void main(String[] args) {
        for (Days day : Days.values()) {
            System.out.println(getWorkingHours(day));
        }

    }

    public static String getWorkingHours(Days day) {
        if (day.getHoursToWeekend() > 0) {
            return "Today " + day.getDayOfWeek() + ". It's " + day.getHoursToWeekend() + " to weekend";
        } else {
            return "Today " + day.getDayOfWeek() + ". It's weekend!";
        }
    }
}

