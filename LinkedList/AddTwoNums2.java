package LinkedList;

import java.util.Stack;

/**
 * Created by Taocr on 2016/12/28.
 */
public class AddTwoNums2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        Stack<Integer> resultStack = new Stack();
        ListNode result = null;
        int sum = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
        }

        while (true) {
            if (!stack1.empty()){
                sum += stack1.pop();
            }
            if (!stack2.empty()){
                sum += stack2.pop();
            }
            if (stack1.empty() && stack2.empty()) {
                break;
            }
            resultStack.push(sum % 10);
            sum /= 10;
        }

        while (!resultStack.empty()) {
            result = new ListNode(resultStack.pop());
            result = result.next;
        }

        return result;
    }
}
