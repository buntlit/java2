package gb.lesson1;

public class Robot implements Competitors {
    private String name;
    private final String TYPE = "Robot";
    private int maxDistance;
    private int maxHeight;
    private final int MAX_DISTANCE = 5000;
    private final int MAX_HEIGHT = 5;
    private boolean onDistance = true;

    public void run(int dist) {
        if (dist <= maxDistance) {
            System.out.printf("%s %s successfully run %d meters\n", TYPE, name, dist);
            onDistance = true;
        } else {
            System.out.printf("%s %s can't run %d meters\n", TYPE, name, dist);
            onDistance = false;
        }
    }

    public void jump(int height) {
        if (height <= maxHeight) {
            System.out.printf("%s %s successfully jump %d meters\n", TYPE, name, height);
            onDistance = true;
        } else {
            System.out.printf("%s %s can't jump %d meters\n", TYPE, name, height);
            onDistance = false;
        }

    }

    public boolean isOnDistance() {
        return onDistance;
    }

    public Robot(String name) {
        this.name = name;
        this.maxDistance = MAX_DISTANCE;
        this.maxHeight = MAX_HEIGHT;
    }

    public Robot(String name, int maxDistance, int maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    public void infoRetired() {
        System.out.printf("%s %s retired\n\n", TYPE, name);
    }

    public void infoSuccess() {
        System.out.printf("%s %s successfully complete obstacles\n\n", TYPE, name);
    }
}

