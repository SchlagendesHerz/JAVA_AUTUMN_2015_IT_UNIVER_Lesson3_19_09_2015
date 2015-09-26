package Lesson3_19_09_2015.homework;

public class Node<T> {
    private Node<T> next;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
