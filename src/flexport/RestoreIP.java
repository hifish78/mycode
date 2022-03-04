package flexport;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/restore-ip-addresses/submissions/
//12.45.67.200
// [0, i) + [i,j) + [j, k) + [K, len)

public class RestoreIP {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }

        //12.45.67.200
        // [0, i) + [i,j) + [j, k) + [K, len)
        int n = s.length();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++){
                for (int k = j + 1 ; k < n; k++) {
                    String str1 = s.substring(0, i);
                    String str2 = s.substring(i, j);
                    String str3 = s.substring(j, k);
                    String str4 = s.substring(k);
                    if (isValid(str1) && isValid(str2) && isValid(str3) && isValid(str4)) {
                        res.add(str1 + "." + str2 + "." + str3 + "." + str4);
                    }
                }
            }
        }
        return res;
    }

    public List<String> restoreIp(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        int n = s.length();
        List<String> list = new ArrayList<>();
        dfs(s, 0, list, res);
        return res;
    }

    private void dfs(String str, int index, List<String> ipList, List<String> res) {
        if (index == str.length() && ipList.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ipList.size(); i++) {
                if (sb.length() > 0) {
                    sb.append(".");
                }
                sb.append(ipList.get(i));
            }
            res.add(sb.toString());
            return;
        }

        if (index == str.length() || ipList.size() == 4) {
            return;
        }

        for (int i = 1; i < 4; i++) {
            int start = index;
            int end = index + i;
            if (end <= str.length()) {
                String tmp = str.substring(start, end);
                if (isValid(tmp)) {
                    ipList.add(tmp);
                    dfs(str, end, ipList, res);
                    ipList.remove(ipList.size() - 1);
                }
            }
        }
    }

    private boolean isValid(String str) {
        if (str.charAt(0) == '0' && str.length() > 1) {
            return false;
        }
        int val = Integer.valueOf(str);
        if (val < 0 || val > 255) {
            return false;
        }
        return true;
    }

    private void helper(String s, int index, List<String> path) {
        if(index == s.length()){
            //print
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String substr = s.substring(index, i + 1);
            path.add(substr);
            helper(s, i + 1, path);
            path.remove(path.size() - 1);
        }

    }

}
