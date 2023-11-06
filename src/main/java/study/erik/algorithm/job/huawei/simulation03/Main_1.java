package study.erik.algorithm.job.huawei.simulation03;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Main_1 {

    class UndergroundSystem {


        Map<Integer, Pair<String, Integer>> checkMap = new HashMap<>();

        Map<String, long[]> summaryMap = new HashMap<>();

        public UndergroundSystem() {

        }

        public void checkIn(int id, String stationName, int t) {
            checkMap.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<String, Integer> pair = checkMap.get(id);
            checkMap.remove(id);
            String inStation = pair.getKey();
            Integer intTime = pair.getValue();
            String key = getKey(inStation, stationName);
            long[] summary = summaryMap.getOrDefault(key, new long[2]);
            summary[0]++;
            summary[1] += (t - intTime);
            summaryMap.put(key, summary);
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = getKey(startStation, endStation);
            long[] summary = summaryMap.get(key);
            return summary[1] * 1.0/summary[0];
        }

        public String getKey(String inStation, String outStation) {
            //想多了，之前想从a到b与从b到a作为一个key来算了。
            return String.format("%s-%s", inStation, outStation);
        }
    }
}
