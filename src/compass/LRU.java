package compass;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    class LRUCache {
        private class Node {
            Node prev;
            Node next;
            int key;
            int val;
            Node(int key, int val) {
                this.key = key;
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }

        private Map<Integer, Node> map = new HashMap<>();
        private int capacity;

        private Node head = new Node(-1, -1);
        private Node tail = new Node(-1, -1);
        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                if (size > capacity) {
                    Node tail = removeTail();
                    map.remove(tail.key);
                    size--;
                }
            } else {
                Node node = map.get(key);
                node.val = value;
                moveToHead(node);
            }
        }

        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private Node removeTail() {
            Node res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}
