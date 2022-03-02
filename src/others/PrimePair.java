package others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimePair {
    // n = a * b ( a <= b)
    // a <= b
    // a * a <= a * b
    // a * b <= b * b
    // a * b = n, so a * a <= n <= b * b ==> a <= sqrt(n) <= b
    //So if a number (greater than 1) is not prime and we test divisibility
    // up to square root of the number, we will find one of the factors.
    public boolean prime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if ( x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void findPair(int n1, int n2) {
        int cnt = 0;
        int first = 0;
        int second = 0;
        for (int i = n1; i < n2; i++) {
            if (prime(i)) {
                if (first == 0) {
                    first = i;
                } else {
                    second = i;
                    if (second - first == 2) {
                        cnt++;
                        System.out.println(first + " " + second);
                    }
                    first = second;
                }
            }
        }

        System.out.println(cnt);
    }

    public void getPair(int n) {

        // 5 : 0, 5; 1, 4; 2, 3
        // 6: 0, 6,  1, 5, ... 3, 3
        for (int i = 0; i <= n /2; i++) {
            System.out.println(i + "," +  (n - i));
        }
    }
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n1 = scan.nextInt();
//        int n2 = scan.nextInt();
//        PrimePair findPair = new PrimePair();
//        findPair.findPair(n1, n2);

        PrimePair findPair = new PrimePair();
        findPair.getPair(20);

    }
}
