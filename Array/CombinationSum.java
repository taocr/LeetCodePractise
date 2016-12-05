package Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题：Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * 给定一个数组和一个目标数，找到所有C中唯一的组合能够使其和为目标数。
 * C中选择的数可以重复任意多次数
 * 例：candidates:[2,3,6,7]  target: 7   answer:[[7], [2, 2, 3]]
 *
 * 思路
 * 1、 减法，递归调用实现，使用的空间比较多效率也一般，要注意一个实现点就是对于一次递归最后一个数减去后刚好等于0的情况，比如例子中7的情况
 * 当计算出7=0的时候，为了实现递归的功能于是将最后一次加入的这个数删去了，但是当位于最顶层的栈中时，删去以后就会使得currentbuf为空，即currentbuf=[7]，target = 0，
 * 于是添加进入buffer，然后currentbuf就为空了，但是回到上一层的时候，已经7已经是数组中的最后一个数了，为了保证正常的逻辑，这里需要再去掉
 * 一个currentbuf元素表示回到整个递归调用形成的树的上一个节点上，于是问题出现了，currentbuf已经为空了，而又要减去一个节点，那么就出错了
 *
 * 更好的思路：
 * 1、用的相同的思想，但是写法比起来更简便。首先将数组排序，然后哦开始进入递归调用，递归的方法跟我的差不多，但是判断语句上不太一样，对于大于0的情况，
 * 是否循环要判断两个条件，第1是当前的这一层栈中的循环遍历有没有结束，另一个条件时当前遍历到的这个数是不是<=target，因为已经排过序，如果大于了那么后面就都不用比了
 * 直接退回上一个节点即可，对于满足条件的则将当前遍历到的这个数添加进入当前的ArrayList中，进入下一层栈（树）的遍历上，当下一层的栈循环遍历完成后就将当前这个数删去（即退回上一层调用的节点处）
 * 对于==0的情况，跟我的处理差不多，但是等于0的情况下直接添加，添加完成后 会退回上一层的调用处然后
 * Created by Taocr on 2016/11/9.
 *
 * 2、回溯法的规范格式：外层顺序为：建立返回的结构，对数组进行排序，回溯开始，返回得到的结构体
 * 回溯核心部分，每次进入回溯的过程中，首先根据条件判定是否要将当前的列表内容加入到总列表中，然后当前的这层树遍历，注意约束条件
 *
 * 总结为回溯模板的小更改
 * 效率居然没有我自己写的方法高？不可能，我开始怀疑LeetCode的测试结果了
 * 想了一下，可能是排序的问题，没有发挥出排序的作用，其实可以不排序
 * 妈蛋，效率还是比我自己写的低，真的怀疑LeetCode的测试结果了
 *
 * 回溯总结：
 * 分析自己的方法以及其他的两种方法，实际上都是回溯的模板变形而来，所以回溯的方法真的很重要
 */
public class CombinationSum {
    //方法1
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> buf = new ArrayList<List<Integer>>();
        ArrayList<Integer> currentbuf = new ArrayList<Integer>();

        partition(candidates, target, 0, buf, currentbuf);

        return buf;
    }

    public static void partition(int[] candidates, int target, int start, ArrayList<List<Integer>> buffer, ArrayList<Integer> currentbuf){
        if (target == 0) {
            buffer.add(new ArrayList(currentbuf));
            currentbuf.remove(currentbuf.lastIndexOf(candidates[start]));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                currentbuf.add(candidates[i]);
                partition(candidates, target - candidates[i], i, buffer, currentbuf);
            }
            if (currentbuf.size() > 0)
                currentbuf.remove(currentbuf.size() - 1);
        } else if (target < 0) {
            currentbuf.remove(currentbuf.size() - 1);
        }
    }

    //方法2
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);

        return result;
    }

    private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
        if(target > 0){
            for(int i = start; i < candidates.length && target >= candidates[i]; i++){
                cur.add(candidates[i]);
                getResult(result, cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);
            }//for
        }//if
        else if(target == 0 ){
            result.add(new ArrayList<Integer>(cur));
        }//else if
    }

    //方法3
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backtrace(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private void backtrace (List<List<Integer>> list, List<Integer> tmpList, int[] candidates, int target, int start) {
        if (target == 0){
            list.add(new ArrayList<Integer>(tmpList));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                tmpList.add(candidates[i]);
                backtrace(list, tmpList, candidates, target - candidates[i], i);
                tmpList.remove(tmpList.size() - 1);
            }
        } else if (target < 0)
            return;
    }

}