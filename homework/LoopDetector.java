package Lesson3_19_09_2015.homework;

public class LoopDetector {

    public static <T> boolean isLoop(Node<T> head) {
        Node<T> runner1 = head;
        Node<T> runner2 = head;
        while (runner1.getNext() != null || runner2.getNext() != null) {
            if (runner1.getNext() == runner2.getNext().getNext()) {
                return true;
            }
            runner1 = runner1.getNext();
            runner2 = runner2.getNext().getNext();
        }
        return false;
    }

    public static void main(String[] args) {
        Node<Integer> head = new Node<>((int) (Math.random() * 100));
        Node<Integer> curNode = head;
        Node<Integer> loop = null;
        for (int i = 0; i < 20; i++) {
            curNode.setNext(new Node<>((int) (Math.random() * 100)));
            curNode = curNode.getNext();
            if (i == 10) {
                loop = curNode;
            }
            if (i == 19) {
                curNode.setNext(loop);
            }
        }
        curNode = head;
//        while (curNode.getNext() != null){
//            System.out.println(curNode.getData());
//            curNode = curNode.getNext();
//        }

        System.out.println(isLoop(head));
    }

}