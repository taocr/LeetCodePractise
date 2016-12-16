package Array;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 *
 * 找出所有使得k个数的和为n的组合，只有1到9的数组可以使用，另外每个集合应该是唯一的
 *
 * 思路：
 * 1、回溯法:由于没有数组需要排序，直接创建返回的结构体；回溯内层首先为判断条件，主要为判断整数以及数的个数问题，之后根据
 * 具体情况判定是否还需要继续回溯
 * 跟
 *
 * Created by Taocr on 2016/11/9.
 */
public class CombinationSum3 {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        backtrace(k, n, list, new ArrayList<Integer>(), 0);

        return list;
    }

    private static void backtrace(int limit, int target, List<List<Integer>> list, ArrayList<Integer> tmpList, int start) {
        if (limit == 0 && target == 0)
            list.add(new ArrayList<Integer>(tmpList));
        else if (limit > 0 && target > 0) {
            for (start = start + 1; start <= 9; start++) {
                tmpList.add(start);
                backtrace(limit - 1, target - start, list, tmpList, start);
                tmpList.remove(tmpList.size() - 1);
            }
        }
        else if (limit < 0 || target < 0)
            return;
    }
}
