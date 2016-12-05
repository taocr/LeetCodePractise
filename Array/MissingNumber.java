package Array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 问题：
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * 给定一个数组包含了n个不同的数（从0~n），找到丢失的那个
 * 你的算法应该保持线性的时间复杂度。你能够用不变的额外空间复杂度来实现它吗？
 *
 * 思路：
 * 1、从头开始往后遍历用一个变量来存储下一个位置应该存的数，如果不同那么就代表丢失了(存在问题，数组中的元素不是按顺序排列的，因此这个方法行不通)
 *
 * 2、定义一个哈希表，将数组中所有的元素都存入进去，之后再开始从0到n在哈希表中查找每一个数，如果没有找到那就缺少了这个
 *
 * 3、利用位运算，数组中的数比起正常的n个自然数排序来说肯定少一个，用a^b^b=a原理，使得一个数能够记录下不同的那个数，比如[0,1,3]，这样的数组，我们用一个 为0的变量 ^ 数组中的一个元素 ^ 角标 最后再^一次角标+1的数
 * 即 0^0^0=0  0^1^1=0 0^3^2到这里的时候数组已经遍历完成，不过为了得到正确的结果，我们应该再异或一次角标+1（即数组中最大的那个数），即为0^3^2 ^3 = 2，这样的结果就是缺少的那个数了，记住位的逻辑运算是最快的
 *
 * 4、利用等差数列求和的公式，得到n个数的和，再依次去减去数组中的所有元素，最后剩下的那个数就是缺少的数了，马蛋，这个我都没想出来，坑了
 *
 * 5、首先对数列进行排序，然后用二分查找的办法，这个方法的效率应该不高
 *
 * Created by Taocr on 2016/12/3.
 */
public class MissingNumber {
    public static int missingNumber(int[] nums){
        HashSet<Integer> hs = new HashSet<Integer>();
        for(Integer currentNum : nums){
            hs.add(currentNum);
        }

        for (int i = 0; i < nums.length; i++) {
            if(!hs.contains(i))
                return i;
        }
        return nums.length;
    }

    public static int missingNumber2(int[] nums){ //xor
        int xor = 0;
        int i = 0;
        for(i = 0; i < nums.length; i++){
            xor = xor ^ i ^ nums[i];
        }
        return xor ^ i;
    }

    public int missingNumber3(int[] nums) { //sum
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }

    public int missingNumber4(int[] nums) { //binary search
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid= (left + right)/2;
        while(left<right){
            mid = (left + right)/2;
            if(nums[mid]>mid) right = mid;
            else left = mid+1;
        }
        return left;
    }
}
