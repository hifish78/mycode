package karat;

import sun.awt.image.ImageWatched;

import java.util.*;

public class FindParent {
    public List<Integer> findZeroOne(int[][] input) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] item : input) {
            map.put(item[1], map.getOrDefault(item[1], 0) + 1);
            map.put(item[0], 0);
        }

        List<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == 0 || map.get(key) == 1) {
                res.add(key);
            }
        }
        return res;
    }
    public List<Integer> findZeroOne2(int[][] input) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] item : input) {
            map.put(item[0], 0);
            map.put(item[1], 0);
        }

        for (int[] item: input) {
            map.put(item[1], map.get(item[1]) + 1);
        }

        for (int key: map.keySet()) {
            if (map.get(key) == 0 || map.get(key) == 1) {
                res.add(key);
            }
        }

        return res;
    }


    public boolean hasCommonAncestor(int[][] edges, int x, int y) {
        Map<Integer, Set<Integer>> dirParents = new HashMap<>();
        for (int[] edge : edges) {
            dirParents.putIfAbsent(edge[1], new HashSet<>());
            dirParents.get(edge[1]).add(edge[0]);
        }

        Set<Integer> xParent = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (dirParents.containsKey(cur)) {
                Set<Integer> allParents = dirParents.get(cur);
                for (int item : allParents) {
                    if (!xParent.contains(item)) {
                        xParent.add(item);
                        queue.offer(item);
                    }
                }
            }
        }

        Set<Integer> yParent = new HashSet<>();
        queue = new LinkedList<>();
        queue.offer(y);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (dirParents.containsKey(cur)) {
                Set<Integer> allParents = dirParents.get(cur);
                for (int item : allParents) {
                    if (!yParent.contains(item)) {
                        yParent.add(item);
                        queue.offer(item);
                    }
                }
            }
        }

        for (int xP: xParent) {
            if (yParent.contains(xP)) {
                return true;
            }
        }

        return false;
    }



    public static void main(String[] args) {
        FindParent findParent = new FindParent();
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
        List<Integer> res = findParent.findZeroOne(input);
//        List<Integer> res = findParent.findNodesWithZeroOrOneParent(input);
        System.out.println(res.toString());

        boolean res1 = findParent.hasCommonAncestor(input, 4,5);
        System.out.println(res1);

    }
}
