package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 给定一个已排序数组，并且数组中没有重复元素，返回简要的范围
 *
 * 思路：
 * 1、感觉很简单啊，O(n)的时间复杂度都很容易实现啊.
 * 首先声明一个变量用来放范围开始的数，之后判断一下数组为空的特殊情况，正常情况下进入数据进行遍历，每次遍历到一个数就将
 * 其记录到start中，之后进入内层的while循环，用来判断当前遍历到的数的下一个数是否是在范围内，是的话就将i+1，判断当前遍历
 * 到的数是否和start记录下的数一样，一样就添加单个数的范围，不一样添加多个数的范围
 * 由于for循环内部有while循环来跳过所有除范围头个数和尾部数以外的所有书，所以每次for循环内部必定会添加一个list中的参数，
 * 且连续的两次for循环,i的值由于while循环内部i++的存在也不一定是相差1的关系
 *
 * 更好的思路：
 * 1、大部分的思路都差不多
 * Created by Taocr on 2016/12/8.
 */
public class SummaryRanges {
    public static List<String> summaryRanges (int[] nums) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        if (nums.length == 0)
            return list;
        for (int i = 0; i < nums.length; i++) {
            start = nums[i];
            while (i < nums.length - 1 && nums[i+1] == nums[i] + 1)
                i++;
            if (nums[i] != start)
                list.add(new String(start+"->"+nums[i]));
            else
                list.add(new String(start+""));
        }
        return list;
    }
}
