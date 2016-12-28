package LinkedList;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

/**
 * 问题：
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null
 * 给定一个链表，返回换开始的点，如果没有环，返回null
 *
 * 思路：
 * 环路法，解法跟Array中的FindTheDuplicateNumber是一样的，首先通过两个指针确认是否有环，再将走的比较快的那个重新置为头
 * 节点，快的变为一步一步走，当其与原来慢的相等时，此节点就是环的开始节点了
 * Created by Taocr on 2016/12/26.
 */
public class LinkedListCycle2 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode walker = head;
        ListNode runner = head;
        while (walker.next != null && runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                runner = head;
                while (walker != runner) {
                    walker = walker.next;
                    runner = runner.next;
                }
                return walker;
            }
        }
        return null;
    }
}
