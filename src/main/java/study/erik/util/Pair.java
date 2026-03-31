package study.erik.util;

public class Pair<KEY, VALUE> {

    private KEY key;
    private VALUE value;

    public Pair(KEY k, VALUE v) {
        this.key = k;
        this.value = v;
    }

    public KEY getKey() {
        return key;
    }

    public VALUE getValue() {
        return value;
    }

}
