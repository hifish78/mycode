package walmart;

/**
 * https://leetcode.com/problems/implement-strstr/
 * 题目是很简单的给两个string s1，s2. 找 first index of s1 which has a s2.
 * 如s1=“abctargetdef” s2=“target”，返回index 3.
 * 另s2里面可以有wild card * 可以代替任何一个letter，exp s2=“ta*get”，一样返回index3
 */
public class FindIndex {
    public int findIndex(String s1, String s2) {
        if (s2 == null) {
            return -1;
        }
        int m = s1.length();
        int n = s2.length();
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            while ( j < n) {
                if (s1.charAt(i + j) != s2.charAt(j)) {
                    break;
                } else {
                    j++;
                }
            }

            if ( j == n) {
                return i;
            }
        }
        return -1;
    }

    public int findIndex2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return -1;
        }

        int m = s1.length();
        int n = s2.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (s1.charAt(i) == s2.charAt(j) || s2.charAt(j) == '*') {
                i++;
                j++;
                if (j == n) {
                    return i - j;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
            //Time Limit exceeded
//            if (j == n) {
//                return i;
//            }
        }
        return -1;

    }



    public static void main(String[] args) {
        FindIndex findIndex = new FindIndex();
        String s1="abctargetdef";
        String s2="*arget";
        int res = findIndex.findIndex2(s1, s2);
        System.out.println(res);
    }
}
