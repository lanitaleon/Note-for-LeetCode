package easy;

/**
 * <h1>492 构造矩形</h1>
 * <p>作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 </p>
 * <p>所以，现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。</p>
 * <p>要求：</p>
 * <ul>
 *     <li>你设计的矩形页面必须等于给定的目标面积。</li>
 *     <li>宽度 W 不应大于长度 L ，换言之，要求 L >= W 。</li>
 *     <li>长度 L 和宽度 W 之间的差距应当尽可能小。</li>
 *     <li>返回一个 数组 [L, W]，其中 L 和 W 是你按照顺序设计的网页的长度和宽度。</li>
 * </ul>
 * <h2>提示</h2>
 * <p>1 <= area <= 10^7</p>
 */
public class ConstructRectangle {
    public static void main(String[] args) {
        ConstructRectangle cr = new ConstructRectangle();
        // [2, 2]
        System.out.println(2 == cr.constructRectangle(4)[0]);
        // [37,1]
        System.out.println(37 == cr.constructRectangle4(37)[0]);
        // [427,286]
        System.out.println(427 == cr.constructRectangle3(122122)[0]);
        // [2,1]
        System.out.println(2 == cr.constructRectangle2(2)[0]);
    }

    /**
     * 官解 0ms 我还以为这题会有人枚举测试用例呢 还是太简单了都懒得弄了吧
     */
    public int[] constructRectangle4(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            --w;
        }
        return new int[]{area / w, w};
    }

    /**
     * 民解 0ms
     */
    public int[] constructRectangle3(int area) {
        for (int w = (int) Math.floor(Math.sqrt(area)); w > 0; w--) {
            if (area % w == 0) return new int[]{area / w, w};
        }
        return new int[]{-1, -1};
    }

    /**
     * 根据3改的，为什么从小到大比从大到小慢那么多 0ms
     */
    public int[] constructRectangle2(int area) {
        double squareRoot = Math.sqrt(area);
        int root = (int) Math.floor(squareRoot);
        while (root > 0 && area % root != 0) {
            root--;
        }
        return new int[]{area / root, root};
    }

    /**
     * 我写的 37ms
     */
    public int[] constructRectangle(int area) {
        double squareRoot = Math.sqrt(area);
        int root = (int) squareRoot;
        if (squareRoot % 1 == 0) {
            return new int[]{root, root};
        }
        root++;
        while (root <= area && area % root != 0) {
            root++;
        }
        return new int[]{root, area / root};
    }
}
