package LinkedList;

import com.sun.javafx.iio.ImageLoaderFactory;
import jdk.nashorn.internal.ir.IfNode;

import java.util.Stack;

/**
 * 问题:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 给定一个字符串包含()、[]、{}，判断字符串是否有效
 *
 * 思路：
 * 1、用栈来做，遇到(,[,{,都将其存入到栈中，每次判断到有右括号的时候就进行将栈顶出栈操作，如果出栈的括号不是与右括号相
 * 对应的做括号，就表示不对
 *
 * 更好的思路：
 * 没有更好的方法，但是可以优化，比如不将字符串化为字符数组，通过CharAt来获取每个字符，另外每次获取新的右括号字符的时候，
 * 可以判断一下堆栈是否为空的操作，增加效率
 * Created by Taocr on 2016/12/27.
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == '(' || symbol == '[' || symbol == '{')
                stack.push(symbol);
            else if (symbol == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if (symbol == ']' && !stack.empty() && stack.peek() == ']')
                stack.pop();
            else if (symbol == '}' && !stack.empty() && stack.peek() == '}')
                stack.pop();
            else return false;
        }
        return stack.empty();
    }
}
