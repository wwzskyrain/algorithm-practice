/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : ShoppingOffers.java, v 0.1 2022年04月03日 7:42 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShoppingOffers {

    @LetCodeCommit(title = "638. Shopping Offers",
            selfRemark = "这个题目有点难搞，先不写了.2022-04-03")
    public int shoppingOffers(List<Integer> price,
                              List<List<Integer>> special,
                              List<Integer> needs) {

        List<List<Integer>> finalSpecial = new ArrayList<>();
        special.forEach(offer -> {
            int min = 0x7fffffff;
            for (int i = 0; i < needs.size(); i++) {
                int n = needs.get(i);
                min = Math.min(min, n / offer.get(i));
            }
            while (min > 0) {
                List<Integer> newOffer = new ArrayList<>();
                for (int i = 0; i < offer.size(); i++) {
                    newOffer.add(offer.get(i) * min);
                }
                finalSpecial.add(newOffer);
                min--;
            }
        });

        IntStream.of(price.size())
                .forEach(i -> {
                    Integer need = needs.get(i);
                    while (need > 0) {
                        Integer[] arr = new Integer[price.size() + 1];
                        arr[i] = price.get(i) * need;
                        arr[arr.length - 1] = arr[i];
                        finalSpecial.add(Arrays.asList(arr));
                        need--;
                    }
                });

        return 0;
    }

    @Parameter
    public List<Integer>       price;
    @Parameter(1)
    public List<List<Integer>> special;
    @Parameter(2)
    public List<Integer>       needs;
    @Parameter(3)
    public int                 expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, shoppingOffers(price, special, needs));
    }

}