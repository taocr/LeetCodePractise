package Array;

/**
 * 问题：
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1]
 * 给定一个已经排好序的整型数组，找到给定的目标值的开始和结束的位置，你的算法的时间复杂度应该在O(log n),如果目标数在数组中不存在，返回[-1, -1]
 * 举例：给定[5,7,7,8,8,10] 目标数为8，返回[3,4]
 *
 * 思路：
 * 1、很简单，从头开始遍历，设定一开始的返回数组为[-1, -1]，遍历中第一次找到这个数了就放到数组中（两个元素都放），后面找到的就放在后面就行了
 * 不过满足不了时间复杂度，O(log n)是本题的难点
 *
 * 2、两个指针,一个从头往后，一个从后往前，需要有两个标记记录当前元素是否是i遇到的第一个元素，以及是否是j遇到的第一个元素，另外要记录下i和j最后遇到的符合要求的元素个数
 * 最后要判断一下各种情况，比如有可能i遍历了所有符合要求的数，那么startAlready已经标记，但是endAlready却没有，这时就代表了array的第二个元素没有正确设置，那么将array[1]=iPosition即可
 * 另外如果endAlready已经标记，但是startAlready没有标记，那么将array[0]=jPosition即可。
 * 除此以外的任何情况都直接返回array即可，因为都已经在循环中正确设置了两个元素。
 * 时间复杂度还是达不到要求
 *
 * 更好的思路：
 * 1、二分查找，https://discuss.leetcode.com/topic/5891/clean-iterative-solution-with-two-binary-searches-with-explanation
 * 时间复杂度为O（log n）
 * Created by Taocr on 2016/12/5.
 */
public class SearchForARange {
    public static int[] searchRange(int[] nums, int target) {
        int[] array = {-1, -1};
        boolean isFirstElement = true;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                if (isFirstElement == true) {
                    array[0] = i;
                    array[1] = i;
                    isFirstElement = false;
                } else {
                    array[1] = i;
                }
            }
        }
        return array;
    }

    public static int[] searchRange2(int[] nums, int target) {
        int[] array = {-1, -1};
        boolean startAlready = false;
        boolean endAlready = false;
        int iPosition = -1;
        int jPosition = -1;

        for(int i = 0, j = nums.length - 1; i <= j; i++ ,j--) {
            if (nums[i] == target) {
                if (startAlready == false) {
                    array[0] = i;
                    startAlready = true;
                }
                iPosition = i;
            }
            if (nums[j] == target){
                if (endAlready == false) {
                    array[1] = j;
                    endAlready = true;
                }
                    jPosition = j;
            }
        }
        if (startAlready == true && endAlready == false) {
            array[1] = iPosition;
        } else if (startAlready == false && endAlready == true) {
            array[0] = jPosition;
        }
        return array;
    }

    public static int[] searchRange3(int[] nums, int target) {
        int i = 0, j = nums.length -1;
        int[] array = {-1, -1};

        while (i < j)
        {
            int mid = (i + j) / 2;//取中间的数
            if (nums[mid] < target) //对于中间的数小于目标数的情况，将起点定位mid+1
                i = mid + 1;
            else//对于中间的数大于等于目标数的情况，将终点定为mid
                j = mid;
        }
        if (nums[i] != target)//对于一直约束到哦最后i=j的时候，约束下来的这个数不为目标数，那么证明数组中没有这个数
            return array;
        else//约束到最后如果为目标数，那么这是一个起点数
            array[0] = i;

        j = nums.length - 1;//对于起点的i来说已经确定了，且不需要再置为0了
        while (i < j)
        {
            int mid = (i + j)/2 + 1;//使得中间数偏向右侧
            if (nums[mid] > target)
                j = mid - i;
            else
                i = mid;
        }
        nums[1] = j;
        return nums;
    }
}
