package easy;

/**
 * <h1>1108</h1>
 * <p>给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。</p>
 * <p>所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>给出的 address 是一个有效的 IPv4 地址</li>
 * </ul>
 */
public class DefangIPaddr {
    public static void main(String[] args) {
        DefangIPaddr obj = new DefangIPaddr();
        System.out.println("1[.]1[.]1[.]1".equals(obj.defangIPaddr("1.1.1.1")));
    }

    /**
     * 谁出的垃圾题目
     */
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}
