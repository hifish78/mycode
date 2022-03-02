package c1;

import java.util.Stack;

public class LargestArea {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;


        for (int i = 0; i < heights.length; i++) {
            // 如果栈不为空且当前考察的新元素值比栈顶元素值小，表明以栈顶元素值为高的矩形的面积是可以确定的了。
            //该矩形的高就是栈顶元素值，其右侧边界就是当前考察的新元素，左侧边界是栈顶元素的前一个元素，
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int cur = stack.pop();
                int h = heights[cur];
                int left = stack.isEmpty() ? -1: stack.peek();
                int right = i - 1;
                res = Math.max(res, h * (right - left));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = heights.length - 1;
            res = Math.max(res, h * (right - left));
        }
        return res;
    }

    public static void main(String[] args) {
        LargestArea largestArea = new LargestArea();
        int[] heights = {2,1,5,6,2,3};
        int res = largestArea.largestRectangleArea(heights);
        System.out.println(res);
    }
}
