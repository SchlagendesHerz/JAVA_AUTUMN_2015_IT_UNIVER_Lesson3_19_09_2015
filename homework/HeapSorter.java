package Lesson3_19_09_2015.homework;

import java.util.Arrays;

public class HeapSorter {

    public static <T extends Comparable<T>> void sort(T[] array) {
        heapify(array);
        T polled;
        for (int i = 0; i < array.length; i++) {
            polled = array[0];
            array[0] = array[array.length - 1 - i];
            pullDown(array, 0, (array.length - 1 - i) / 2);
            array[array.length - 1 - i] = polled;
        }
    }

    private static <T extends Comparable<T>> int getChildToCompare(T[] array, int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        return (rightChild > array.length - 1) ? leftChild :
                (array[leftChild].compareTo(array[rightChild]) > 0)
                        ? leftChild : rightChild;
    }

    private static <T extends Comparable<T>> void heapify(T[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            pullDown(array, i, array.length / 2);
        }
    }

    private static <T extends Comparable<T>> void pullDown(T[] array, int index, int border) {
        if (index >= array.length / 2) {
            return;
        }
        T toPullDown = array[index];
        int childToCompare = getChildToCompare(array, index);
        while (index < border && toPullDown.compareTo(array[childToCompare]) < 0) {
            array[index] = array[childToCompare];
            index = childToCompare;
            childToCompare = getChildToCompare(array, index);
        }
        array[index] = toPullDown;
    }

    public static void main(String[] args) {
        Integer[] ar = new Integer[100];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = (int) (Math.random() * 1000);
        }
//        heapify(ar);
//        MyPriorityQueue<Integer> q = new MyPriorityQueue<Integer>(ar.length);
//        q.heap = ar;
//        q.size = ar.length;
//        System.out.println(q);
        sort(ar);
        for (int i = 0; i < ar.length - 2; i++) {
            if (ar[i].compareTo(ar[i + 1]) > 0){
                System.out.println("false");
                break;
            }
        }
        System.out.println(Arrays.toString(ar));
    }
}