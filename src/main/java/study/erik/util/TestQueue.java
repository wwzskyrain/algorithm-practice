package study.erik.util;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestQueue {

    public static void main(String[] args) {
        PriorityQueue<Pair<Integer, String>> q = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.add(new Pair<>(2, "3"));
        q.add(new Pair<>(1, "1"));
        q.add(new Pair<>(1, "1-1"));
        q.add(new Pair<>(2, "2"));
        System.out.println(q.poll());
        q.add(new Pair<>(1, "1-1-1"));
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());

    }

}
