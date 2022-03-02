package karat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/subdomain-visit-count/
//Input: cpdomains = ["9001 discuss.leetcode.com"]
//Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
public class ASubdomain {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();


        // google.mail.com 900
        for (String str : cpdomains) {
            int i = str.indexOf(' ');
            int cnt = Integer.parseInt(str.substring(0, i));
            String domain = str.substring(i + 1);
            map.put(domain, map.getOrDefault(domain, 0) + cnt);
            for (i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String key = domain.substring(i + 1);
                    map.put(key, map.getOrDefault(key, 0) + cnt);
                }
            }

        }

        for (String key : map.keySet()) {
            String str = map.get(key) + " " + key;
            res.add(str);
        }

        return res;
    }

}
