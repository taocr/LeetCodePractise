package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题：
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value
 * appears at least twice in the array, and it should return false if every element is distinct.
 *
 * 给出一个整型数组，找寻数组中包含的任何重复的元素。你的功能应该在存在任何元素的值出现两次的情况下返回true，而在任何元素的值都是唯一的情况
 * 下返回false
 *
 * 思路：
 * 1、建立一个HashSet，将原数组中的元素一个个填入，当填入时遇到填入的位置有元素就表示有重复元素，于是返回true
 *
 * 2、将数组中的数据进行排序，于是两个相等的元素就会靠在一起，之后从使用两个指针开始判断两个指针指向的元素时候相等，
 * 奇怪？为什么第2个方法的效率要远高于第1个？想不通
 *
 * 3、最差方案，强行破解，定义两个循环，外循环确定一个元素，内循环找寻后面的元素中是否有与其相同的元素，有就返回true，到最后都没有就放回false
 *
 * 更好的方法：
 * 查看结果中较好的方法基本与第一个思路相同，使用哈希来做，因此对于效率我产生了疑问，是否真的就比第2个方法强，我觉得数据量如果很大应该是成立的
 *
 * 1、其中一个自己实现哈希的方法比较有启发意义，但是其定义了一个范围，方法记录在containsDuplicate3中，此方法运用了与或的逻辑操作，效率比起算术要好，
 * 不过需要提前知道元素范围，因此可能对于某些情况不实用
 * Created by Taocr on 2016/11/4.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if(!hs.contains(nums[i]))
                hs.add(nums[i]);
            else
                return true;
        }
        return false;
    }



    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {//从第2个元素开始判断，如果与前一个元素相等就表示有重复元素
            if(nums[i] == nums[i-1])
                return true;
        }
        return false;
    }

    public boolean containDuplicate3(int[] nums) {
        byte[] mark = new byte[1000];

        for(int num : nums){//简单来说，建立了一个1000个byte大小，相当于一个哈希表，其中每个桶都是1个字节即8位，于是能表示8个数，即总计8000个数可以标记存储
            int i = num/8;//查找对应的桶
            int j = num%8;//具体检测当前这个数在对应的桶中的具体对应位
            int check = 1 << j;//用于检测的位
            if((mark[i] & check) != 0)
                return true;
            else//对于第一次标记，需要或上从而标记
                mark[i] |= check;
        }
        return false;
    }
}
