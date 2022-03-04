package flexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decodeways {
    public int numDecodings(String s) {
        // 不难发现对于字符串 s 的某个位置 i 而言，我们只关心「位置 i 自己能否形成独立 item 」和「位置 i 能够与上一位置（i-1）能否形成 item」，而不关心 i-1 之前的位置
        //dp[i]表示前i个字符的解码方案
        //dp[i] = dp[i-1]  ,   1<= s[i] <= 9
        //dp[i] = dp[i-2],  10=< s[i-1, i] <= 26
        //dp[i] = dp[i-1] + dp[i-2], 1<= s[i] <= 9 and 10=< s[i-1, i] <= 26

        //126
        // s[i] --> [1,9] dp[i] = dp[i-1]
        // s[i-1, i] --> [10, 26], dp[i] = dp[i-2]
        // dp[i] = dp[i -1] + dp[i-2];
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < n + 1; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];

    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        Map<String, Character> map = new HashMap<>();
        int i = 1;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            String val = String.valueOf(i);
            map.put(val, ch);
            i++;
        }

        //11106
        //AAJF(1 1 10 6) , KJF(11, 10, 6)
        StringBuilder sb = new StringBuilder();
        dfs(str, map,0, sb, res);
        return res;
    }

    private void dfs(String str, Map<String, Character> map, int index, StringBuilder sb, List<String> res) {
        if (index == str.length()) {
            res.add(sb.toString());
            return;
        }

        // 1 -> A, 2 -> B
        for (String key : map.keySet()) {
            if (str.startsWith(key, index)) {
                sb.append(map.get(key));
                dfs(str, map, index + key.length(), sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Decodeways decodeways = new Decodeways();
//        String str = "12";
//        int res = decodeways.numDecodings(str);
//        System.out.println(res);

//        "AAJF" with the grouping (1 1 10 6)
//        "KJF" with the grouping (11 10 6)
        String str2 = "11106";
        List<String> res2 = decodeways.decode(str2);
        for (int i = 0; i < res2.size(); i++) {
            System.out.println(res2.get(i));
        }

    }
}
