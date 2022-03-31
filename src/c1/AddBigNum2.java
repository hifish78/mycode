package c1;

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
 *
 * # Explanation:
 * # 123,004,012,999,002 + 35,234,001,001,000 + 481,241,001,001,000
 * # = 639,479,015,001,002
 * # calculateMean(a, b, c) = 213 -> 159 -> 671 -> 667 -> 1
 * # Explanation:
 * # (123,004,012,999,002 + 35,234,001,001,000 + 481,241,001,001,000) / 3
 * # = 213,159,671,667,001
 */


class Node {
    int val;
    Node next;
    Node(int val) {
        this.val = val;
    }
}


public class AddBigNum2 {
    public Node calSum(Node a, Node b, Node c) {
        Node newA = reverse(a);
        Node newB = reverse(b);
        Node newC = reverse(c);
        Node dummy = new Node(-1);
        Node cur = dummy;

        int carry = 0;
        boolean done = false;
        while (!done) {
            int sum = 0;
            done = true;
            if (newA != null) {
                sum += newA.val;
                done = false;
                newA = newA.next;
            }
            if (newB != null) {
                sum += newB.val;
                done = false;
                newB = newB.next;
            }
            if (newC != null) {
                sum += newC.val;
                done = false;
                newC = newC.next;
            }
            sum += carry;
            int val = sum % 1000;
            carry = sum / 1000;

            if (!done) {
                cur.next = new Node(val);
            }
            cur = cur.next;
        }
        return reverse(dummy.next);
    }

    public Node calcAvg(Node a, Node b, Node c) {
        Node sumCal = calSum(a, b, c);

        Node dummy = new Node(-1);
        Node cur = dummy;

        int remaining = 0;

        while (sumCal != null) {
            int total = remaining * 1000 + sumCal.val;
            int avg =  total / 3;
            remaining = total  % 3;
            cur.next = new Node(avg);

            cur = cur.next;
            sumCal = sumCal.next;
        }

        if (remaining != 0) {
            if (remaining / 3.0 >= 0.5) {
                cur.val = cur.val + 1;
            }
        }

        return dummy.next;
    }

    public Node reverse(Node head) {
        Node pre = null;
        while (head != null) {
            Node next = head.next;
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
        AddBigNum2 addBigNum2 = new AddBigNum2();


        Node a = new Node(123);
        a.next = new Node(4);
        a.next.next = new Node(12);
        a.next.next.next = new Node(999);
        a.next.next.next.next = new Node(2);

        Node b = new Node(35);
        b.next = new Node(234);
        b.next.next = new Node(1);
        b.next.next.next = new Node(1);
        b.next.next.next.next = new Node(0);

        Node c = new Node(481);
        c.next = new Node(241);
        c.next.next = new Node(1);
        c.next.next.next = new Node(1);
        c.next.next.next.next = new Node(0);

//        Node res = addBigNum2.calSum(a, b, c);
//        while (res != null) {
//            System.out.print(res.val + " ");
//            res = res.next;
//        }

        Node res2 = addBigNum2.calcAvg(a, b, c);
        while (res2 != null) {
            System.out.print(res2.val + " ");
            res2 = res2.next;
        }
        //# = 639,479,015,001,002
        //avg:  213 159 671 667 001

    }
}
