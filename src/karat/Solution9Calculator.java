package karat;

import java.util.Stack;

public class Solution9Calculator {
    //-200 + 3 -100
    //200 + 3 - 100
//    给输入为string，例如"2+3-999"，之包含+-操作，返回计算结果。
    public int calculator(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = 0;
        int sign = 1;
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0'); // 200
            } else if (ch == '+') {
                res += sign * num;
                sign = 1;
                num = 0;
            } else if( ch == '-') {
                res += sign * num;
                sign = -1;
                num = 0;
            }
        }

        res += sign * num;
        return res;
    }

    public int calculator2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = 0;
        int sign = 1;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                String num = ch + "";
                while (i + 1 < str.length() && Character.isDigit(str.charAt(i+1))) {
                    num += str.charAt(i+1);
                    i++;
                }
                res += Integer.parseInt(num) * sign;
            } else if (ch == '+') {
                sign = 1;
            } else if( ch == '-') {
                sign = -1;
            }
        }
        return res;
    }

    public int calculator3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int res = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                String num = ch + "";
                while ( i + 1 < str.length() && Character.isDigit(str.charAt(i + 1))) {
                    num += str.charAt(i+1);
                    i++;
                }
                res = res + Integer.parseInt(num) * sign;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                int preSign = stack.pop();
                int preRes = stack.pop();
                res = preRes + preSign * res;
            }
        }
        return res;
    }




    public static void main(String[] args){

        Solution9Calculator s9 = new Solution9Calculator();
//        String s1 = "2+3-999";
//        System.out.println(s9.calculator2(s1));
//
//        String s2 = "2-3+10";
//        System.out.println(s9.calculator2(s2));
//
//        String s3 = "-2-3+10";
//        System.out.println(s9.calculator2(s3));

        String s4 = "2+((8+2)+(3-999))";
        System.out.println(s9.calculator3(s4));
    }

}
