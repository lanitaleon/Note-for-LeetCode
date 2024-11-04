package easy;

/**
 * <h1>824 山羊拉丁文</h1>
 * <p>给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。</p>
 * <p>请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：</p>
 * <p>如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。</p>
 * <p>例如，单词 "apple" 变为 "applema" 。</p>
 * <p>如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。</p>
 * <p>例如，单词 "goat" 变为 "oatgma" 。</p>
 * <p>根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。</p>
 * <p>例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。</p>
 * <p>返回将 sentence 转换为山羊拉丁文后的句子。</p>
 * <h2>提示</h2>
 * <ul>
 *     <li>1 <= sentence.length <= 150</li>
 *     <li>sentence 由英文字母和空格组成</li>
 *     <li>sentence 不含前导或尾随空格</li>
 *     <li>sentence 中的所有单词由单个空格分隔</li>
 * </ul>
 */
public class ToGoatLatin {

    /**
     * 1ms 民解 把最后一个空格的判段变成删除最后一个空格优化了1ms
     */
    public String toGoatLatin2(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (isVowel(words[i].charAt(0))) {
                sb.append(words[i]);
            } else {
                sb.append(words[i].substring(1)).append(words[i].charAt(0));
            }
            sb.append("ma").append("a".repeat(i + 1)).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    /**
     * 2ms 我写的
     */
    public String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            sb.append(translate(words[i], i));
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private String translate(String word, int index) {
        StringBuilder sb = new StringBuilder();
        char c = word.charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            sb.append(word);
        } else {
            sb.append(word, 1, word.length());
            sb.append(word.charAt(0));
        }
        sb.append("ma");
        sb.append("a".repeat(index + 1));
        return sb.toString();
    }

    public static void main(String[] args) {
        ToGoatLatin g = new ToGoatLatin();
        System.out.println("Eachmaa ordwmaaa onsistscmaaaa ofmaaaaa owercaselmaaaaaa andmaaaaaaa uppercasemaaaaaaaa etterslmaaaaaaaaa onlymaaaaaaaaaa"
                .equals(g.toGoatLatin2("Each word consists of lowercase and uppercase letters only")));
        System.out.println("Imaa peaksmaaa oatGmaaaa atinLmaaaaa".equals(g.toGoatLatin("I speak Goat Latin")));
        System.out.println("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
                .equals(g.toGoatLatin("The quick brown fox jumped over the lazy dog")));
    }
}
