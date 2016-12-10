package Array;

/**
 * 问题：
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * 给定一个包含n+1个数的整形个数组，每个元素都在 1 至 n 之间，至少存在一个对重复的数。假定只有一对重复的数，找到重复的那个数
 * 不可以改变数组（即数组是只读的），只能使用恒定的O(1)复杂度的额外空间，你的时间复杂度应该在O(n*n)以内，只有一个重复的数，但是可以重复多次
 *
 * 思路：
 * 1、穷举，嵌套两层循环，fori{for(j=i+1)}，当nums[i] == nums[j]，返回nums[i]，时间复杂度是小于O(n^2)的，但是感觉太简单了，这道题的难度是hard啊
 * 效率异常低。。
 *
 * 2、位图法，建立整数表示极限大小的位图，每找到一个数在相应的位置上标记，若为1则表示已经找到过这个数随即返回当前遍历到的这个数值大小，因为建立的位图大小规定，所以应该不算空间复杂度为O(n)
 * 感觉不太靠谱，整数的范围最大为2^31-1，太大了吧
 *
 * 3、异或的方法，问题在于要保存之前异或的结果，这样对于空间复杂度满足不了
 *
 * 更好的思路：
 * 1、环路法，不好理解，即所有的元素会构成一个环，而入口就是重复的那个元素
 * http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
 * * Created by Taocr on 2016/12/9.
 */
public class FindTheDuplicateNumber {
    public static int findDuplicate (int[] nums) {//穷举
        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i+1; j < nums.length;j++) {
                if (nums[i] == nums[j])
                    return nums[i];
            }
        }
        return (Integer)null;
    }

    public static int findDuplicate2 (int[] nums) {//环路法
        if (nums.length > 1) {
            int slow = nums[0];//第一个元素必定不在环内，因为数组内的元素为1至n，因此没有任何一个元素被访问时可以让下一个被访问的元素为角标为0的元素
            int fast = nums[nums[0]];
            while ( slow != fast) { //退出循环时，满足slow=x_j , fast=x_{2j}，共有三个特性：1、此时j的长度一定大于"p"的进入环的链的长度c，因为此时已经在环中，否则不会使得slow和fast相等；
                slow = nums[slow];//2、j一定是"p"中环的长度l的倍数，因为走了j步回到起点，因此j一定为l的倍数；3、j一定是大于c的l的最小倍数，因为如果存在更小的，那么我们一定会在到达j之前到达那里
                fast = nums[fast];//因此j = l`，即大于c的l的倍数的最小值，从而我们找到了l`的值
            }
            //接下来就是如何通知l`来找到环的起点在哪里

            fast = 0;//利用fast来做finder
            while (slow != fast) {//假设环的入口位于x_c,那么对于fast(finder)来说从0开始即走c步到达x_c
                fast = nums[fast];//另一边，之前得到的结论是j=l`,那么slow=x_j就意味着slow=x_l`，由于fast走了c步，那么slow也走c步，就成了slow=x_{l`+c}，也就等价于slow从开始到现在向前走了c步然后绕环循环了l`次回到原位
                slow = nums[slow];//因此就相当于两者相遇的地方即x_c就是环的入口，即slow or fast的值就是环的入口了
            }
            return slow;
        }
        return -1;
    }
}
