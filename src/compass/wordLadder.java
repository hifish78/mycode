package compass;

import java.util.*;

//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//        Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//        Explanation: There are 2 shortest transformation sequences:
//        "hit" -> "hot" -> "dot" -> "dog" -> "cog"
//        "hit" -> "hot" -> "lot" -> "log" -> "cog"
public class wordLadder {
    Map<String, List<String>> graph = new HashMap<>();
    Map<String, Integer> visited = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return res;
        }

        //create graph;
        set.add(beginWord);
        bfs(beginWord, endWord, set);
        dfs(beginWord, endWord, set, new ArrayList<>(), res);

        return res;
    }

    private void bfs(String beginWord, String endWord, Set<String> set) {
        for (String word : set) {
            graph.put(word, new ArrayList<>());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.put(beginWord, 0);
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                int curDist = visited.get(cur);
                List<String> nexts = getNexts(cur, set);

                for (String next : nexts) {
                    graph.get(cur).add(next);
                    if (!visited.containsKey(next)) {
                        visited.put(next, curDist + 1);
                        if (next.equals(endWord)) {
                            found = true;
                        } else {
                            queue.offer(next);
                        }
                    }
                }
            }

            if (found) {
                break;
            }
        }
    }

    private List<String> getNexts(String cur, Set<String> set) {
        List<String> res = new ArrayList<>();
        char[] arr = cur.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char old = arr[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                arr[i] = ch;
                String tmp = String.valueOf(arr);
                if (set.contains(tmp)) {
                    res.add(tmp);
                }
            }
            arr[i] = old;
        }
        return res;
    }

    private void dfs(String cur, String endWord, Set<String> set, List<String> list, List<List<String>> res) {
        list.add(cur);
        if (endWord.equals(cur)) {
            res.add(new ArrayList<>(list));
        } else {
            for (String next: graph.get(cur)) {
                if (visited.get(next) == visited.get(cur) + 1) {
                    dfs(next, endWord, set, list, res);
                }
            }
        }
        list.remove(list.size() - 1);
    }
}
