package karat;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) find one rectangles
 * there is an image filled with 0s and 1s. There is at most one rectangle in this image filled with 0s, find the
 * rectangle. Output could be the coordinates of top-left and bottom-right elements of the rectangle, or top-left
 * element, width and height.
 *
 * 2) find all rectangle
 *for the same image, it is filled with 0s and 1s. It may have multiple rectangles filled with 0s. The rectangles are
 *  separated by 1s. Find all the rectangles.
 *  3) find all shapes
 *the image has random shapes filled with 0s, separated by 1s. Find all the shapes. Each shape is represented by
 * coordinates of all the elements inside.
 *
 */

public class FindRectangle {
    /* 1) find one rectangles
 * there is an image filled with 0s and 1s. There is at most one rectangle in this image filled with 0s, find the
 * rectangle. Output could be the coordinates of top-left and bottom-right elements of the rectangle, or top-left
 * element, width and height.
 */
    public int[][] findOneRectangle(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[2][2]; // [1, 3], [2, 4]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int row = i;
                    int col = j;
                    res[0] = new int[]{i, j};
                    while (col < n && grid[i][col] == 0) {
                        col++;
                    }
                    while (row < m && grid[row][col-1] == 0) {
                        row++;
                    }
                    res[1] = new int[]{row - 1, col - 1};
                    return res;
                }
            }
        }

        return res;
    }

    public List<int[]> findOneRectangle2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
//        int[][] res = new int[2][2]; // [1, 3], [2, 4]

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res.add(new int[]{i, j});
                    int height = 1;
                    int width = 1;
                    while (i + height < m && grid[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < n && grid[i][j+width] == 0) {
                        width++;
                    }
                    res.add(new int[]{i + height - 1, j + width - 1});
                    //return res;
                    break;
                }
            }
            if (res.size()!= 0) {
                break;
            }

        }

        return res;
    }

    public List<List<int[]>> findRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<int[]>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    List<int[]> tmp = new ArrayList<>();
                    tmp.add(new int[]{i, j});
                    int height = 1;
                    int width = 1;
                    while (i + height < m && grid[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < n && grid[i][j + width] == 0) {
                        width++;
                    }
                    tmp.add(new int[]{i + height - 1, j + width - 1});
                    if (tmp.size() == 2) {
                        res.add(tmp);
                    }
                    for (int x = i; x <= i + height - 1; x++) {
                        for (int y = j; y <= j + width - 1; y++) {
                            grid[x][y] = 1;
                        }
                    }

                }
            }
        }
        return res;
    }

//    the image has random shapes filled with 0s, separated by 1s. Find all the shapes. Each shape is represented by coordinates of all the elements inside.
    public List<List<int[]>> findAllshapes(int[][] grid) {
        List<List<int[]>> res = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    List<int[]> list = new ArrayList<>();
                    dfs(grid, i, j, list);
                    res.add(list);
                }
            }
        }
        return res;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void dfs(int[][] grid, int x, int y, List<int[]> res) {
            int m = grid.length;
            int n = grid[0].length;
            if (x < 0 || x >= m || y < 0 || y >= n){
                return;
            }
            if(grid[x][y] == 1){
                return;
            }
            grid[x][y] = 1;
            res.add(new int[]{x, y});
            dfs(grid, x + 1, y, res);
            dfs(grid, x - 1, y, res);
            dfs(grid, x , y + 1, res);
            dfs(grid, x , y -1, res);
            
    }

    public static void main(String[] args){
        FindRectangle s6 = new FindRectangle();
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1,},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1}};
          List<List<int[]>> res = s6.findRectangles(grid);
//        List<int[]> res = s6.findOneRectangle2(grid);
//        List<List<int[]>> res = s6.findAllshapes(grid);

//        for (int[] r : res) {
//            System.out.println(r[0] + "," + r[1]);
//        }

        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.println(res.get(i).get(j)[0] + "," + res.get(i).get(j)[1]);
            }
            System.out.println();
        }
    }
}
