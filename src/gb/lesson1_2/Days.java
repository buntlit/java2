package gb.lesson1_2;

public enum Days {

    MONDAY("Monday", 40), TUESDAY("Tuesday", 32), WEDNESDAY("Wednesday", 24), THURSDAY("Thursday", 16), FRIDAY("Friday", 8), SATURDAY("Saturday", 0), SUNDAY("Sunday", 0);
    private String dayOfWeek;
    private int hoursToWeekend;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHoursToWeekend() {
        return hoursToWeekend;
    }

    Days(String dayOfWeek, int hoursToWeekend) {
        this.dayOfWeek = dayOfWeek;
        this.hoursToWeekend = hoursToWeekend;
    }

}