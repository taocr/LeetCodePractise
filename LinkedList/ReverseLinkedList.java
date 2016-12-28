package LinkedList;

import java.util.List;

/**
 * 问题：
 * Reverse a singly linked list.
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * 翻转一个单链表
 * 一个列表可以用迭代和递归来实现，能够能实现它们？（迭代即在函数中实现循环，而递归表示不断调用自身达到循环的目的）
 *
 * 思路：
 * 1、在头结点前再加一个节点，从而使循环更为通用，pPrev指向的一直是一开始的头那个节点，而pCur就是当前要被放到头部的那个
 * 节点，pNext用来保留当前节点的下一个节点从而防止链表断裂。每次将pCur放到newHead的下一个节点即成为新的头节点即可
 *
 * Created by Taocr on 2016/12/22.
 */
public  class ReverseLinkedList {
     public static ListNode reverseList(ListNode head) {//迭代
         ListNode pCur = null;
         ListNode pNext = null;
         ListNode pPrev = head;
         ListNode newHead = new ListNode(0);

         newHead.next = head;
         if (head != null)
             pCur = head.next;

         while (pCur != null) {
             pNext = pCur.next;
             pCur.next = newHead.next;
             newHead.next = pCur;
             pPrev.next = pNext;
             pCur = pNext;
         }
         return newHead.next;
     }

     public static ListNode reverseList2(ListNode head) {//递归
         if (head == null || head.next == null)
             return head;
         ListNode newHead = new ListNode(0);
         newHead.next = head;
         return reverseListInt(newHead, head, head.next);
     }

     public static ListNode reverseListInt(ListNode newHead, ListNode pPrev, ListNode pCur) {//递归
         if (pCur == null)
             return newHead.next;
         ListNode pNext = pCur.next;
         pCur.next = newHead.next;
         newHead.next = pCur;
         pPrev.next = pNext;
         pCur = pNext;
         return reverseListInt(newHead, pPrev, pCur);
     }
}
