package flexport;

//        注意： .  $  | 和 * 等转义字符，必须得加 \\。
//        public String[] split(String regex, int limit) --> limit是分割的份数
//        https://zhuanlan.zhihu.com/p/45151144
//limit参数
//        该参数用于控制匹配的次数. public String[] split(String regex)为limit为0的情况.
//        limit > 0 : 最多匹配limit-1次,得到一个长度为limit的数组. String[limit-1]为余下未匹配的字符串
//        limit = 0 : 尽可能的多匹配, 且长度无限制, 但结尾空字符串将被丢弃
//        limit < 0 : 尽可能的多匹配, 且长度无限制
//str = "1.1.1.1." , arr[] = str.split("//.") , arr.length == 4 since the ending empty string is discarded

public class ValidIP {
    public String validIPAddress(String queryIP) {
        if (isValidIpv4(queryIP)) {
            return "IPv4";
        }
        if (isValidIpv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isValidIpv4(String queryIP) {
        // must have "\\.', also must have "-1", otherwise the case fail 1.1.1.1.
        String[] strs = queryIP.split("\\.", -1);
        if (strs.length != 4) {
            return false;
        }
        for (String str : strs) {
            if (str.length() < 1 || str.length() > 4) {
                return false;
            }
            if (str.length() > 1 && str.charAt(0) == '0') {
                return false;
            }
            for (char ch: str.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }

            int val = Integer.parseInt(str);
            if (val < 0 || val > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIpv6(String queryIP) {
        String base16 = "0123456789abcdefABCDEF";
        String[] strs = queryIP.split(":", -1);
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }

        if  (strs.length != 8) {
            return false;
        }

        for (String str :  strs) {
            if (str.length() < 1 || str.length() > 4) {
                return false;
            }
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (base16.indexOf(ch) == -1) {
                    return false;
                }
            }
        }
        return true;

    }
}
