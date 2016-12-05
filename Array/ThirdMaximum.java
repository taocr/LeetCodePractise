package Array;

/**
 * 问题：
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
 * return the maximum number. The time complexity must be in O(n).
 *
 * 在非空数组中找出第三大的数，如果不存在就返回最大的数，要求时间复杂度为O(n)
 *
 * 思路：
 * 1、时间复杂度为O(n)，那么必然意味着只能遍历一次数组，需要三个变量firstMax、secondMax、thirdMax来根据每个数的具体情况进行判断
 * 注：一开始使用整型的最小数来进行三个变量的初始化，使得出现任何数都能够大于它们,但是有个问题，数组中也是可以出现这个数的，如果出现了就会
 * 出现异常的情况，并且需要很多的判断才能输出数据，感觉很麻烦
 *
 * 更好的方法：
 * 对于int型来说，不一定非要用最小的数来进行初始化，可以用Integer特有的null引用来操作，初始化三个Integer类型的引用为null，判断的语句还是
 * 和我的做法一样，最后只需要判断一下thirdMax是否为null，为null输出firstMax，不为null输出thirdMax即可
 *
 * Created by Taocr on 2016/11/3.
 */
public class ThirdMaximum {
    public static int ThirdMaximum(int[] nums) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        boolean hasMINVALUE = false;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE)
                hasMINVALUE = true;
            if (nums[i] == firstMax || nums[i] == secondMax || nums[i] == thirdMax)//对于当前数等于三个最大数的情况，跳过
                continue;
            if (nums[i] > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            } else if (nums[i] < firstMax && nums[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] < secondMax && nums[i] > thirdMax) {
                thirdMax = nums[i];
            }
        }

        if (firstMax > secondMax && secondMax > thirdMax) {
            if (!hasMINVALUE && thirdMax == Integer.MIN_VALUE)
                return firstMax;
            return thirdMax;
        } else
            return firstMax;
    }

    public static int ThirdMaximum2(int[] nums) {
        Integer firMax = null;
        Integer secMax = null;
        Integer thrMax = null;

        for (Integer num : nums) {
            if (num.equals(firMax) || num.equals(secMax) || num.equals(thrMax)) continue;
            if (firMax == null || num > firMax) {
                thrMax = secMax;
                secMax = firMax;
                firMax = num;
            } else if (secMax == null || num > secMax) {
                thrMax = secMax;
                secMax = num;
            } else if (thrMax == null || num > thrMax) {
                thrMax = num;
            }
        }

        return thrMax == null ? firMax : thrMax;
    }
}
