package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear
 * once。 Find all the elements of [1, n] inclusive that do not appear in this array.Could you do it without extra
 * space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * 给定一个整型数组，其中的元素假定应该为1~n(数组大小)，一些元素出现了两次而有些元素只出现一次，找出其中1~n中没有出现的元素，不用额外空间
 * 并且时间复杂度为O(n)，返回的是一个List不计算额外的空间
 *
 * 思路：
 * 1、定义一个足够大的数作为哈希表用，当遍历到一个元素就将数的对应的位置上1，最后对整个数的每个位遍历一次就知道哪些数据没有出现，问题在于应该不满足要求
 * 使用了额外的空间，时间复杂度为O(n)
 *
 * 更好的方法
 * 1、由于数组内的所有的元素是1~n，因此我们可以用这些元素来假定为数组中的元素的位置，即循环遍历一次，每次遇到一个数就将这个数所对应的下标上的数置为负数，
 * 因此遍历完成后，数组中所有已经出现过的数所代表的下标上的那些数都是负的，那么不是负的数所对应的位置的序号就是没有出现的数了，比较巧妙
 *
 * 2、此方法与第1个方法的想法相同，只不过使用的标记方法不一样，其用了+n的方法，另外为了以防一个数出现了两次，所以使用%进行取余，第二次会将数组首先还原再进行+n,最后判断<=n的数就是没有出现的数
 * Created by Taocr on 2016/11/9.
 */
public class FindDisappearedNumbers {
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i ++)
            nums[(nums[i]-1) % n] += n;
        for (int i = 0; i < nums.length; i ++)
            if (nums[i] <= n)
                res.add(i+1);
        return res;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int mark = Math.abs(nums[i])-1;//因为一个元素可能出现多次，所以每次去当前元素具体代表的数时都用abs进行去绝对值，保证为正的，-1是因为数组的下标从0开始,另外题目中的描述可以看出数组中没0，
            if (nums[mark] > 0)//对于下标上的数>0的情况就将其置为负数，而对于负数则不变
                nums[mark] = -nums[mark];
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0)
                list.add(i+1);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list;
        list = findDisappearedNumbers(nums);
        System.out.println(list.toString());
    }
}
