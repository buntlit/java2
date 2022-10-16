package gb.lesson1;

public class Track implements Obstacle {
    int dist;

    public void doIt(Competitors competitor) {
        competitor.run(dist);
    }

    public Track(int dist) {
        this.dist = dist;
    }
}