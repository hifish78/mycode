package c1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目二：大数相加再求平均，输入是链表 In the BigInt representation, numbers are stored as a linked list,
 * where each linked list element is a number between 0 and 999 that represents one order of magnitude in the BigInt.
 * The partial number might use all three digits (Ex: '123') or might have leading zeros (Ex: '5').
 *
 * # Example BigInts:
 * # 1,000 is stored as 1 -> 0
 * # 2,500,456 is stored as 2 -> 500 -> 456
 * # 2,005,456 is stored as 2 -> 5 -> 456
 * # a = 123 -> 4 -> 12 -> 999 -> 2
 * # b = 35 -> 234 -> 1 -> 1 -> 0
 * # c = 481 -> 241 -> 1 -> 1 -> 0
 * # calculateSum(a, b, c) = 639 -> 479 -> 15 -> 1 -> 2
 * # Explanation:
 * # 123,004,012,999,002 + 35,234,001,001,000 + 481,241,001,001,000
 * # = 639,479,015,001,002
 * # calculateMean(a, b, c) = 213 -> 159 -> 671 -> 667 -> 1
 * # Explanation:
 * # (123,004,012,999,002 + 35,234,001,001,000 + 481,241,001,001,000) / 3
 * # = 213,159,671,667,001
 */
class ListNode {
    String val;
    ListNode next;
    ListNode(String val) {
        this.val = val;
    }
}

public class AddBigNum {


    public static ListNode calculateSum(List<ListNode> nums) {
        ListNode dummy = new ListNode("-1");
        ListNode cur = dummy;

        ListNode[] arr = new ListNode[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] =  reverse(nums.get(i));
        }

        boolean done = false;
        int carry = 0;

        while (!done) {
            int sum = 0;
            done = true;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null) {
                    sum += Integer.valueOf(arr[i].val);
                    done = false;
                    arr[i] = arr[i].next;
                }
            }

            sum += carry ;
            carry =  sum / 1000;
            sum  = sum % 1000;
            // if missing "if (!done)" , will be "0 639 479 15 1 2", only need to add a new list node when not done
            if (!done) {
                String strSum = String.valueOf(sum);
                while (strSum.length() != 3) {
                    strSum = "0" + strSum;
                }
                cur.next = new ListNode(strSum);
                cur = cur.next;
            }

        }

        if (carry > 0) {
            cur.next = new ListNode(String.valueOf(carry));
        }

        return reverse(dummy.next);

    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
//           # a = 123 -> 4 -> 12 -> 999 -> 2
//           # b = 35 -> 234 -> 1 -> 1 -> 0
//           # c = 481 -> 241 -> 1 -> 1 -> 0
        AddBigNum addBigNum = new AddBigNum();
        List<ListNode> nums = new ArrayList<>();

        ListNode a = new ListNode("123");
        a.next = new ListNode("4");
        a.next.next = new ListNode("12");
        a.next.next.next = new ListNode("999");
        a.next.next.next.next = new ListNode("2");

        ListNode b = new ListNode("35");
        b.next = new ListNode("234");
        b.next.next = new ListNode("1");
        b.next.next.next = new ListNode("1");
        b.next.next.next.next = new ListNode("0");

        ListNode c = new ListNode("481");
        c.next = new ListNode("241");
        c.next.next = new ListNode("1");
        c.next.next.next = new ListNode("1");
        c.next.next.next.next = new ListNode("0");
        nums.add(a);
        nums.add(b);
        nums.add(c);
//        ListNode res = addBigNum.calculateSum(nums);
        ListNode res = AddBigNum.calculateSum(nums);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
        //# = 639,479,015,001,002

    }
}
