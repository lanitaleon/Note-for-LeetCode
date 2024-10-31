package easy;

/**
 * <h1>744 寻找比目标字母大的最小字母</h1>
 * <p>给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。</p>
 * <p>返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>2 <= letters.length <= 10^4</li>
 *     <li>letters[i] 是一个小写字母</li>
 *     <li>letters 按非递减顺序排序</li>
 *     <li>letters 最少包含两个不同的字母</li>
 *     <li>target 是一个小写字母</li>
 * </ul>
 */
public class NextGreatestLetter {

    /**
     * 0ms 官解，嗯，都是二分，看看差距
     */
    public char nextGreatestLetter2(char[] letters, char target) {
        int length = letters.length;
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        while (low < high) {
            // 这个二分可以避免死循环，别失忆了
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }

    /**
     * 0ms 我写的
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int r = letters.length - 1;
        if (letters[r] <= target) {
            return letters[l];
        }
        if (letters[l] > target || letters[r] == letters[l]) {
            return letters[l];
        }
        while (l < r) {
            if (r - l == 1) {
                return letters[l] > target ? letters[l] : letters[r];
            }
            int m = (l + r) / 2;
            if (letters[m] <= target) {
                l = m;
            } else if (letters[m] > target) {
                r = m;
            }
        }
        return letters[0];
    }

    public static void main(String[] args) {
        NextGreatestLetter nextGreatestLetter = new NextGreatestLetter();
        System.out.println('c' == nextGreatestLetter.nextGreatestLetter2(new char[]{'c', 'f', 'j'}, 'j'));
        System.out.println('j' == nextGreatestLetter.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g'));
        System.out.println('x' == nextGreatestLetter.nextGreatestLetter(new char[]{'x', 'x', 'y', 'y'}, 'z'));
        System.out.println('c' == nextGreatestLetter.nextGreatestLetter(new char[]{'c', 'f', 'g'}, 'a'));
        System.out.println('f' == nextGreatestLetter.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
    }
}
