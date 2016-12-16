package Array;


import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * 问题：
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 * 给定个一个未排序整数数组，找到第一个丢失的正整数
 * 算法的时间复杂度为O(n)，空间复杂度为O(1)
 *
 * 思路：
 * 1、遍历一次数组，记录下最小值和最大值，并将数组的元素和得出，通过最小值和最大值算出等差数列的和，两数相减即为答案
 * 解法错误，数组内的数可以重复，因此这样不成立
 *
 * 更好的思路：
 * 1、将找到的正整数放在应该放的位置上，比如5就放在索引为4的位置上，
 * Created by Taocr on 2016/12/15.
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length){
            if(nums[i] == i+1 || nums[i] <= 0 || nums[i] > nums.length)
                i++;
            else if(nums[nums[i]-1] != nums[i])
                swap(nums, i, nums[i]-1);
            else i++;
        }
        i = 0;
        while(i < nums.length && nums[i] == i+1)
            i++;
        return i+1;
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
