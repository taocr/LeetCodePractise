package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * 给定一个任何元素都唯一的整型数组，返回所有可能的集合，
 * 注意：答案的总集合中不能包含任何重复的集合
 * 例：[1,2,3] ，答案为[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 思路：
 * 1、很明显的回溯法，整个的结构可以构成一颗树，每次到了节点就要退回上一节点，用模板来做，首先创建需要返回的结构，之后对数组进行排序，然后进入回溯
 * 每次回溯调用，首先加入当前的tmpList,然后，根据当前遍历到的节点来判定需不需要继续向下调用，回溯中的剪枝是在数组遍历到最后的元素时结束的，
 * 此时剪枝，即直接返回，不进行任何操作
 * 典型的回溯模板，效率很高
 * Created by Taocr on 2016/12/5.
 */
public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);

        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums, int start){
        list.add(new ArrayList<Integer>(tmpList));
        for (int i = start; i < nums.length; i++) {//回溯的核心部分代码
            tmpList.add(nums[i]);
            backtrack(list, tmpList, nums, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }
}
