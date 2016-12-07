package Array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 问题：
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 *
 * 给定一个整数数组，返回两个数的角标使得两者之和能够得到一个指定的数，你可以假设每个输入的值只有一个唯一的解
 *
 * 思路：
 * 1、动态规划，不过跟暴力破解的方法没什么区别，与其向nums[i]+nums[j]=target，不如反过来想即nums[j] = target-nums[i]，对于target知道了，遍历数组可以获得每个元素的数，
 * 于是查找是否有剩下的那个数就可以了
 * 这样操作的效率很低，时间复杂度达不到O(n)；
 *
 * 2、一样的想法，不过用hashTable进行操作试试看,有个小问题，里面的元素是否会重复，如果会重复那么HashTable不能用的，因为相同的键会只留存一个
 * 提交发现里面的元素存在重复的情况，因此这种方法不适合
 *
 * 3、先排序，之后用指针进行夹逼，对于首尾两个指针指向的元素的和小于target的情况，左侧的指针自增1，对于大于target的情况，右侧的指针自减1
 * 有一个问题，需要保持每个元素的位置，这个位置会在排序时被打破，需要解决，解决起来很麻烦
 *
 * 更好的方法：
 * 1、O(n)的方法，跟我第2个想法一样，不过解决方法不同，我有点傻，可以从数组的头开始遍历，一开始map是空的，每次判断map是否包含target-nums[1]即可，不包含就将目前这个元素添加，包含了就直接可以输出了，不需要先将所有的元素都放入
 *
 * Created by Taocr on 2016/11/9.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target){
        int[] answer = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int secondElement = target - nums[i];
            for(int j = i+1; j < nums.length; j++){
                if(secondElement == nums[j]) {
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        return answer;
    }

    public static int[] twoSum2(int[] nums, int target){//只能用于数组中元素不重复的情况，应该也可以经过修改实现，但是太麻烦，不如第三种
        int[] answer = new int[2];
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            hashmap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int secondElement = target - nums[i];
            if(nums[i] != secondElement && hashmap.containsKey(secondElement)) {
                answer[0] = i;
                answer[1] = hashmap.get(target - nums[i]);
                return answer;
            }
        }
        return answer;
    }

    public static int[] twoSum2EX(int[] nums, int target) {
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (hashmap.containsKey(target - nums[i]))
                return new int[]{hashmap.get(target - nums[i]), i};//到要输出了才分配空间，以免浪费空间与时间
            hashmap.put(nums[i], i);
        }
        return null;
    }

    public static int[] twoSum3(int[] nums, int target){
        int[] answer = new int[2];

        Arrays.sort(nums);

        int low = 0;
        int high = nums.length - 1;
        int sum = 0;
        while (low < high){
            sum = nums[low] + nums[high];
            if (sum == target){
                answer[0] = low;
                answer[1] = high;
                return answer;
            } else if (sum < target){
                low++;
            } else if (sum > target){
                high--;
            }

        }
        return answer;
    }
}
