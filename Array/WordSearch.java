package Array;

/**
 * 问题：
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * 给定一个字符数组，在其中查找是否包含指定的词，这个词可以由平行or垂直的相邻元素组成。一个元素只能够使用一次
 *
 * 例：
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 * 思路：
 * 1、深度优先遍历问题,外层建立对矩阵的遍历，主要的search函数用作递归调用，用一个相同大小的boolean型矩阵来标识当前索引到的元素是否已经访问过了
 * 内部首先判断index是否与字符串长度相等，如果相等即所有的词都已经比对过了，且之前如果出现了不想等的情况就已经返回了false截断，所以用于做
 * 返回true的条件。之后一系列的false情况进行判断例如超出边界、当前索引的字符与对应的字符串的具体字符不相同还有当前索引的字符已经访问过的
 * 情况，当跳过了这些就代表还要继续向下寻找，于是将hasVisited数组的相应位置
 *
 * 2、深度优先遍历也可以用栈来实现，避免使用递归。
 * Created by Taocr on 2016/12/7.
 */
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        boolean[][] hasVisited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, 0, i, j, hasVisited))
                    return true;
            }
        }
        return false;
    }

    private static boolean search(char[][] board, String word, int index, int i, int j, boolean[][] hasVisited) {
        if (index == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || word.charAt(index) != board[i][j] || hasVisited[i][j])
            return false;

        hasVisited[i][j] = true;
        boolean ret = search(board, word, index + 1, i - 1, j, hasVisited)
                || search(board, word, index + 1, i + 1, j, hasVisited)
                || search(board, word, index + 1, i, j - 1, hasVisited)
                || search(board, word, index + 1, i, j + 1, hasVisited);
        hasVisited[i][j] = false;

        return ret;
    }
}
