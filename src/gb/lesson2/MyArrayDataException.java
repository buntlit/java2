package gb.lesson2;

public class MyArrayDataException extends NumberFormatException{
    private int numColumn;
    private int numRow;

    public int getNumColumn() {
        return numColumn;
    }

    public int getNumRow() {
        return numRow;
    }

    public MyArrayDataException(String s, int numColumn, int numRow) {
        super(s);
        this.numColumn = numColumn;
        this.numRow = numRow;
    }
}
