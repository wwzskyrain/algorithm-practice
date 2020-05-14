package study.erik.algorithm.ds;

/**
 * @author
 */
public class SkipListEntry {

    /**
     * data
     */
    public String key;
    public Integer value;


    public SkipListEntry up = null;
    public SkipListEntry down = null;
    public SkipListEntry left = null;
    public SkipListEntry right = null;

    /**
     * special
     */
    public static final String negInf = "-oo";
    public static final String posInf = "+oo";

    /**
     * constructor
     */
    public SkipListEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
