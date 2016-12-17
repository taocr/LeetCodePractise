package Array;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import javafx.scene.transform.MatrixType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题：
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [[ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]]
 *  You should return [1,2,3,6,9,8,7,4,5].
 *  给定一个具有m行，n列的矩阵，按照螺旋的顺序将所有元素输出
 *
 *  思路：
 *  1、http://www.2cto.com/kf/201401/270133.html，非常好理解的版本
 * Created by Taocr on 2016/12/16.
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int j = colBegin; j <= colEnd; j ++) { //向右
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;//增长行开头

            for (int j = rowBegin; j <= rowEnd; j ++) {//向下
                res.add(matrix[j][colEnd]);
            }
            colEnd--;//增长列开头

            if (rowBegin <= rowEnd) {
                for (int j = colEnd; j >= colBegin; j --) {//向左
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                for (int j = rowEnd; j >= rowBegin; j --) {//向上
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }

        return res;
    }
}
