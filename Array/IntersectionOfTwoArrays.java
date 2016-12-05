package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * Given two arrays, write a function to compute their intersection.
 * Each element in the result must be unique.
 * The result can be in any order.
 *
 * 给定两个数组，计算出两个数组重合的部分组成的数组，即都有的元素
 * 输出的数组中，所有的元素都只能出现一次
 * 结果可以是任何顺序的
 *
 * 思路：
 * 1、蛮力破解，对第一个数组进行遍历，每个元素跟第二个数组中每个元素进行比对
 *
 * 2、给一个数组遍历完成一个哈希表，再遍历一次另一个数组，一个个进行哈希搜索即可，因为结果的数为独一无二的，因此HashSet很适合
 * 最后很麻烦，如何将一个存的元素为Integer的ArrayList转换为一个int[]数组，采用的方法是先转换为Integer[]的数组，然后遍历进行转换。
 * 效率较低
 *
 *更好的方法：
 * 1、使用两个HashSet，不过思想还是一样，不过HashSet针对这题的要求来说确实比Arraylist更适合，因为结果的顺序随便，而且使用HashSet就不用去判断ArrayList中是否有存有相同的元素了
 * 时间复杂度为O(n),效率较高
 *
 * 2、对两个数组进行排序，之后用两个指针分别从两个数组的头开始比起，那个数组的元素小于另一个了就指针自增1，相同的话就添加到HashSet中并两个指针都自增1
 * 时间复杂度为O(nlogn)
 *
 * 3、二分搜索
 * 这个需要将要搜索的那个数组进行排序，遍历一个数组，在另一个数组中查找，与上面那个我觉得没什么太大的优势，其他还是一样
 * 时间复杂度为O(nlogn)
 * Created by Taocr on 2016/11/9.
 */
public class IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2){
        ArrayList<Integer> al = new ArrayList<Integer>();
        HashSet<Integer> hs = new HashSet<Integer>();

        for (int i = 0; i < nums2.length; i++)
            hs.add(nums2[i]);

        for (int i = 0; i < nums1.length; i++) {
            if (hs.contains(nums1[i]) && !al.contains(nums1[i]))
                al.add(nums1[i]);
        }
        int[] answer = new int[al.size()];
        Integer[] tmp = new Integer[al.size()];
        al.toArray(tmp);
        for (int i = 0; i < answer.length; i++)
            answer[i] = tmp[i].intValue();
        return answer;
    }

    public static int[] intersection2(int[] nums1, int[] nums2){
        HashSet<Integer> hash = new HashSet<Integer>();
        HashSet<Integer> intersect = new HashSet<Integer>();

        for (int i = 0; i < nums2.length; i++) {
            hash.add(nums2[i]);
        }

        for(int i = 0;i < nums1.length; i++){
            if(hash.contains(nums1[i]))
                intersect.add(nums1[i]);
        }

        int[] answer = new int[intersect.size()];
        int index = 0;
        for(Integer num : intersect)
            answer[index++] = num;
        return answer;
    }

    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    public int[] intersection4(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (Arrays.binarySearch(nums2, num) >= 0) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
}
