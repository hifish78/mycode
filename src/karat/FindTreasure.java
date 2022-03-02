package karat;

import java.util.ArrayList;
import java.util.List;

public class FindTreasure {
    /**
     * Find legal moves
     * 第一问就是给一个i和j，找出身边四个方向里为0的所有格子
     *
     *
     * 给一个二维matrix，-1代表墙，0代表路。问给定一个起点坐标为0，是否能到达所有的0。
     */


    public boolean reachable(int[][] grid, int x, int y) {
        int  m = grid.length;
        int n = grid[0].length;

        dfs(grid, x, y);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] != 0) {
            return;
        }
        grid[i][j] = 2;
        dfs(grid, i + 1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j -1);
    }

 /**
  * board3 中1代表钻石，给出起点和终点，问有没有一条不走回头路的路线，能从起点走到终点，并拿走所有的钻石，给出所有的最短路径。
  */
    /**
     * board3 中1代表钻石，给出起点和终点，问有没有一条不走回头路的路线，能从起点走到终点，并拿走所有的钻石，给出所有的最短路径。
     * @param grid
     * @param
     * @param
     * @return
     */
    public List<List<String>> findAllTreasures(int[][]grid, int r1, int c1, int r2, int c2){
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    count++;
                }
            }
        }
        List<List<String>> resList = new ArrayList<List<String>>();
        helper(grid, r1, c1, count, new ArrayList<String>(), resList, r2, c2);
        int min = Integer.MAX_VALUE;
        for(List<String> list : resList){
            min = Math.min(min, list.size());
        }
        List<List<String>> minresList = new ArrayList<List<String>>();
        for(List<String> list : resList){
            if( list.size() == min){
                minresList.add(list);
            }
        }

        return minresList;
    }
    private void helper(int[][]grid, int x, int y, int count, List<String> path, List<List<String>> res, int r2,
                        int c2){
        int m = grid.length;
        int n = grid[0].length;
        if(x < 0 || x >= m || y < 0 || y >= n){
            return ;
        }
        if(grid[x][y] != 0 && grid[x][y] != 1){
            return;
        }

        path.add("{" + x + "," + y+ ")");
        if(grid[x][y] == 1){
            count--;
        }
        if(x == r2 && y == c2){
            if(count == 0) {
                res.add(new ArrayList<String>(path));
            }
            // path.forEach(p-> {System.out.print(p);System.out.print("|");});
            path.remove(path.size() - 1);
            return;
        }
        //path.add("{" + x + "," + y+ ")");
        int temp =  grid[x][y];
        grid[x][y] = 2;

        helper(grid, x + 1, y, count, path, res, r2, c2);
        helper(grid, x  - 1, y, count, path, res, r2, c2);
        helper(grid,   x , y + 1,count , path, res, r2, c2);
        helper(grid, x, y -1, count, path, res, r2, c2);
        grid[x][y] = temp;
        if(grid[x][y] == 1){
            count++;
        }
        path.remove(path.size() - 1);

    }
    public static void main(String[] args){
        int[][] board1 = {
                { 1,  0,  0, 0, 0 },{  0, -1, -1, 0, 0 },{  0, -1,  0, 1, 0 },
                { -1,  0,  0, 0, 0 },{0,  1, -1, 0, 0 },{0,  0,  0, 0, 0 }};

        FindTreasure s18 = new FindTreasure();
        System.out.println("is reachable:" + s18.reachable(board1, 0, 1));
        int[][] board2 = {
                { 1,  0,  0, 0, 0 },{  0, -1, -1, 0, 0 },{  0, -1,  0, 1, 0 },
                { -1,  0,  0, 0, 0 },{0,  1, -1, 0, 0 },{0,  0,  0, 0, 0 }};
        s18.findAllTreasures(board2, 5, 0, 0, 4).forEach(e-> {e.forEach(n ->System.out.print(n));System.out.println("}");});

        int[][] board3 = {
                { 1,  0,  0, 0, 0 },{  0, -1, -1, 0, 0 },{  0, -1,  0, 1, 0 },
                { -1,  0,  0, 0, 0 },{0,  1, -1, 0, 0 },{0,  0,  0, 0, 0 }};
        s18.findAllTreasures(board3, 5, 2, 2, 0).forEach(e-> {e.forEach(n ->System.out.print(n));System.out.println("}");});

        int[][] board4 = {
                { 1,  0,  0, 0, 0 },{  0, -1, -1, 0, 0 },{  0, -1,  0, 1, 0 },
                { -1,  0,  0, 0, 0 },{0,  1, -1, 0, 0 },{0,  0,  0, 0, 0 }};
        s18.findAllTreasures(board3, 0, 0, 4, 1).forEach(e-> {e.forEach(n ->System.out.print(n));System.out.println("}");});
// [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (2, 2), (3, 2), (3, 1), (4, 1)]
        //  Or
        //  [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 2), (3, 1), (4, 1)]
    }

}
