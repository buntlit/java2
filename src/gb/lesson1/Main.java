package gb.lesson1;

public class Main {
    public static void main(String[] args) {

        Obstacle[] obstacle = {new Wall(2), new Track(100), new Wall(4), new Track(2000), new Track(1500)};
        Competitors[] competitors = {new Robot("Robot", 100000, 10), new Cat("Cat", 2000, 4), new Human("Chel", 10000, 4), new Cat("Kot"), new Human("Hum"), new Robot("Bobbert")};
        for (Competitors comp : competitors) {
            for (Obstacle obst : obstacle) {
                obst.doIt(comp);
                if (!comp.isOnDistance()) {
                    comp.infoRetired();
                    break;
                }
            }
            if (comp.isOnDistance()) {
                comp.infoSuccess();
            }
        }
    }
}
