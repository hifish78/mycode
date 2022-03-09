package compass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 大概思路就是把那些随机节点放到一个set里面，然后把链表从头到尾跑一遍，如果当前节点不在set里面就说明有break，
 * 但是直到找到下一个在set里面的节点时再count++，需要注意的edge case就是头尾的break不算
 *  * 还有个优化思路（虽然也是O(N)）就是不看链表只看那些随机节点，如果一个随机节点的next不是随机节点，那就有break，不然没有，
 *  然后把节点是tail的情况考虑进去就完事儿了
 */
class Node{
    int val;
    Node next;
    public Node(int val){
        this.val = val;
    }
}
public class NodeBreak {

        public static int findBreak(Node head, List<Node> random){
            Set<Node> set  = new HashSet<Node>();
            for(Node node : random){
                set.add(node);
            }

            Node cur = head;
            int count = 0;
            while(cur != null){
                if(set.contains(cur) && !set.contains(cur.next)){
                    cur = cur.next;
                    while(cur != null && !set.contains(cur)) {
                        cur = cur.next;
                    }
                    if(set.contains(cur)) {
                        count++;
                    }
                }else {
                    cur = cur.next;
                }
            }
            return count;
        }

        public static void main(String[] args){
            Node n1 = new Node(1);
            Node n2 = new Node(1);
            Node n3 = new Node(1);
            Node n4 = new Node(1);
            Node n5 = new Node(1);
            Node n6 = new Node(1);
            n1.next = n2;
            n2.next = n3;
            n3.next = n4;
            n4.next = n5;
            n5.next = n6;
            List<Node> random = new ArrayList<Node>();
            random.add(n2);
            random.add(n3);
            random.add(n5);
            System.out.println(findBreak(n1, random));

            List<Node> random1 = new ArrayList<Node>();
            random1.add(n1);
            random1.add(n3);
            random1.add(n6);
            System.out.println(findBreak(n1, random1));


            List<Node> random2 = new ArrayList<Node>();
            random2.add(n1);
            random2.add(n2);
            random2.add(n3);
            random2.add(n4);
            random2.add(n5);
            System.out.println(findBreak(n1, random2));

        }
}
