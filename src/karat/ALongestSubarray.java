package karat;

import java.util.Arrays;

//https://www.jianshu.com/p/46f717dd614f


public class ALongestSubarray {
    public String[] findLongest(String[] arr1, String[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;
        //每个元素的值dp[i][j]代表的是子串s1的前i部分和子串s2的前j部分中最大公共子串的长度
        int[][] dp = new int[n1 + 1][n2 + 1];
        int maxLen = 0; //最长匹配的长度
        int maxIndex = 0; //最长匹配对应在s1中的最后一位
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (arr1[i].equals(arr2[j])) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1]  > maxLen) {
                        maxLen = dp[i+1][j+1];
                        maxIndex = i + 1;
                    }
                }
            }
        }

        System.out.println("maxLen:" + maxLen + "maxIndex:" + maxIndex );
        String[] res = new String[maxLen];
        int k = 0;
        for (int i = maxIndex - maxLen; i < maxIndex; i++, k++) {
            res[k] = arr1[i];
        }
        return res;
    }

    public static void main(String[] args){
        String[] arr1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] arr2 =  {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        ALongestSubarray s2 = new ALongestSubarray();
        String[] res = s2.findLongest(arr1, arr2);
        Arrays.stream(res).forEach(e -> System.out.println(e));

    }

}
