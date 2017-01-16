/**
 *问题：Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 返回第一次出现的字符串的索引
 *
 * 思路：
 * 1、暴力破解，对haystack进行遍历，每遍历到一个字符就跟needle进行比较。
 * 2、KMP，比起暴力破解，不同之处在于每次比较不同的时候，会将当外循环的索引跳转 >=1 的距离，是通过前缀后缀的部分匹配值得
 * 到的，因此每次出现两者不同的时候需要对索引进行计算部分匹配值，从而算出下次应该跳转多少
 * Created by Taocr on 2017/1/12.
 */
public class Implement_strStr {
    public static int strStr(String haystack, String needle) {
        for (int i = 0;; i++) { //每次循环使得被比较字符串索引加1，这个i值一直不变
            for (int j = 0;; j++) {//对于i索引指定的角标，用j来指向needle字符串的具体字符
                if (j == needle.length())   //每次循环中如果j已经为needle,length，那么已经比较完了，且两个字符相等
                    return i;//返回i的索引，指向了需要的字符串开始的位置
                if (i + j >= haystack.length()) //每一次j++，因此i+j实际每次只是多1，必然会出现i+j==haystack的情况，此时实际上意味着i开始的字符串中剩下的部分已经没有足够的字符来组成needle的字符串了，因此直接退出即可
                    return -1;
                if (haystack.charAt(i+j) != needle.charAt(j)) //i+j指向了haystack中本次比较的具体字符，而needle的j也是
                    break;
            }
        }
    }

    public static int strStr_
}
