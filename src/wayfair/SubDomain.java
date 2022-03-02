package wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input: cpdomains = ["9001 discuss.leetcode.com"]
 * Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 *
 * Explanation: We only have one website domain: "discuss.leetcode.com".
 * As discussed above, the subdomain "leetcode.com" and "com" will also be visited.
 * So they will all be visited 9001 times.
 *
 * Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 */
public class SubDomain {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : cpdomains) {
            int idx = str.indexOf(" ");
            //also works for idx = domain.indexOf(' ');
            int cnt = Integer.parseInt(str.substring(0, idx));
            String domain = str.substring(idx + 1);
            map.put(domain, map.getOrDefault(domain, 0) + cnt);

            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String key = domain.substring(i + 1);
                    map.put(key, map.getOrDefault(key, 0) + cnt);
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key) + " " + key);
        }

        return res;

    }

    public static void main(String[] args) {
        SubDomain subDomain = new SubDomain();
        String[] cpdomains = {"9001 discuss.leetcode.com"};
        List<String> res =subDomain.subdomainVisits(cpdomains);
        System.out.println(res.toString());

        String[] cpdomains2 ={"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> res2 =subDomain.subdomainVisits(cpdomains2);
        System.out.println(res2.toString());
    }

}
