package Lesson3_19_09_2015;

/**
 * Created by ICH on 19.09.2015.
 */
public class Recursion {

    public static void hanoy(int n, char a, char b, char c){
        if (n > 0){
            hanoy(n-1, a, c, b);
            System.out.println("Put " + n + " from " + a + " to " + c);
            hanoy(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
//        hanoy(3, 'a' , 'b', 'c');
        System.out.println((new Object()).hashCode());
        ;
    }
}
