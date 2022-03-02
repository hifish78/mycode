package c1;

import java.util.*;

public class Steps {
    public int findSteps(int m, int n, List<int[]> walls, List<int[][]> teleports) {
        int[] nums = twoToOne(m, n, walls, teleports);
        int step = 0;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                visited.add(cur);
                if (cur == m * n - 1) {
                    return step;
                }

                for (int next = cur + 1; next < m; next++) {
                    if (nums[next] == -1) {
                        return -1;
                    }
                    int dest = nums[next] == 0 ? next : nums[next];
                    if (visited.contains(dest)) {
                        continue;
                    }
                    queue.offer(dest);
                }
                step++;
            }


        }

        return -1;
    }

    private int[] twoToOne(int m, int n, List<int[]> walls, List<int[][]> teleports) {
        int[] res = new int[m + n];

        //wall -> - 1
        for (int[] wall : walls) {
            int idx = wall[0] * n + wall[1];
            res[idx] = -1;
        }

        //teleport
        for (int[][] item : teleports) {
            int start = item[0][0] * n + item[0][1];
            int end = item[1][0] * n + item[1][1];
            res[start] = end;
        }

      return res;

    }
}
