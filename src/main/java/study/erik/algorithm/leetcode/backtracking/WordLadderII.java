package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2020-03-04 16:31
 * @description url = https://leetcode.com/problems/word-ladder-ii/
 * title = Word Ladder II
 * 分析：在I的基础上，找到所有的最近路径。但是广度搜索难取路径，为此三种方法
 * 1.   先用I中的广度搜索算法，找到最短路径长度shortestLength，然后用深度搜索找到这些路径，
 * 在深度搜索时用shortestLength做深度限制，从而加快遍历，不然肯定会超时的 ———— 来自三年前我的提交
 * 2.   用双队列来做BFS，一个队列用来存放结点，一个队列用来存放结点对应的路径————来自讨论区
 * 3.   用DFS，要优化，不然会搜索整个空间树；用当前已知最短深度来控制搜索路径。
 * 最后，把所有可能的路径过滤一下，用最短路径的长度来过滤。
 */
public class WordLadderII {

    /**
     * 解法三：优化的DFS
     * 评价：思想很完美，代码也和优美，可惜超时了；因为开始搜索时，不一定就能碰到最优解。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        init(wordList, endWord);
        dfsFindLadder(new LinkedList<>(Arrays.asList(beginWord)));

        return allShortestLadders.stream()
                .filter(ladder -> ladder.size() == this.shortedLength)
                .collect(Collectors.toList());

    }

    public List<List<String>> allShortestLadders = new ArrayList<>();
    public Integer shortedLength = Integer.MAX_VALUE;
    public Map<String, List<String>> allComboDict;
    public String endWord;

    public void init(List<String> wordList, String endWord) {
        initAllComboDict(wordList, endWord.length());
        this.endWord = endWord;
    }

    public void dfsFindLadder(Deque<String> currentLadder) {

        if (currentLadder.size() < this.shortedLength) {
            String currentWord = currentLadder.getLast();
            String copyCurrentWord = new String(currentWord);
            for (int i = 0; i < currentWord.length(); i++) {

                String transformWord = copyCurrentWord.substring(0, i) + "*" + copyCurrentWord.substring(i + 1);

                List<String> foundDictWords = allComboDict.get(transformWord);
                if (foundDictWords == null || foundDictWords.size() == 0) {
                    continue;
                }
                if (foundDictWords.contains(this.endWord)) {
                    Iterator<String> iterator = currentLadder.iterator();
                    List<String> copyCurrentLadder = new ArrayList<>();
                    while (iterator.hasNext()) {
                        copyCurrentLadder.add(iterator.next());
                    }
                    copyCurrentLadder.add(this.endWord);
                    allShortestLadders.add(copyCurrentLadder);

                    this.shortedLength = copyCurrentLadder.size();
                    continue;  //加速
                } else {
                    for (String dictWord : foundDictWords) {
                        if (!currentLadder.contains(dictWord)) {
                            currentLadder.addLast(dictWord);
                            dfsFindLadder(currentLadder);
                            currentLadder.removeLast();
                        }

                    }
                }
            }
        }
    }

    public void initAllComboDict(List<String> wordList, Integer wordLength) {

        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < wordLength; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        this.allComboDict = allComboDict;
    }

    @Test
    public void test() {
        String beginWord = "cet";
        String endWord = "ism";
        List<String> wordList = Arrays.asList("kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob");

        List<List<String>> allShortedLadders = findLadders(beginWord, endWord, wordList);

        for (List<String> allShortedLadder : allShortedLadders) {
            System.out.println(allShortedLadder);
        }
    }

    /**
     * 解法二：双队列的广度搜索：
     * 评价：时间和空间都不好，不过思想还是可以的
     * 注意点：
     * 1.因为要找到所有的最短路径，所以一直到一条最短路径后，不要停，要把这一层阶段都访问晚。
     * 所以要一层一层的访问：可以设置一个层结点入队，也可以直接读出该层的所有结点。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLaddersWithBfs(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return results;
        }
        Queue<String> queue = new LinkedList<>();
        Queue<List<String>> q_list = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(beginWord);
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        q_list.add(list);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> visited_list = new ArrayList<>();
            for (int i = 0; i < size; i++) { //一层一层的读取结点。
                String currentWord = queue.poll();
                List<String> curr_list = q_list.poll();
                for (int j = 0; j < currentWord.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String next = currentWord.substring(0, j) + c + currentWord.substring(j + 1);
                        if (visited.contains(next)) continue;
                        if (next.equals(endWord)) {
                            List<String> next_list = new ArrayList<>(curr_list);
                            next_list.add(next);
                            results.add(next_list);
                        } else if (dict.contains(next)) {
                            queue.add(next);
                            List<String> next_list = new ArrayList<>(curr_list);
                            next_list.add(next);
                            q_list.add(next_list);
                            visited_list.add(next);
                        }
                    }
                }
            }
            visited.addAll(visited_list);
            if (results.size() != 0) return results;
        }
        return results;
    }

    /**
     * 解法一：
     * 评价：三年前的提交的了，成绩中等。
     * 哎呦，竟然看不懂。我必须要弄懂
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        ArrayList<String> path = new ArrayList<String>();
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
            return result;
        }
        bfs(beginWord, endWord, wordList);
        dfs(endWord, beginWord, path, result);
        return result;
    }

    void dfs(String start, String end, ArrayList<String> pathArray,
             List<List<String>> result) {
        // 找到了，需要reverse加入的所有单词
        if (start.equals(end) == true) {
            pathArray.add(start);
            Collections.reverse(pathArray);
            result.add(pathArray);
            return;
        }
        if (path.get(start) == null) {
            return;
        }
        pathArray.add(start);
        int nextDepth = (int) path.get(start) - 1;
        for (int i = 0; i < start.length(); i++) {
            char[] strCharArr = start.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (strCharArr[i] == ch) {
                    continue;
                }
                strCharArr[i] = ch;
                String newWord = new String(strCharArr);
                // 只相差一个字母同时这个单词所在的层数也是当前单词的上一层
                if (path.get(newWord) != null && (path.get(newWord) == nextDepth)) {
                    ArrayList<String> newPathArray = new ArrayList<String>(pathArray);
                    dfs(newWord, end, newPathArray, result);
                }
            }
        }
    }

    HashMap<String, Integer> path = new HashMap<String, Integer>();

    // bfs生成path
    void bfs(String start, String end, Set<String> dict) {
        Queue queue = new LinkedList<String>();
        queue.add(start);
        path.put(start, 0);
        String current;
        while (!queue.isEmpty()) {
            current = (String) queue.poll();
            if (current == end) {
                continue;
            }
            for (int i = 0; i < current.length(); i++) {
                char[] strCharArr = current.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (strCharArr[i] == ch) {
                        continue;
                    }
                    strCharArr[i] = ch;
                    String newWord = new String(strCharArr);
                    if (newWord.equals(end) == true || dict.contains(newWord)) {
                        // 每个单词在path中只能出现一次，也就是每个单词只能出现在一层中，这样就很巧妙的解决了环的问题。
                        // 看不懂上一行的神奇的注释。我知道为啥把所有的可选地单词放在一个map中，大家都打平了。
                        // 因为如果在后续的层次（后续的bfs中）有出现了先前出现的word，说明当前出现的这个已经不是最优了，
                        // 直接不要了。
                        if (path.get(newWord) == null) {
                            int depth = (int) path.get(current);
                            path.put(newWord, depth + 1);
                            queue.add(newWord);
                        }
                    }
                }
            }
        }
    }
}
