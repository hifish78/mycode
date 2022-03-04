package flexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Telephone {
    Map<Character, char[]> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});



        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb, res);

        return res;
    }

    private void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char[] candidates = map.get(digits.charAt(index));
        for (char ch:candidates) {
            sb.append(ch);
            dfs(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() -1);
        }
    }
}
