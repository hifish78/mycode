package karat;
/*

https://leetcode.com/problems/word-search/


After catching your classroom students cheating before, you realize your students are
getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid,
and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of
coordinates. If there are multiple matches, return any one.

grid1 = [
	['c', 'c', 'x', 't', 'i', 'b'],
	['c', 'c', 'a', 't', 'n', 'i'],
	['a', 'c', 'n', 'n', 't', 't'],
	['t', 'c', 's', 'i', 'p', 't'],
	['a', 'o', 'o', 'o', 'a', 'a'],
	['o', 'a', 'a', 'a', 'o', 'o'],
	['k', 'a', 'i', 'c', 'k', 'i'],
]
word1 = "catnip"
word2 = "cccc"
word3 = "s"
word4 = "bit"
word5 = "aoi"
word6 = "ki"
word7 = "aaa"
word8 = "ooo"

grid2 = [['a']]
word9 = "a"

// new int[2]

find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
       [(0, 1), (1, 1), (2, 1), (3, 1)]
    OR [(0, 0), (1, 0), (1, 1), (2, 1)]
    OR [(0, 0), (0, 1), (1, 1), (2, 1)]
    OR [(1, 0), (1, 1), (2, 1), (3, 1)]
find_word_location(grid1, word3) => [(3, 2)]
find_word_location(grid1, word4) => [(0, 5), (1, 5), (2, 5)]
find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => [(6, 4), (6, 5)]
find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
find_word_location(grid2, word9) => [(0, 0)]

Complexity analysis variables:

r = number of rows
c = number of columns
w = length of the word

*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FindWordLoc {
    int[][] dirs = {{1, 0}, {0, 1}};
    public List<int[]> findWord(char[][] grid, String word) {
       List<List<int[]>> res = new ArrayList<>();
       for (int i  = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[0].length; j++) {
               if (grid[i][j] == word.charAt(0)) {
                   List<int[]> list = new ArrayList<>();
                   dfs(grid, i, j, 0, word, list, res);
               }
           }
       }

       if (res.size() > 0) {
           return res.get(0);
       }

       return new ArrayList<int[]>();
    }

//    word1 = "catnip"
//    find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
    private void dfs(char[][] grid, int i, int j, int index, String word, List<int[]> list, List<List<int[]>> res) {
        if (index == word.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        int m = grid.length;
        int n = grid[0].length;

        if ( i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }

        if (grid[i][j] != word.charAt(index)) {
            return;
        }

        list.add(new int[]{i, j});
        dfs(grid, i + 1, j, index + 1, word, list, res);
        dfs(grid, i, j+ 1, index + 1, word, list, res);
        list.remove(list.size() - 1);
    }


    public static void main(String[] args) {
        FindWordLoc sol = new FindWordLoc();
        char[][] grid = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'c', 'a', 't', 'n', 'i'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'c', 's', 'i', 'p', 't'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'o', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'c', 'k', 'i'},
        };

        String word1 = "catnip";
        String word2 = "cccc";
        String word3 = "s";
        String word4 = "bit";
        String word5 = "aoi";
        String word6 = "ki";
        String word7 = "aaa";
        String word8 = "ooo";

        List<int[]> res = sol.findWord(grid, word8);
        for (int i = 0; i < res.size(); i++) {
            System.out.print("(" + res.get(i)[0] + "," + res.get(i)[1] + ") ");
        }
        System.out.println();

    }

}
