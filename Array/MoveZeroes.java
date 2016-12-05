package Array;


/**
 *问题：
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * 将数组中所有的“0”都移动到数组的末尾去，并保持非零元素的既定顺序
 *
 * 思路：
 * 1、从头开始遍历，记录下交换元素的位置，当碰到非零元素时将交换位置的元素与当前遍历到的元素进行交换，如遇到0则继续遍历，最后所有的非零元素
 * 交换的操作中为了加快时间，添加一步判断，交换的位置不相同才进行交换
 *
 * 2、第1种思想的优化，同样记录下 交换元素 的位置，同样记下0的个数，从头开始遍历，遇非0元素则将此元素赋值给 交换位置 的元素，交换位置加1
 * 遇0元素则将0的个数的计数count加1,。遍历完成后，将末尾的count个数的元素清0
 *
 * 更好的解决思路：
 * 0ms java 思路：跟第2种有些相似，但是巧妙的运用了swapLoc在将所有非0元素全部排在前面后，swapLoc指向的位置实际上就是0元素开始的位置了，
 * 所以swapLoc可以称为index，连计数都不需要，当用i对数组遍历过一次后，所有的非0元素已经归到了数组的前一部分，而index此时指向的就是数组的
 * 后一部分，只需要从这里开始清零即可
 *
 * Created by Taocr on 2016/11/2.
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums){
        int swapLoc = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, swapLoc);
                swapLoc++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if(i != j) {
            int buf = nums[i];
            nums[i] = nums[j];
            nums[j] = buf;
        }
    }


    public static void moveZeroes2(int[] nums){
        int swapLoc = 0;
        int count = 0;//用于计算0的个数

        for(int i = 0;i < nums.length; i++){
            if(nums[i] == 0)
                count++;
            else{
                nums[swapLoc] = nums[i];
                swapLoc++;
            }
        }

        for (int i = nums.length-count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes3(int[] nums){
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0)
                nums[index++] = nums[i];
        }
        while(index < nums.length)
            nums[index++] = 0;
    }
}
