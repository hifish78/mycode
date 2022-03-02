package flexport;

import java.util.*;

/**
 * 题目：给一个字符串和整型数，字符串的前一个单词和后一个单词 是key-value pair 的关系， 随机给一个单词，让输出连续N个substring。
 * Param 1 String: "this is a sentence it is not a good one and it is also bad"
 * Param 2 Integer: 5
 * 如果给sentence，那么就有三种情况：
 * sentence it is a good
 * sentence it is also bad
 * sentence it is not a
 */
public class WordsPickup {
    public static List<String> genSubstring(String sentence, String start, int k) {
        String[] words = sentence.split(" ");
        Map<String, Set<String>> map = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new HashSet<>());
            }
            map.get(words[i]).add(words[i + 1]);
        }
        List<String> res = new ArrayList<>();
        gen(map, start, new ArrayList<>(), k, res);
        return res;
    }

    private static void gen(Map<String, Set<String>> map, String start, List<String> path, int k, List<String> res) {
        path.add(start);
        if (path.size() == k) {
            StringBuilder sb = new StringBuilder();
            for (String p : path) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(p);
            }

            res.add(sb.toString());
            path.remove(path.size() - 1);
            return;
        }

        if (map.get(start) == null) {
            return;
        }

        for(String next : map.get(start)){
            //   System.out.println("from:" + start + ", to:" + next);
            gen(map, next, path, k, res);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args){

        String s = "this is a sentence it is not a good one and it is also bad";

        System.out.print("");
        genSubstring(s, "sentence", 5).stream().forEach(w -> System.out.println(w));
        String[] words = s.split(" ");
        int randid = new Random().nextInt(words.length);
        System.out.println("random str:" + words[randid]);
        genSubstring(s, words[randid], 5).stream().forEach(w -> System.out.println(w));
    }
}
