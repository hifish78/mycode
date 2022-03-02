package andy;

import java.util.Arrays;
import java.util.Scanner;

public class ToadFrog {

    private boolean isFinished(char[] board) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            if ( board[N /2] != '-') {
                return false;
            }
            if ( i < N / 2 && board[i] != 'F') {
                return false;
            }
            if (i > N / 2 && board[i] != 'T') {
                return false;
            }
        }
        return true;
    }

    private void game() {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        char[] board = new char[N];

        for (int i = 0; i < N; i++) {
            if ( i < N /2) {
                board[i] = 'T';
            } else if (i == N/2) {
                board[i] = '-';
            } else {
                board[i] = 'F';
            }
        }

        System.out.println();
        System.out.println(new String(board));

        int move = 0;
        int empty =  N / 2;
        

        while (true) {
            int num = scan.nextInt();
            int index = num - 1;
            if (index == empty || index < 0 || index >= N) {
                // System.out.println("index: " + index);
                System.out.println("Invalid entry!");
                System.out.println();
                continue;
            }

            if (Math.abs(empty -index) > 2) {
                System.out.println("Invalid entry!");
                System.out.println();
                continue;
            }

//            if (board[index] == 'F' && empty > index) {
//                // System.out.println("index: " + index);
//                System.out.println("Invalid entry!");
//                System.out.println();
//                continue;
//            }
//
//            if (board[index] == 'T' && empty < index) {
//                // System.out.println("index: " + index);
//                System.out.println("Invalid entry!");
//                System.out.println();
//                continue;
//            }

            board[empty] = board[index];
            board[index] = '-';
            empty = index;
            move++;

            System.out.println();
            System.out.println(new String(board));
            System.out.println("Number of moves:");
            System.out.println(move);
            System.out.println();

            if (isFinished(board)) {
                System.out.println("You win!");
                break;
            }
        }
    }

    public static void main(String args[]) {
       ToadFrog toadFrog = new ToadFrog();
       toadFrog.game();


    }
}
