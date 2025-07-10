package cn.hgplan.matrix;

import java.util.*;

public class Matrix {








    public static void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int m=matrix.length;
        int n=matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rows.contains(i)||cols.contains(j)){
                    matrix[i][j]=0;
                }
            }
        }

    }


    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        //定义当前未遍历的边界
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // Traverse from right to left along the bottom row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Traverse from bottom to top along the left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }



    
    public static void main(String[] args) {
//       int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
//       setZeroes(matrix);
//
//       for(int i=0;i<matrix.length;i++){
//           for(int j=0;j<matrix[0].length;j++){
//               System.out.print(matrix[i][j]+" ");
//           }
//           System.out.println();
//       }

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> res = new Matrix().spiralOrder(matrix);
        for(int i=0;i<res.size();i++){
            System.out.print(res.get(i)+" ");
        }
    }
}
