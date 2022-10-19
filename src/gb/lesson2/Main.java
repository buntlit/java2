package gb.lesson2;

public class Main {
    final static int ARR_LENGTH = 4;

    public static void main(String[] args) {
        String[][] stringArray1 = new String[][]{{"4", "5", "6", "7"}, {"1", "2", "3", "0"}, {"4", "5", "6", "7"}, {"1", "2", "3", "0"}};
        String[][] stringArray2 = new String[][]{{"4", "5", "6", "7"}, {"4", "5", "6", "7"}, {"1", "2", "3", "0"}};
        String[][] stringArray3 = new String[][]{{"4", "5", "6", "7"}, {"1", "3", "0"}, {"4", "5", "6", "7"}, {"1", "2", "3", "0"}};
        String[][] stringArray4 = new String[][]{{"4", "5", "6", "7"}, {"1", "2", "3", "0"}, {"4", "5", "a", "7"}, {"1", "2", "3", "0"}};

        try {
            System.out.println("Sum of array: " + sumArray(stringArray1));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Sum of array: " + sumArray(stringArray2));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Sum of array: " + sumArray(stringArray3));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Sum of array: " + sumArray(stringArray4));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int sumArray(String[][] arr) {
        int sum = 0;
        if (arr.length == ARR_LENGTH) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length == ARR_LENGTH) {
                    for (int j = 0; j < arr.length; j++) {
                        try {
                            sum += Integer.parseInt(arr[i][j]);
                        } catch (NumberFormatException e) {
                            throw new MyArrayDataException("Illegal argument in column " + i + ", row " + j, i, j);
                        }
                    }
                } else {
                    throw new MyArraySizeException("Incorrect length of row " + arr[i].length, arr[i].length);
                }
            }
        } else {
            throw new MyArraySizeException("Incorrect length of column " + arr.length, arr.length);
        }
        return sum;
    }
}