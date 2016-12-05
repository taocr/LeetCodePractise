package Array;

import java.util.Arrays;

/**
 * 题目：
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must
 * be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * 给定一个已经排好序的升序整数数组，找到两个数，其和为指定的数，返回值为两个数的索引，且第一个索引值必须比第二个角标值小，注意返回的索引不是从0开始计数的，可以嘉定每个输入都只有一个答案
 *
 * 思路：
 * 1、跟TwoSum中的第三条思路完美契合，排序都不需要了，定义两个指针，一个从头开始一个从尾部开始，用指针进行夹逼，对于首尾两个指针指向的元素
 * 的和小于target的情况，左侧的指针自增1，对于大于target的情况，右侧的指针自减1
 * 原本存在的排序后角标乱掉的情况，这题已经排好序了那么就不会乱了
 *
 * 效率很高，时间复杂度为O(n),空间复杂度为O(1)
 *
 * 更好的方法（其他方法，思路已经很高效了）：
 * 1、找到了一样的方法=。=！
 * 2、用二分查找的方法，因为已经排好序所以二分查找的方法十分有效率。
 * 存在问题，对于数组中存在相同元素的情况下，无法很好地去输出两个元素的位置，
 * 因为二分搜索是稳定的搜索方法，即每次搜索到的角标都与第一次搜索到的角标相同，那么问题就出来了，如果两个元素相同，那么如何将两个元素的位置都找到？
 * 没有想到如何解决，可能需要针对排序后相同元素的数都在一起，从而判断出有几个相同的数，然后拿出两个作为输出
 * * Created by Taocr on 2016/11/9.
 */
public class TwoSum2 {
    public static int[] twoSum(int[] numbers, int target){
        int low = 0;
        int high = numbers.length-1;
        int sum = 0;

        while (low < high) {
            sum = numbers[low] + numbers[high];

            if (sum == target){
                return new int[] {low + 1, high + 1};
            } else if (sum < target){
                low++;
            } else {
                high--;
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] numbers, int target){
        for (int i = 0; i < numbers.length; i++) {
            int secondIndex = Arrays.binarySearch(numbers, target - numbers[i]);
            if(secondIndex >= 0)
                return new int[] {i+1,secondIndex+1};
        }
        return null;
    }
}
