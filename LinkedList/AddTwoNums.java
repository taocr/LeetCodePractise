package LinkedList;

/**
 * 问题：
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and
 * each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 给定两个链表，表示两个非负数，两个数的顺序为倒序，将两个数进行相加并返回用链表表示的结果
 *
 * 思路：
 * 从头开始遍历，需要一个变量记录进位值，重新建立一个链表用于存储相加后的值，最后循环完成后需要判断进位值，如有进位值则
 * 还需要将最高位提高一位
 *
 * 更好的思路：
 * 思路一样，代码可以更优化
 * Created by Taocr on 2016/12/28.
 */
public class AddTwoNums {
    public  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
}
