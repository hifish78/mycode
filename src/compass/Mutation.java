package compass;

import java.util.*;

public class Mutation {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String str: bank) {
            set.add(str);
        }

        if (!set.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(end)) {
                    return cnt;
                }

                List<String> nexts = getNext(set, cur);
                if (nexts.size() == 0) {
                    return -1;
                }

                for (String next : nexts) {
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }

            }
            cnt++;
        }

        return cnt;
    }

    private List<String> getNext(Set<String> set, String cur) {
        List<String> res = new ArrayList<>();
        String choices = "ACGT";
        for (int i = 0; i < cur.length(); i++) {
            char ch = cur.charAt(i);
            for (int j = 0; j < choices.length(); j++) {
                if (ch != choices.charAt(j)) {
                    String next = cur.substring(0, i) + choices.charAt(j) + cur.substring(i + 1);
                    if (set.contains(next)) {
                        res.add(next);
                    }
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        Mutation sol = new Mutation();
        String start = "AACCTTGG";
        String end = "AATTCCGG";
        String[] bank =  {"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        int res = sol.minMutation(start, end, bank);
        System.out.println(res);
    }
}
