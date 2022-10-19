package gb.lesson2;

public class MyArraySizeException extends IllegalArgumentException{
    private int length;

    public int getLength() {
        return length;
    }

    public MyArraySizeException(String s, int length) {
        super(s);
        this.length = length;
    }
}
