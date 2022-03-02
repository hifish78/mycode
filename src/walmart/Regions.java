package walmart;
//https://leetcode.com/problems/surrounded-regions/

/**
 * * 给定一个01矩阵，把所有不和边界相连的1联通块都变成0.
 *  * 普通DFS就行，可能是基础不扎实写的不够顺利吧。
 *  *
 */
public class Regions {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n -1) {
                    if (board[i][j] == '1') {
                        dfs(board, i, j);
                    }
                }

            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = '0';
                } else if (board[i][j] == '2') {
                    board[i][j] = '1';
                }
            }
        }
    }
    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] != '1') {
            return;
        }
        board[i][j] = '2';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
        //board[i][j] = 'O';
    }


}
