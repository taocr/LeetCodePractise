package Array;

/**
 * 问题：
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,Given input array nums = [1,1,2],Your function should return length = 2, with the first two elements of
 * nums being 1 and 2 respectively
 * 删去已排序数组中的重复元素，最后返回为不重复数组元素的长度，且数组中不重复的元素要按顺序全部放置在全前面
 *
 * 思路：
 * 1、记录下数组长度和当前要替换的元素索引(初始为1)，从索引为1的数开始遍历，当碰到任何一个数和其前面的数相等就继续遍历并、
 * 将len-1,不相等则将当前数与要替换的索引指向的数进行替换，并将要替换的索引自增1
 * Created by Taocr on 2016/12/14.
 */
public class RemoveDuplicatesfromSortedArray {
    public static int removeDuplicate(int[] nums) {
        int len = nums.length;
        int currentElement = 1;
        for (int i = 1; i < nums.length; i++) {
            if (i < nums.length) {
                nums[currentElement++] = nums[i];
            }
        }
        return len;
    }
}
