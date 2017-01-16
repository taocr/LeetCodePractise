package LinkedList;

/**
 * Created by Taocr on 2016/12/22.
 */
public class Test {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(7);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(4);
        AddTwoNums.addTwoNumbers(l1, l2);
    }
}
