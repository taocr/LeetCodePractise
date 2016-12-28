package LinkedList;

/**
 * 问题：
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 *
 * 给定一个链表，确定其中是否存在一个环
 * 能够不用额外空间进行解决
 *
 * 思路：
 * 1、用额外的空间，每次将引用记录在hash表中，遍历的过程中,next指向的引用在hash表中包含了，就代表有环
 *
 * 2、运用算法，从头开始，用两个指针，一个每次走一步，另一个每次走两步，如果存在环，那么最后必然会在某一个点重合。
 * 如果有一个点的往下一步是null，那么就代表没有环。
 * Created by Taocr on 2016/12/26.
 */
public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {//2，两个指针的方法
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (walker.next != null && runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }
}
