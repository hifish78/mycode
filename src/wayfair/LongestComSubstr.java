package wayfair;

import java.util.Arrays;
import java.util.List;

/**
 * https://www.jianshu.com/p/46f717dd614f
 *
 * [
 *   ["3234.html", "xys.html", "7hsaa.html"], // user1
 *   ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
 * ]
 * 输出两个user的最长连续且相同的访问记录。 ["xys.html", "7hsaa.html"]
 *
 * b) longest common substring https://www.lintcode.com/problem/79/
 * 区别是题面把字符串换成browsing history，相应字符变成history里的每个url，而且要求输出最长连续的common history，而不是最长长度。
 * [
 *    ["3234.html", "xys.html", "7hsaa.html"], // user1
 *    ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
 * ]
 *  						                user2
 *           user1         ""	   "3234.html"		"sdhsfjdsh.html"	"xys.html"	"7hsaa.html"
 *    			""			0	    0				0					0			0
 *  		"3234.html"		0	    1				0					0			0
 *  		"xys.html"		0	    0				0					1			0
 *  		"7hsaa.html"	0	    0				0					0			2
 *
 *
 *
 *
 *  String A = "tutorialhorizon";
 *  String B = "dynamictutorialProgramming";
 *  Output: Length of Longest Common Substring: 8 ("tutorial").
 *
 *  								b
 *              ""d y n a m i c t u t o r i a l P r o g r a m m i n g
 *          ""  0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *  		t	0
 *  		u	0
 *  		t	0
 *  a	o	0
 *  		r	0
 *  		i	0
 *  		a	0
 *  		l	0
 *  i represent ith letter from string 1
 *  j represent jth letter from string 2
 *  dp(i,j) means the longest length of the common subarray
 *  dp[i][j] = {
 *  	if (a[i] == b[j])  -> dp[i-1][j-1] +1
 *  }
 *
 *  	   "" a b c d
 *  ""  0  0 0 0 0
 *  a   0  1 0 0 0
 *  b   0  0 2
 *  c   0  0
 */

// 每个元素的值dp[i][j]代表的是子串s1的前i部分 s1[0..i)  和子串s2的前j部分 s2[0..j) 中最大公共子串的长度。
//        String str3 = "alibaba";
//        String str4 = "ababila";
//        System.out.println(dp[4][2]); //1
//        System.out.println(str1.substring(0, 4)); //alib , 不包含index4 [0..4) ==> [0..3]
//        System.out.println(str2.substring(0, 2)); //ab
public class LongestComSubstr {
    public int findLongestLen(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        int res = 0;
        for (int i = 0;  i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (str1.charAt(i) ==  str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }



        return res;
    }

    // 那么其实这个矩阵中最大的元素的值就代表了公共子串的最大长度，
    // 而且通过这个最大元素的行列索引我们就可以定位到这个公共子串在原字符串中的结束位置，再通过长度就可以反推出公共子串的内容了。
    public String[] findLongestComSubstr(String[] user1, String[] user2)  {
        int m = user1.length;
        int n = user2.length;
        int[][] dp = new int[m + 1] [ n + 1];

        int maxLen = 0;
        int maxIndex = 0;

        for (int i = 0;  i < user1.length; i++) {
            for (int j = 0; j < user2.length; j++) {
                if (user1[i].equals(user2[j])) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > maxLen) {
                        maxLen = dp[i+ 1][j + 1];
                        maxIndex = i + 1;
                    }

                }
            }
        }

        String[] res = new String[maxLen];
        int k = 0;
        System.out.println(maxIndex);
        System.out.println(maxLen);
        for (int j = maxIndex - maxLen; j < maxIndex; j++) {
            res[k++] = user1[j];
        }

        return res;
    }



    public static void main(String[] args) {
        LongestComSubstr longestComSubstr = new LongestComSubstr();
//        String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};
//        String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
//        String[] res = longestComSubstr.findLongestComSubstr(user1, user2);
//
//
//        String str1 = "tutorialhorizon";
//        String str2 = "dynamictutorialProgramming";
//        int res2 = longestComSubstr.findLongestLen(str1, str2);
//        System.out.println(res2);


        String str3 = "alibaba";
        String str4 = "ababila";
//        System.out.println(dp[4][2]);
//        System.out.println(str1.substring(0, 4));
//        System.out.println(str2.substring(0, 2));

        int res3 = longestComSubstr.findLongestLen(str3, str4);


    }


}
