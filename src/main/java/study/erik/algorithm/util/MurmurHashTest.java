package study.erik.algorithm.util;

import org.apache.commons.codec.digest.MurmurHash3;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-04 11:02
 * @description
 */
public class MurmurHashTest {


    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            String key = "name" + i;
            System.out.println(key + ":" + MurmurHash3.hash32(key.getBytes()));
        }
        System.out.println("----------");
        for (int i = 0; i < 10; i++) {
            String key = "name" + i;
            System.out.println(key + ":" + MurmurHash3.hash64(key.getBytes()));
        }
    }


}
