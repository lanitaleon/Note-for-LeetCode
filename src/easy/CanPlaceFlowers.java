package easy;

/**
 * <h1>605 种花问题</h1>
 * <p>假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。</p>
 * <p>给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。</p>
 * <p>另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= flowerbed.length <= 2 * 10^4</li>
 *     <li>flowerbed[i] 为 0 或 1</li>
 *     <li>flowerbed 中不存在相邻的两朵花</li>
 *     <li>0 <= n <= flowerbed.length</li>
 * </ul>
 */
public class CanPlaceFlowers {

    public static void main(String[] args) {
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
        System.out.println(canPlaceFlowers.canPlaceFlowers3(new int[]{0, 0, 1, 0, 1}, 1));
        System.out.println(!canPlaceFlowers.canPlaceFlowers3(new int[]{1, 0, 0, 0, 0, 1}, 2));
        System.out.println(!canPlaceFlowers.canPlaceFlowers3(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers.canPlaceFlowers3(new int[]{1, 0, 0, 0, 0}, 2));
        System.out.println(canPlaceFlowers.canPlaceFlowers3(new int[]{1, 0, 0, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers.canPlaceFlowers3(new int[]{1, 0, 0, 0, 1}, 1));

    }

    /**
     * 民解 3ms
     */
    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        // 这个思路很丝滑，我怎么会写成那样...
        // 当前值是 1 即后一位不可能种，直接 + 2
        // 当前值是 0 如果到末尾了或者下一位是 0，可以种
        // 当前值是 0，没到末尾，下一位是1，不可以种，且 +3
        for (int i = 0, len = flowerbed.length; i < len && n > 0; ) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                i += 2;
                n--;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }

    /**
     * 我写的 1ms
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        int len = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                len++;
                if (len == 2) {
                    if (i == 1 || i == flowerbed.length - 1) {
                        len = 1;
                        n--;
                        if (n == 0) {
                            return true;
                        }
                    }
                } else if (len == 3) {
                    len = 1;
                    n--;
                    if (n == 0) {
                        return true;
                    }
                }
            } else {
                len = 0;
            }
        }
        return len == flowerbed.length;
    }

    /**
     * 我写的 1ms
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0) {
                    if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                        n--;
                        i++;
                        if (n == 0) {
                            return true;
                        }
                    }
                } else if (i == flowerbed.length - 1) {
                    if (flowerbed[i - 1] == 0) {
                        n--;
                        i++;
                        if (n == 0) {
                            return true;
                        }
                    }
                } else {
                    if (flowerbed[i + 1] == 0 && flowerbed[i - 1] == 0) {
                        n--;
                        i++;
                        if (n == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
