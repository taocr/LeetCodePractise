package Array;

import java.util.concurrent.ForkJoinPool;

/**
 * 问题：
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * 给定一个矩阵，如果一个元素是0，那么设置它的整个行和列都为0,
 *
 * 思路：
 * 1、问题在于0元素的记录问题，因为需要对矩阵进行遍历，但是不知道有几个0存在，因此需要记录下所有0的位置，然后按要求对矩阵进行置0工作
 * 记录用的数据结构可以选择两个数组，一个为m长度，一个为n长度，当搜索到矩阵中的一个数为0时将相应的行和列上的数组中的元素记录下来，最后根据两个数组进行置0
 * 使用的空间效率为O(m+n)，看题目描述还有更好的办法
 * 其实用int这些基本结构的每一位来标识更省空间，但是实现起来比起已经实现的这个变化不大，就不实现了
 *
 * 更好的思路
 * 1、存储空间固定，用矩阵自己来记录，在第一行和第一列记录下所有为0的元素对应的位置，相应位置上置0作为标记，从而不使用额外空间
 * Created by Taocr on 2016/12/13.
 */
public class SetMatrixZeroes {
    public static void setZeroes(int[][] matrix) {  // O(m+n)
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i] == true) {
                for (int index = 0; index < column.length; index++)
                    matrix[i][index] = 0;
            }
        }

        for (int j = 0; j < column.length; j++) {
            if (column[j] == true) {
                for (int index = 0; index < row.length; index++)
                    matrix[index][j] = 0;
            }
        }
    }

    public static void setZeroes2(int[][] matrix) { //O(1)
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}
