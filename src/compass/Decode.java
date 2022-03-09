package compass;

import java.util.Stack;

public class Decode {
    public String decodeString(String s) {
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackNum = new Stack<>();
        // 3[a2[c]]
        int curNum = 0;
        String curStr = "";

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                curNum = curNum * 10 + Integer.parseInt(ch + "");
            } else if (Character.isLetter(ch)) {
                curStr += ch;
            } else if (ch == '[') {
                stackStr.push(curStr);
                stackNum.push(curNum);
                curStr = "";
                curNum = 0;
            } else if ( ch == ']') {
                int preNum = stackNum.pop();
                String preStr = stackStr.pop();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < preNum; i++) {
                    sb.append(curStr);
                }

                curStr = preStr + sb.toString();
            }
        }

        return curStr;

    }

    public static void main(String[] args) {
        Decode decode = new Decode();
        String s = "3[a2[c]]";
        String res = decode.decodeString(s);
        System.out.println(res);


    }
}
