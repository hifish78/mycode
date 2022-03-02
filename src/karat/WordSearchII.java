package karat;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/word-search-ii/

class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (find(board, word)) {
                res.add(word);
            }
        }

        return res;
    }

    private boolean find(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (word.charAt(index) != board[i][j]) {
            return false;
        }

        if (visited[i][j] == true) {
            return false;
        }

        visited[i][j] = true;
        if (dfs(board, word, i + 1, j, index + 1, visited) || dfs(board, word, i - 1, j, index + 1, visited) ||
                dfs(board, word, i, j + 1, index + 1, visited) || dfs(board, word, i, j - 1, index + 1, visited)) {
            return true;
        }
        visited[i][j] = false;

        return false;

    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath", "pea","eat","rain"};
        List<String> res = sol.findWords(board, words);
        System.out.println(res.toString());
    }
}