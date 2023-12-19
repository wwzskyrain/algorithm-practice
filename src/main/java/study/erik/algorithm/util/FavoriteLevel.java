package study.erik.algorithm.util;

public enum FavoriteLevel {

    FIVE_STAR("五星"),
    FOUR_STAR("四星"),
    THREE_STAR("三星"),
    TWO_STAR("二星"),
    ONE_STAR("一星");

    private final String name;

    FavoriteLevel(String name) {
        this.name = name;
    }

}
