package study.erik.algorithm.leetcode.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author erik.wang
 * @date 2019/03/23
 **/
public class Solution {

    public static int mobileSelectStation(int[] houseCoordinate, int radius) {

        if (houseCoordinate == null || houseCoordinate.length < 1) {
            return 0;
        }

        if (houseCoordinate.length == 1) {
            return 1;
        }

        int baseHouseIndex = 0;
        int stationCount = 0;
        while (true) {

            int targetStationIndex = baseHouseIndex;
            int baseIndex1 = baseHouseIndex + 1;

            while (true) {
                if (baseIndex1 >= houseCoordinate.length) {
                    break;
                }
                if (houseCoordinate[baseIndex1] - houseCoordinate[baseHouseIndex] <= radius) {
                    targetStationIndex = baseIndex1;
                    baseIndex1++;
                } else {
                    break;
                }
            }
            stationCount++;
            int nextBaseHouseIndex = selectNextBaseHouseIndex(targetStationIndex, houseCoordinate, radius);
            if (nextBaseHouseIndex > 0) {
                baseHouseIndex = nextBaseHouseIndex;
                continue;
            } else {
                break;
            }


        }

        return stationCount;
    }

    /**
     * 基于当前的基站坐标，即baseIndex的值，选择下一个未覆盖房子的最小坐标
     *
     * @param targetStationIndex 当前基站位子
     * @param houseArray         房子坐标数组
     * @return -2,参数错误；0：已经不需要再建立基站了；>0 下一个所有未覆盖的房子的最小坐标
     */
    public static int selectNextBaseHouseIndex(int targetStationIndex, int[] houseArray, int radius) {

        int nextBaseHouseIndex = targetStationIndex + 1;
        while (true) {
            if (nextBaseHouseIndex >= houseArray.length) {
                break;
            }
            if (houseArray[nextBaseHouseIndex] - houseArray[targetStationIndex] <= radius) {
                nextBaseHouseIndex++;
            } else {
                break;
            }
        }

        return nextBaseHouseIndex >= houseArray.length ? 0 : nextBaseHouseIndex;
    }

    @Test
    public void test_mobile_select_station() {
        int[] houseArray1 = {1, 2, 3, 4, 5};
        int[] houseArray2 = {2, 4, 5, 6, 7, 11, 12};
        Assertions.assertEquals(2, mobileSelectStation(houseArray1, 1));
        Assertions.assertEquals(3, mobileSelectStation(houseArray2, 2));
    }


}
