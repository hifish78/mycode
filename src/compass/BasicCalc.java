package compass;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalc {
    public int calculate(String s) {
        Stack<Character> stack = new Stack<Character>();
        List<Long> result = new ArrayList<Long>();
        String numstr = "";
        boolean isN = false;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(isOP(ch)){
                if((ch == '-') && ((i == 0)  || isOP(s.charAt( i - 1)) || (s.charAt( i - 1) =='('))){
                    isN = true;
                    continue;
                }
                while(!stack.isEmpty() && (getPriorty(stack.peek()) <=  getPriorty(ch ))){
                    char curop = stack.pop();
                    long right = result.get(result.size() - 1);
                    result.remove(result.size() - 1);
                    long left = result.size() == 0? 0 : result.get(result.size() - 1);
                    if(result.size() > 0){
                        result.remove(result.size() - 1);
                    }
                    result.add(calucate( left,  right,  curop));

                }
                stack.push(ch);
            }else if(ch == '('){
                stack.push(ch);
            }else  if(ch == ')'){
                while(!stack.isEmpty() && (stack.peek() != '(')){
                    char curop = stack.pop();
                    long right = result.get(result.size() - 1);
                    result.remove(result.size() - 1);
                    long left = result.size() == 0? 0 : result.get(result.size() - 1);
                    if(result.size() > 0){
                        result.remove(result.size() - 1);
                    }
                    System.out.println("left:" + left + " right:" + right + " op:" + curop);
                    result.add(calucate( left,  right, curop));

                }
                stack.pop();
            }else{
                if(ch != ' '){
                    numstr = numstr + ch;
                }
                if(( i == s.length() - 1) || isOP(s.charAt(i + 1 )) ||(s.charAt(i + 1) == '(')
                        ||(s.charAt(i + 1) == ')')){
                    if(numstr.length() > 0){
                        long value = Long.valueOf(numstr);
                        if(isN){
                            value = -value;
                            isN = false;
                        }
                        result.add(value);
                        numstr = "";
                    }
                }

            }

        }
        while(!stack.isEmpty() ){
            char curop = stack.pop();
            long right = result.get(result.size() - 1);
            result.remove(result.size() - 1);
            long left = result.size() == 0? 0 : result.get(result.size() - 1);
            if(result.size() > 0){
                result.remove(result.size() - 1);
            }
            result.add(calucate( left,  right,  curop));

        }
        long lres = result.get(0);
        return (int)lres;
    }
    private boolean isOP(char ch){
        return (ch == '+') || (ch == '-') || (ch == '*')  || (ch == '/') ;
    }
    private long calucate(long left, long right, char op){
        if(op == '+'){
            return left + right;
        }else  if(op == '-'){
            return left - right;
        }else  if(op == '*'){
            return left * right;
        }else {
            return left / right;
        }
    }

    private int getPriorty(char op){
        if((op == '+') || (op == '-')){
            return 2;
        }else  if((op == '*') || (op == '/')){
            return 1;
        }else if(op == '('){
            return 3;
        }else if(op == ')'){
            return 0;
        }
        return 0;
    }
}
