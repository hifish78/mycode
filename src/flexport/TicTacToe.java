package flexport;
//https://leetcode.com/problems/design-tic-tac-toe/
public class TicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int n;

    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];

    }

    public int move(int row, int col, int player) {
        int toAdd = (player == 1) ? 1 :  -1;
        rows[row] += toAdd;
        cols[col] += toAdd;

        if (row == col) {
            diagonal += toAdd;
        }
        if (row + col == n - 1) {
            antiDiagonal += toAdd;
        }

        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n
                || Math.abs(antiDiagonal) == n || Math.abs(diagonal) == n) {
            return player;
        }
        return 0;

    }
}
