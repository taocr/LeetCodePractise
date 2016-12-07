package Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 给定一个整型数组，选取数组中的三个元素，使它们和为0,找到所有不重复的组合
 * 例：[-1, 0, 1, 2, -1, -4]，答案为[[-1, 0 ,1],[-1, -1, 2]]
 *
 * 思路：
 * 1、将3Sum转为2Sum来做，即每次选取一个数，之后在剩下的元素形成的数组中用两个指针进行夹逼，问题在于最后的答案可能会重复，因此需要去重复
 * 首先需要排序，因为用指针夹逼的手段必须要实现排序
 * 具体来说，外层循环用来取将3Sum变为2Sum的那个数，这个数需要有个变量记录它之前的值，因为对于两个相同的值来说，是没有必要再计算的，直接跳过即可。
 * 之后内层循环需要记录变为2Sum后的target，两个指针的和的sum值，此外还需要记录每次满足了target==sum后所得到的nums[i]值，这是因为内层的循环中也可能出现重复的情况
 * 例如[-2,0,0,2,2]，对于外层循环取-2的情况，内层会出现两次的[0,2]，多用一个变量来记录就能使得出现这种情况的时候能够不将其添加到最后的答案中，即去重的主要实现
 * 两个指针进行夹逼也要注意，当i或j有一个指向了使3Sum变为2Sum的那个数时需要跳过，因为每个数只能用一次，两个指针的夹逼过程在2Sum中有写，不具体解释了，有一个坑要注意
 * 当出现之前说的重复的情况时，其也有这具体的后续工作即i++、j--，否则会陷入无限循环的bug，唉。。踩过的坑
 *
 * 更好的思路：
 * 1、用的方法是一样的，但是更简洁，同时也有一个小窍门，即外层的循环，其实到nums.length-2即可，不用到最后一个，因为当外层的循环到nums.length-2的时候
 * 内层的循环的i与j就是相等的了，可以省掉这一步。另外用
 * while (lo < hi && num[lo] == num[lo+1]) lo++;
 * while (lo < hi && num[hi] == num[hi-1]) hi--;
 * lo++; hi--;
 * 这三步，当满足了target==sum的情况下，可以直接地将lo跳过所有相等的数，语句更简洁，且效率会更高
 * Created by Taocr on 2016/12/6.
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum (int[] nums) {//自己的方法1
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Integer foreNum = null;

        for(int index = 0; index < nums.length; index++){
            if (foreNum != null && nums[index] == foreNum)
                continue;
            else {
                foreNum = nums[index];
                int target = 0 - nums[index];
                int i = index + 1;//fore pointer
                int j = nums.length - 1;//last pointer
                Integer foreIValue = null;
                while (i < j) {
                    int sum = nums[i] + nums[j];
                    if(i == index || sum < target)
                        i++;
                    else if(j == index || sum > target)
                        j--;
                    else if(sum == target && (foreIValue == null || foreIValue != nums[i])) {
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(nums[index]);
                        al.add(nums[i]);
                        al.add(nums[j]);
                        list.add(al);
                        foreIValue = nums[i];
                        i++;
                        j--;
                    } else{
                        i++;
                        j--;
                    }
                }
            }
        }
        return list;
    }

    public static List<List<Integer>> threeSum2 (int[] nums) {//better thought 1
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        for(int index = 0; index < nums.length-2; index++) {
//            if(nums[index] != nums[index+1]) {//这样不行，因为如果后面的数跟前面的数一样，那么最后的答案中可能其会出现两次，
//                                                因为在数组中它重复了，这是允许的，但是这样的判断会使得不可能出现重复的元素
            if (index == 0 || (index > 0 && nums[index] != nums[index - 1])){
                int i = index + 1;
                int j = nums.length - 1;
                int target = 0 - nums[index];
                while (i < j) {
                    int sum = nums[i] + nums[j];
                    if (sum == target){
                        list.add(Arrays.asList(nums[index], nums[i], nums[j]));
                        while (i < j && nums[i] == nums[i+1])
                            i++;
                        while (i < j && nums[j] == nums[j-1])
                            j--;
                        i++;
                        j--;
                    } else if (sum < target) {
                        i++;
                    } else
                        j--;
                }
            }
        }
        return list;
    }
}
