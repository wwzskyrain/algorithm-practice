package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author erik.wang
 * @date 2020-08-18 23:41
 * 阿里二面
 */
public class Main {

    @Test
    public void test_max_long_sub_string() throws ExecutionException, InterruptedException {
        String[] words = new String[1000];
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 100; j++) {
                sb.append(random.nextInt(50) - '0');
            }
            words[i] = sb.toString();
        }
        String s = maxLongthSubString(words);
        System.out.println(s);
    }

    public String maxLongthSubString(String[] words) throws InterruptedException, ExecutionException {

        int wordSize = words.length;
        final int treadSize = 20;
        ExecutorCompletionService<String> executor = new ExecutorCompletionService(Executors.newFixedThreadPool(treadSize));
        for (int i = 0; i < words.length; i++) {
            executor.submit(new Task(words[i]));
        }

        int maxLenght = 0;
        String maxSubStr = "";
        for (int i = 0; i < wordSize; i++) {
            Future future = executor.take();
            String word = (String) future.get();
            if (word.length() > maxLenght) {
                maxSubStr = word;
            }
        }
        return maxSubStr;

    }

    public class Task implements Callable<String> {
        private String word;

        public Task(String word) {
            this.word = word;
        }

        @Override
        public String call() throws Exception {
            return findLongSubString(word);
        }
    }

    @Test
    public void test_find_long_sub_string() {
        Assert.assertEquals("13", findLongSubString("1334"));
    }

    public String findLongSubString(String word) {

        String maxStr = "";
        int l = 0;
        int h = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (h < word.length()) {
            char c = word.charAt(h);
            Integer count = map.getOrDefault(c, 0);
            if (count.equals(0)) {
                count++;
                map.put(c, 1);
                h++;
                if (h - l > maxStr.length()) {
                    maxStr = word.substring(l, h + 1);
                }
                continue;
            }
            char c1;
            do {
                c1 = word.charAt(l++);
                map.put(c1, 0);
            } while (c1 != c);
        }
        if (h - l > maxStr.length()) {
            maxStr = word.substring(l, h + 1);
        }
        return maxStr;
    }

    public int[] merger(int[] a, int b[]) {
        int[] c = new int[a.length + b.length];
        int aIndex = 0, bIndex = 0, cIndex = 0;

        while (aIndex < a.length && bIndex < b.length) {
            int av = a[aIndex], bv = b[bIndex];
            if (av == bv) {
                c[cIndex++] = av;
                aIndex++;
                bIndex++;
            } else {
                c[cIndex++] = av < bv ? a[aIndex++] : b[bIndex++];
            }
        }
        while (aIndex < a.length) {
            c[cIndex++] = a[aIndex++];
        }
        while (bIndex < b.length) {
            c[cIndex++] = b[bIndex];
        }
        return Arrays.copyOf(c, cIndex);
    }

    @Test
    public void test_solution() {
        int[] a = {2, 10, 14, 19, 51, 71}, b = {2, 9, 10, 14, 19, 40, 51};
        int[] c = {2, 9, 10, 14, 19, 40, 51, 71};
        Assert.assertArrayEquals(c, merger(a, b));
    }

}
