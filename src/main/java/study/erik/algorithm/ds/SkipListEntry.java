package study.erik.algorithm.ds;

public class SkipListEntry {

    // data
    public String key;
    public Integer value;

    // links
    public SkipListEntry up;
    public SkipListEntry down;
    public SkipListEntry left;
    public SkipListEntry right;

    // special
    public static final String negInf = "-oo";
    public static final String posInf = "+oo";

    // constructor
    public SkipListEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
