package gb.lesson5;

public class Main {
    final static int SIZE = 10000000;
    final static int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {

        float[] arr1 = new float[SIZE];
        float[] arr2 = new float[SIZE];
        long timeStart1;
        long timeStart2;
        long timeEnd1;
        long timeEnd2;

        for (int i = 0; i < SIZE; i++) {
            arr1[i] = 1;
        }
        for (int i = 0; i < SIZE; i++) {
            arr2[i] = 1;
        }
        timeStart1 = System.currentTimeMillis();
        arr1 = calculateArrayOneThread(arr1);
        timeEnd1 = System.currentTimeMillis();

        System.out.println("Last value in one-thread method: " + arr1[SIZE - 1]);
        System.out.println("Working time one-thread method: " + (timeEnd1 - timeStart1) + "ms");

        System.out.println();

        timeStart2 = System.currentTimeMillis();
        arr2 = calculateArrayTwoThreads(arr2);
        timeEnd2 = System.currentTimeMillis();

        System.out.println("Last value in two-thread method: " + arr2[SIZE - 1]);
        System.out.println("Working time two-thread method: " + (timeEnd2 - timeStart2) + "ms");

    }

    public static float[] calculateArrayOneThread(float[] array) {
        return calculateArray(array, 0);
    }

    private static float[] calculateArrayTwoThreads(float[] array) throws InterruptedException {

//        float[] subArr1 = new float[HALF_SIZE];
//        float[] subArr2 = new float[HALF_SIZE];
//        System.arraycopy(array, 0, subArr1, 0, HALF_SIZE);
//        System.arraycopy(array, HALF_SIZE, subArr2, 0, HALF_SIZE);
//        Thread thread1 = new Thread(() -> {
//            subArr1 = calculateArray(subArr1, 0);
//        });
//        Thread thread2 = new Thread(() -> {
//            subArr2 = calculateArray(subArr2, HALF_SIZE);
//        });
//
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//
//        System.arraycopy(subArr1, 0, array, 0, HALF_SIZE);
//        System.arraycopy(subArr2, 0, array, HALF_SIZE, HALF_SIZE);
//
//        return array;


//        final float[][] subArr1 = {new float[HALF_SIZE]};
//        final float[][] subArr2 = {new float[HALF_SIZE]};
//        System.arraycopy(array, 0, subArr1[0], 0, HALF_SIZE);
//        System.arraycopy(array, HALF_SIZE, subArr2[0], 0, HALF_SIZE);
//
//        Thread thread1 = new Thread(()->{
//            subArr1[0] = calculateArray(subArr1[0], 0);
//        });
//        Thread thread2 = new Thread(()->{
//            subArr2[0] = calculateArray(subArr2[0], HALF_SIZE);
//        });
//
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//
//        System.arraycopy(subArr1[0], 0, array, 0, HALF_SIZE);
//        System.arraycopy(subArr2[0], 0, array, HALF_SIZE, HALF_SIZE);
//
//        return array;


        float[] subArr1 = new float[HALF_SIZE];
        float[] subArr2 = new float[HALF_SIZE];
        System.arraycopy(array, 0, subArr1, 0, HALF_SIZE);
        System.arraycopy(array, HALF_SIZE, subArr2, 0, HALF_SIZE);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < subArr1.length; i++) {
                subArr1[i] = calculateCells(subArr1[i], i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < subArr2.length; i++) {
                subArr2[i] = calculateCells(subArr2[i], i + HALF_SIZE);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.arraycopy(subArr1, 0, array, 0, HALF_SIZE);
        System.arraycopy(subArr2, 0, array, HALF_SIZE, HALF_SIZE);

        return array;
    }

    public static float[] calculateArray(float[] array, int cellNumberCoefficient) {
        for (int i = 0; i < array.length; i++) {
            array[i] = calculateCells(array[i], i + cellNumberCoefficient);
        }
        return array;
    }

    public static float calculateCells(float cellValue, int cellNumber) {
        return (float) (cellValue * Math.sin(0.2f + cellNumber / 5) * Math.cos(0.2f + cellNumber / 5) * Math.cos(0.4f + cellNumber / 2));
    }
}
