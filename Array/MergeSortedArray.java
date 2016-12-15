package Array;

/**
 * 问题：
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from
 * nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 给定两个已排序数组，将两个数组合成一个已排序数组，这个数组放在nums1中，其有足够的空间
 *
 * 思路：
 * 1、诀窍在于从后往前遍历数组，从而不会使得合并时将nums1的元素覆盖掉
 * Created by Taocr on 2016/12/14.
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        for(m = m-1,n = n - 1; m>=0 && n>=0;) {
            if (nums1[m] >= nums2[n]) {
                nums1[index] = nums1[m];
                m--;
            }else {
                nums1[index] = nums2[n];
                n--;
            }
            index--;
        }

        if (m < 0) {
            for (;n >= 0;n--, index--)
                nums1[index] = nums2[n];
        } else if (n < 0) {
            for (;m >=0;m--, index--)
                nums1[index] = nums1[m];
        }
    }
}
