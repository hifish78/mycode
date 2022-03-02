package c1;
//https://leetcode.com/problems/snakes-and-ladders/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SnakeLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        int rows = board.length;
        int cols = board[0].length;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int ans = 0;

        queue.offer(1);
        visited.add(1);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                visited.add(cur);
                if (cur == n * n ) {
                    return ans;
                }

                for (int diff = 1; diff <= 6 && cur + diff <= n * n; diff++) {
                    int[] pos = getCoordinate(cur + diff, rows, cols);
                    int next = board[pos[0]][pos[1]] == -1 ? cur + diff : board[pos[0]][pos[1]];
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);

                }
            }
            ans++;
        }

        return -1;

    }

    public int[] getCoordinate(int n, int rows, int cols) {
        int r = rows - 1 - (n - 1) / cols;
        int c = (n - 1) % cols;
        if (r % 2 == rows % 2) {
            return new int[]{r, cols - 1 - c};
        } else {
            return new int[]{r, c};
        }
    }

    public static void main(String[] args) {
        SnakeLadders snakeLadders = new SnakeLadders();
        int[][] board = {{-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}};
//        int[][] board = {
//                {-1,-1,19,10,-1},
//                {2,-1,-1,6,-1},
//                {-1,17,-1,19,-1},
//                {25,-1,20,-1,-1},
//                {-1,-1,-1,-1,15}
//        };


        int res =snakeLadders.snakesAndLadders(board);

    }
}
