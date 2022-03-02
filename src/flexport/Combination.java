package flexport;

//给一个map， {"0":["a","b"], "1":["c"]}
//        和一个string，"001"
//        输出所有可能的组合["aac","abc","bac","bbc"]
// similar as https://leetcode.com/problems/letter-combinations-of-a-phone-number/submissions/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//follow up
//        如果map的key不止一位，如加入一个"01":"z"，
//        输出应该是["aac","abc","bac","bbc","az","bz"]

public class Combination {
    public List<String>  combination(Map<String, char[]> map, String str) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(map, str, sb, res);
        return res;
    }
    // {"0":["a","b"], "1":["c"]}
    // 00
    private void dfs(Map<String, char[]> map, String str, StringBuilder sb, List<String> res) {
        if (sb.length() == str.length()) {
            res.add(sb.toString());
            return;
        }

        char[] arr = map.get(str.charAt(sb.length()) + "");
        for (char ch : arr) {
            sb.append(ch);
            dfs(map, str, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> combination2(Map<String, char[]> map, String str) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs2(map, str,0,  sb, res);
        return res;
    }

    //        如果map的key不止一位，如加入一个"01":"z"，
//        输出应该是["aac","abc","bac","bbc","az","bz"]
    // "0", "1", "01"
    private void dfs2(Map<String, char[]> map, String str, int index, StringBuilder sb, List<String> res) {
        if (str.length() == index) {
            res.add(sb.toString());
            return;
        }
        //001
        for (String key : map.keySet()) {
            if (str.startsWith(key, index)) {
                char[] arr = map.get(key);
                for (char ch : arr) {
                    sb.append(ch);
                    dfs2(map, str, index + key.length(), sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }


    }

    public static void main(String[] args) {
        Combination com = new Combination();
        String str = "001";
//        Map<String, char[]> map = new HashMap<>();
//        map.put("0", new char[]{'a', 'b'});
//        map.put("1", new char[]{'c'});
//        List<String> res = com.combination(map, str);
//        System.out.println(res);

        Map<String, char[]> map2 = new HashMap<>();
        map2.put("0", new char[]{'a', 'b'});
        map2.put("1", new char[]{'c'});
        map2.put("01", new char[]{'z'});
        List<String> res2 = com.combination2(map2, str);
        System.out.println(res2);
    }

}
