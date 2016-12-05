package Array;

import java.util.*;

/**
 * 题目：Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k
 *
 * 给出一个整型数组和一个整型数k，找出时候存在两个不同的索引i、j，使得nums[i] = nums[j]，并且i与j相差最多为k
 *
 * 思路：
 * 1、蛮力破解，从第1个元素开始，范围定为k，即在这个范围中找是否有与此元素相等的元素存在，有的话就返回true，如果全部遍历完成后都没有就返回false
 * 太麻烦，不写了
 *
 * 2、用哈希来做，都是要找相等的数，那么就从第1个元素开始存入哈希表，每存入一个都去查一下哈希表中是否有此元素，有的话再对角标进行比较，
 * 不过存入的哈希桶需要记录下存入的这个元素的角标，方便进行比较
 *
 * 行不通，不能单纯用HashMap，对于数组中有多个元素相同的情况下HashMap只能记录下一个的角标，且后面就没办法添加了，改为了一个比较复杂的方法
 * 还是用HashMap，定义存入的键为具体数组中的数值，而值定位一个存整型的ArrayList，于是将值相等的多个元素的角标存起来，进行对比，当HashMap
 * 中没有这个元素对应的键的时候，就新建这个ArrayList并存入，而对于有的情况就不用新建了，取出这个对应的ArrayList进行判断，或返回或添加角标就行了
 *
 *更好的方法：
 * 1、第一个方法没看懂，貌似用了滑动窗口的方法，但是不明白到底是怎么个想法，效率为88.95%，效率最高，不过不懂为什么
 *
 * 2、其实前面想的复杂了，不需要将所有的角标都存储下来，想一想，在数组中遍历，如果说碰到了相同的元素的键时，其实只要将上一个这个值的角标与
 * 当前这个值的角标进行相减比较k就行了，如果 <=的话就直接返回true，如果>的话那么其他更远的元素的角标肯定也更加大，因此不需要再进行运算，
 * 即Map中的键的值只需要存储当前这个元素的角标即可，前面的都可以抛弃
 * 这个方法效率为75%左右
 * Created by Taocr on 2016/11/5.
 */
public class ContainsDuplicate2 {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                ArrayList<Integer> indexList = map.get(nums[i]);
                for (Integer num : indexList){
                    int difference = i - num.intValue();
                    if(difference > 0 && difference <= k)
                        return true;
                }
                indexList.add(i);
            } else {
                ArrayList<Integer> indexList = new ArrayList<Integer>();
                indexList.add(i);
                map.put(nums[i], indexList);
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);//这两句的判断与执行语句到底是为什么？
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            Integer last = map.put(nums[i], i);//不进行判断直接存入，如果原来这个键有对应值那么就会将原来的值返回
            if(last != null && Integer.valueOf(last) >= i-k)//对返回的值进行判断，如果不为空且当前元素的角标-这个值的角标<=k就输出true
                return true;
        }
        return false;
    }
}
