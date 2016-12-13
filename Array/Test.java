package Array;

import java.util.Arrays;

/**
 * Created by Taocr on 2016/11/5.
 */
public class Test {
    public static void main(String[] args){
//        int[] nums = {-2, 0, 0, 2 , 2};
        int[] nums = {3,2,1};
        NextPermutatioon.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
