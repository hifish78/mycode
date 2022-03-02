package wayfair;

import java.util.*;

public class FindMaxName {
    public String findMax(String[][] visits) {
        Arrays.sort(visits, (a, b) -> ( a[1].equals(b[1]) ? Integer.parseInt(a[0]) - Integer.parseInt(b[0]) :  a[1].compareTo(b[1])));

        Map<String, Queue<Integer>> map = new HashMap<>();
        int max = 0;
        String res = "";
        for (String[] visit : visits) {
            int time = Integer.parseInt(visit[0]);
            String name = visit[1];
            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<>());
            }
            Queue<Integer> queue = map.get(name);
            while (!queue.isEmpty() && !inOneHr(time, queue.peek())) {
                queue.poll();
            }
            queue.offer(time);
            if (queue.size() > max) {
                max = queue.size();
                res = name;
            }
        }
        return res;
    }

    private boolean inOneHr(int t1, int t2) {
        int h1 = t1 / 100;
        int m1 = t1 % 100;
        int h2 = t2 / 100;
        int m2 = t2 % 100;
        int diff = (h1 - h2) * 60 + (m1 - m2);
        if (diff <= 60) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FindMaxName findMaxName = new FindMaxName();
        String[][] visits = {
                {"1000", "c"},
                {"2000", "a"},
                {"2100", "b"},
                {"2020", "a"},
                {"3020", "b"},
                {"9020", "b"},
                {"5020", "d"},
                {"2010", "a"}};
        String res = findMaxName.findMax(visits);
        System.out.println(res);

    }
}
