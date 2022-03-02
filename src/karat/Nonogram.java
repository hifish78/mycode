package karat;

import java.util.ArrayList;
import java.util.List;

/*
A nonogram is a logic puzzle, similar to a crossword, in which the player is given a blank grid and has to color it according to some instructions.
Specifically, each cell can be either black or white, which we will represent as 0 for black and 1 for white.

+------------+
| 1  1  1  1 |
| 0  1  1  1 |
| 0  1  0  0 |
| 1  1  0  1 |
| 0  0  1  1 |
+------------+

For each row and column, the instructions give the lengths of contiguous runs of black (0) cells.
For example, the instructions for one row of [ 2, 1 ] indicate that there must be a run of two black cells,
followed later by another run of one black cell, and the rest of the row filled with white cells.

These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.

Your job is to write a function to validate a possible solution against a set of instructions. Given a 2D matrix representing a player's solution;
and instructions for each row along with additional instructions for each column; return True or False according to whether both sets of instructions match.

Example instructions #1

matrix1 = [[1,1,1,1],
           [0,1,1,1],
           [0,1,0,0],
           [1,1,0,1],
           [0,0,1,1]]
rows1_1    =  [], [1], [1,2], [1], [2]
columns1_1 =  [2,1], [1], [2], [1]
validateNonogram(matrix1, rows1_1, columns1_1) => True

Example solution matrix:
matrix1 ->
                                   row
                +------------+     instructions
                | 1  1  1  1 | <-- []
                | 0  1  1  1 | <-- [1]
                | 0  1  0  0 | <-- [1,2]
                | 1  1  0  1 | <-- [1]
                | 0  0  1  1 | <-- [2]
                +------------+
                  ^  ^  ^  ^
                  |  |  |  |
  column       [2,1] | [2] |
  instructions      [1]   [1]


Example instructions #2

(same matrix as above)
rows1_2    =  [], [], [1], [1], [1,1]
columns1_2 =  [2], [1], [2], [1]
validateNonogram(matrix1, rows1_2, columns1_2) => False

The second and third rows and the first column do not match their respective instructions.

Example instructions #3

matrix2 = [
[ 1, 1 ],
[ 0, 0 ],
[ 0, 0 ],
[ 1, 0 ]
]
rows2_1    = [], [2], [2], [1]
columns2_1 = [1, 1], [3]
validateNonogram(matrix2, rows2_1, columns2_1) => False

The black cells in the first column are not separated by white cells.

n: number of rows in the matrix
m: number of columns in the matrix
"""

        matrix1 = [
        [1,1,1,1], # []
        [0,1,1,1], # [1] -> a single run of _1_ zero (i.e.: "0")
        [0,1,0,0], # [1, 2] -> first a run of _1_ zero, then a run of _2_ zeroes
        [1,1,0,1], # [1]
        [0,0,1,1], # [2]
        ]

        # True
        rows1_1 = [[],[1],[1,2],[1],[2]]
        columns1_1 = [[2,1],[1],[2],[1]]
        # False
        rows1_2 = [[],[],[1],[1],[1,1]]
        columns1_2 = [[2],[1],[2],[1]]

        matrix2 = [
        [1,1],
        [0,0],
        [0,0],
        [1,0]
        ]
        # False
        rows2_1 = [[],[2],[2],[1]]
        columns2_1 = [[1,1],[3]]

*/
public class Nonogram {
    public boolean isValidNonogram(int[][] grid, int[][] rows, int[][] cols) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 0 || m != rows.length || n != cols.length) {
            return false;
        }

        boolean isValidRows = isValidNonogramRows(grid, rows);
        boolean isValidCols = isValidNonogramCols(grid, cols);
        return isValidRows && isValidCols;
    }
/*
    matrix1 ->
    row
            +------------+     instructions
            | 1  1  1  1 | <-- []
            | 0  1  1  1 | <-- [1]
            | 0  1  0  0 | <-- [1,2]
            | 1  1  0  1 | <-- [1]
            | 0  0  1  1 | <-- [2]
            +------------+
            ^  ^  ^  ^
            |  |  |  |
    column   [2,1] | [2] |
    instructions      [1]   [1]
 */
    private boolean isValidNonogramRows(int[][] grid, int[][] rows) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            List<Integer> rowsCnt = new ArrayList<>();
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                while (j < n && grid[i][j] == 0) {
                    cnt++;
                    j++;
                }
                if (cnt != 0) {
                    rowsCnt.add(cnt);
                    cnt = 0;
                }
            }
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 0) {
//                    cnt++;
//                } else {
//                    if (cnt != 0) {
//                        rowsCnt.add(cnt);
//                        cnt = 0;
//                    }
//                }
//            }
//            // 0 1 0 0, 最后两个0
//            if (cnt != 0) {
//                rowsCnt.add(cnt);
//            }

            if (rowsCnt.size() != rows[i].length) {
                return false;
            }
            for (int k = 0; k < rows[i].length; k++) {
                if (rowsCnt.get(k) != rows[i][k] ) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidNonogramCols(int[][] grid, int[][] cols) {
        int m = grid.length;
        int n = grid[0].length;

        for (int j = 0; j < n; j++) {
            List<Integer> colsCnt = new ArrayList<>();
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                while (i < m && grid[i][j] == 0) {
                    cnt++;
                    i++;
                }
                if (cnt != 0) {
                    colsCnt.add(cnt);
                    cnt = 0;
                }
            }

            if (colsCnt.size() != cols[j].length) {
                return false;
            }

            for (int k = 0; k < cols[j].length; k++ ) {
                if (colsCnt.get(k) !=  cols[j][k]) {
                    return false;
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {
        int[][] matrix1 = { { 1, 1, 1, 1 },{ 0, 1, 1, 1 },{ 0, 1, 0, 0 },{ 1, 1, 0, 1 },{ 0, 0, 1, 1 }};
        int[][] rows1 = {{},{1},{1,2},{1},{2}};
        int[][] columns1 = {{2,1},{1},{2},{1}};
        Nonogram nonogram = new Nonogram();
        System.out.println(nonogram.isValidNonogram(matrix1, rows1, columns1)); //true

        int[][] rows2 = {{},{},{1},{1},{1,1}};
        int[][] columns2 = {{2},{1},{2},{1}};
        System.out.println(nonogram.isValidNonogram(matrix1, rows2, columns2)); //false

    }
}
