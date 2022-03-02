package karat;

public class ALongestSeq {
    // https://leetcode-cn.com/problems/longest-common-subsequence/solution/fu-xue-ming-zhu-er-wei-dong-tai-gui-hua-r5ez6/
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] 代表 text1[0:i]([0, i-1)) 和 text2[0:j] ([0, j-1)) 的最长公共子序列，
        //当 text1[i] == text2[j] 时，dp[i][j] = dp[i-1][j-1]+1。否则 dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i -1) == text2.charAt(j -1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];

    }
}

