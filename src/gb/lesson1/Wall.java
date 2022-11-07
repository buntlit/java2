package gb.lesson1;

public class Wall implements Obstacle {
    int height;

    public void doIt(Competitors competitor) {
        competitor.jump(height);
    }

    public Wall(int height) {
        this.height = height;
    }
}

