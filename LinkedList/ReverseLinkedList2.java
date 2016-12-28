package LinkedList;

import java.util.List;

/**
 * 问题：
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 *
 * 翻转一个链表的从m到n部分，使用内部空间并只遍历一次，给定的m、n符合1 ≤ m ≤ n ≤ length of list的条件
 *
 * 思路：
 * 跟翻转单链表方法一样，不同的是这里需要先遍历到m的位置然后限定号n的位置，即需要加上一些判断条件
 *
 * Created by Taocr on 2016/12/22.
 */
public class ReverseLinkedList2 {
    public static ListNode reverseLinkedList(ListNode head, int m, int n) {
        if (m == n)
            return head;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode pCur = newHead;
        int i = 0;
        while(i < m - 1) {
            pCur = pCur.next;
            i++;
        }
        head = pCur;
        ListNode pPrev = pCur.next;
        pCur = pPrev.next;
        i = i + 2;

        while (i <= n) {
            ListNode pNext = pCur.next;
            pCur.next = head.next;
            head.next = pCur;
            pPrev.next = pNext;
            pCur = pNext;
            i++;
        }

        return newHead.next;
    }
}
