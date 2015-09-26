package Lesson3_19_09_2015.homework;

import java.lang.reflect.Array;
import java.util.Comparator;

import static java.lang.Math.ceil;
import static java.lang.Math.log;

public class MyPriorityQueue<T extends Comparable<T>> {

    private static final int DEFAULT_QUEUE_LENGTH = 20;

    T[] heap;
    int size;
    private Comparator<T> comparator;


    public MyPriorityQueue(int qLength) {
        this(qLength, (o1, o2) -> o2.compareTo(o1));
    }

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int qLength, Comparator<T> comparator) {
        if (!qLengthCheck(qLength)) {
            qLength = DEFAULT_QUEUE_LENGTH;
        }
        heap = (T[]) (Array.newInstance(Comparable.class, qLength));
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean offer(T elem) {
        if (this.size == heap.length) {
            return false;
        }
        heap[size] = elem;
        pullUp(size++);
        return true;
    }

    public T poll() {
        if (this.size == 0) {
            return null;
        }
        T toReturn = heap[0];
        heap[0] = heap[--size];
        pullDown(0);
        return toReturn;
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        int maxLevel = (int) ceil(log(this.size + 1) / log(2) - 1);
        int maxKeySize = 4;
        int curIndex = 0;
        for (int i = 0; i <= maxLevel; i++) {
            int edgeOffset = (int) ((Math.pow(2, maxLevel - i - 1) - 1) * (maxKeySize + 1) + Math.ceil(maxKeySize / 2.0));
            toReturn.append(makeString(' ', edgeOffset));
            int levelLength = (int) Math.pow(2, i);
            for (int j = 0; j < levelLength; j++) {
                T elem = curIndex < this.size ? heap[curIndex++] : null;
                if (i != 0 && j % 2 == 0) toReturn.append('[');
                toReturn.append(elem == null ? makeString('-', maxKeySize - 1) : fitStringToSize(elem.toString(), maxKeySize - 1));
                if (i != 0 && j % 2 != 0) toReturn.append(']');
                if (j != levelLength - 1) {
                    int nodeOffset = (int) (Math.pow(2, maxLevel - i) * (maxKeySize + 1) - maxKeySize);
                    toReturn.append(j % 2 != 0 ? makeString(' ', nodeOffset) : makeString('_', nodeOffset));
                }
            }
            toReturn.append("\n");
        }
        return toReturn.toString();
    }

    private String fitStringToSize(String data, int size) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < size - data.length(); i++) {
            toReturn.append(" ");
        }
        toReturn.append(data);
        return toReturn.toString();
    }

    private int getChildToCompare(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        return (rightChild > size - 1) ? leftChild :
                (comparator.compare(heap[leftChild], heap[rightChild]) > 0)
                        ? rightChild : leftChild;
    }

    private String makeString(char ch, int quant) {
        StringBuilder toReturn = new StringBuilder();
        while (quant-- > 0) {
            toReturn.append(ch);
        }
        return toReturn.toString();
    }

    private void pullDown(int index) {
        if (index >= size / 2) {
            return;
        }
        T toPullDown = heap[index];
        int childToCompare = getChildToCompare(index);
        while (index < size / 2 && comparator.compare(toPullDown, heap[childToCompare]) > 0) {
            heap[index] = heap[childToCompare];
            index = childToCompare;
            childToCompare = getChildToCompare(index);
        }
        heap[index] = toPullDown;
    }

    private void pullUp(int index) {
        T toPullUp = heap[index];
        int parent = (index - 1) / 2;
        while (index > 0 && comparator.compare(toPullUp, heap[parent]) < 0) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index - 1) / 2;
        }
        heap[index] = toPullUp;
    }

    private boolean qLengthCheck(int qLength) {
        return qLength > 0;
    }

    public static void main(String[] args) {
//        MyPriorityQueue<Integer> q = new MyPriorityQueue<>(31, (o1, o2) -> o1.compareTo(o2));
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>(31);
        for (int i = 0; i < 31; i++) {
            q.offer((int) (Math.random() * 100));
        }
//        Integer [] heap = new Integer[63];
//        for (int i = 0; i <heap.length ; i++) {
//            heap[i] = i;
//        }
//        q.heap = heap;
        System.out.println(q);
        while (!q.isEmpty()) {
            System.out.println(q.poll());
            System.out.println(q);
        }
    }
}