package Array;

import java.util.Arrays;

/**
 * 问题：
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * 给定一个用数组表示的非负数，给这个数加1
 * Created by Taocr on 2016/12/14.
 */
public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int carry = 1;//进位标志，且因为数组要加1，所以直接放在carry中
        for (int i = digits.length - 1; i >=0 ; i--) {
            int sum = digits[i]  + carry;
            digits[i] = sum%10;

            if ((carry = sum/10) == 0)
                break;
        }

        if (carry != 0) {
            int[] result = new int[digits.length + 1];
            System.arraycopy(digits, 0, result, 1, digits.length);
            result[0] = carry;
            return result;
        }

        return digits;
    }
}
