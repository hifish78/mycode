package c1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitString {
//    比如input string 是 abcdef, brute force 我是两个nested loop, 分别代表第一段substring 和 第二段substring 的长度，
//    从长度1开始loop, 所以所有的情况就是 [a, b, cdef], [a,bc, def], [a, bcd ,ef], [a, bcde, f],
//    [ab, c, def], [ab, cd, ef].........
//    给一段string, 把string 分成三份，每份长度至少1. 然后要求这三份其中两份，两两相加不会变成第三份。
//    return 所有可能的分割组合，这题用brute force 就行，也不难。

    public List<List<String>> split(String str) {
        int n = str.length();
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String str1 = str.substring(0, i);
                String str2 = str.substring(i, j);
                String str3 = str.substring(j);


                if (str1.equals(str2 + str3) || str1.equals(str3 + str2) ||
                        str2.equals(str1 + str3) || str2.equals(str3 + str1) ||
                        str3.equals(str1 + str2) || str3.equals(str2 + str1)) {
                    continue;
                }
                res.add(Arrays.asList(str1, str2, str3));
            }
        }
        return res;
    }
}
