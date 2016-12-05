package Array;

import java.util.*;

/**
 * 题目：
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 *
 * 给定一个整型数组，其中的元素假定应该为1~n(数组大小)，一些元素出现了两次而有些元素只出现一次，找出其中1~n中出现两次的数组，不用额外空间
 * 并且时间复杂度为O(n)，返回的是一个List不计算额外的空间
 *
 * 思路：
 * 1、因为数组中的元素为1~n，正好等于数组的大小，因此可以用其来标识数组的角标，遍历一次，每次遍历到的元素我们都将此为位上的数置为负数，
 * 遍历过程中，对于当前遍历到的这个数为负的情况我们不该变它的值，继续遍历下去，之后在通过i遍历到的值为nums[i]，然后nums[nums[i]]为负数的
 * 时候我们才肯定这个数已经被标记过了，即不能根据i对应的当前这个元素是否为正负来判断，而要根据nums[nums[i]]是否为正负进行判断
 *
 * 重点在于当i遍历到的元素为负时不代表任何意思，只是可能前面有的元素标记了这一个角标而已，所以要根据nums[nums[i]]进行判断，即根据具体元
 * 素的索引来判断此索引指向的角标上的元素是否为负，是负的话代表了这是第二次索引到这个角标了，记录下来即可
 *
 * 更好的方法：
 * 1、第一个找到的跟我的想法一样 =。=！
 * 2、
 *
 *
 * Created by Taocr on 2016/11/9.
 */
public class FindDuplicates {
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int mark = Math.abs(nums[i]) - 1;
            if (nums[mark] > 0)
                nums[mark] = -nums[mark];
            else if (nums[mark] < 0 && !list.contains(mark+1))//判断数组元素的索引的位置上的数为负数，且列表中不包含这个数
                list.add(mark + 1);//在列表中添加这个数
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,2,2,2,2,3,1};
        List<Integer> list = findDuplicates(nums);
        System.out.println(list.toString());
    }
}
