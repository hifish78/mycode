package karat;

import java.util.HashSet;
import java.util.Set;

public class Matrix {
//    给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。
    public boolean isValidMatrixError(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i  < n; i++) {
            Set<Integer> setRow = new HashSet<>();
            Set<Integer> setCol = new HashSet<>();
            int rowMin = Integer.MAX_VALUE;
            int rowMax = Integer.MIN_VALUE;
            int colMin = Integer.MAX_VALUE;
            int colMax = Integer.MIN_VALUE;

            for (int j = 0; j < n; j++) {
                if (!setRow.contains(matrix[i][j])) {
                    setRow.add(matrix[i][j]);
                    rowMin = Math.min(rowMin, matrix[i][j]);
                    rowMax = Math.max(rowMax, matrix[i][j]);
                } else {
                    return false;
                }

                if (!setCol.contains(matrix[i][j])) {
                    setCol.add(matrix[i][j]);
                    colMin = Math.min(colMin, matrix[i][j]);
                    colMax = Math.max(colMax, matrix[i][j]);
                } else {
                    return false;
                }
            }
            if (rowMin != 1 || rowMax != n || colMin != 1 || colMax != n) {
                return false;
            }

        }
        return true;
    }

    public boolean isValidMatrix(int[][] grid) {
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (grid[i][j] < 1 || grid[i][j] > n) {
                    return false;
                }
                if (!set.contains(grid[i][j])) {
                    set.add(grid[i][j]);
                } else {
                    return false;
                }
            }
//            if (set.size() != n) {
//                return false;
//            }
        }

        for (int j = 0; j < n; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (grid[i][j] < 1 && grid[i][j] > n) {
                    return false;
                }
                if (!set.contains(grid[i][j])) {
                    set.add(grid[i][j]);
                } else {
                    return false;
                }
            }
//            if (set.size() != n) {
//                return false;
//            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {2,3, 1}, {3, 1, 2}};
        Matrix matrix = new Matrix();
        System.out.println(matrix.isValidMatrix(grid));
    }



}
