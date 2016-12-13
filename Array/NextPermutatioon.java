package Array;

import java.util.Arrays;

/**问题：
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 实现下一个排序，将按字母表顺序生成给定序列的下一个较大的序列，直到整个序列为减序为止
 * 例：1,2,3 → 1,3,2；3,2,1 → 1,2,3；；1,1,5 → 1,5,1
 *
 * 思路：
 * 1、简单的来看，{1, 2, 5, 4, 3}，从右开始，最多的减序序列为{5, 4, 3}，因此下面要将2并入进去进行排列得到下一个字典序排
 * 列，从{5, 4, 3}中找出比2大的最小的数，将其变为第一个数，然后将剩下的数按字典序从小到大进行排列即可。
 * 需要判断一些特殊情况，比如整个序列都是减序,直接将整个序列进行正常的排序即可
 *
 * 更好的思路
 * 上面的方法时间复杂度为O(n)，空间复杂度为O(1)，没有找到更好的方法
 * Created by Taocr on 2016/12/12.
 */
public class NextPermutatioon {
    public static void nextPermutation (int[] nums) {
        if (nums.length <= 1)
            return;
        int i = nums.length - 1;
        for (; i > 0; i--) {    //找到右侧最大的减序序列，i就是减序序列的头个元素
            if (isDecreaseSequence(i,nums) == false)
                break;
        }
        if (i <= 0) {        //前面的循环结束后，i<=0的话，代表数组遍历完，没有提前break，那么就代表一直是减序，于是只要对数组进行从小到大的从新排序即可
            Arrays.sort(nums);
            return;
        }
        //确定i以后，找到从右侧的减序序列中大于i-1角标的最小的数
        int firstGreaterIndex = findFirstGreaterIndex(i, nums);
        //i-1与firstGreaterIndex上的两个数交换
        swap(i - 1, firstGreaterIndex,nums);
        //将从i开始到最右侧的所有的数进行从小到大的排序即可
        Arrays.sort(nums, i, nums.length);
    }

    private static void swap(int i, int firstGreaterIndex, int[] nums) {
        int buf = nums[i];
        nums[i] = nums[firstGreaterIndex];
        nums[firstGreaterIndex] = buf;
    }

    private static int findFirstGreaterIndex(int i, int[] nums) {
        int num = nums[i - 1];
        int firstGreaterIndex = i;

        for (; i < nums.length; i++) {
            if (nums[i] > num && nums[i] < nums[firstGreaterIndex])
                firstGreaterIndex = i;
        }

        return firstGreaterIndex;
    }

    private static boolean isDecreaseSequence(int i, int[] nums) {
        if (i - 1 >=0 && nums[i - 1] < nums[i])
            return false;
        else
            return true;
    }
}
