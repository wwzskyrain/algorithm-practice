package study.erik.algorithm.job.zijie.simulation13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/21 11:02
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Min_Cost_to_Connect_All_Points {

    @LetCodeCommit(title = "1584. Min Cost to Connect All Points")
    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();
        int  n = points.length;
        U_F uf = new U_F(n);
        int[][] p = points;
        for(int i = 0;i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int dis = Math.abs(p[i][0] - p[j][0]) + Math.abs(p[i][1] - p[j][1]);
                edges.add(new Edge(dis, i ,j));
            }
        }
        edges.sort(Comparator.comparingInt(e -> e.len));
        int total = 0;
        int visitedNum = 1;
        for(Edge e : edges) {
            int x = e.x;
            int y = e.y;
            if(uf.unionSet(x, y)){
                total += e.len;
                visitedNum++;
                if(visitedNum == n) {
                    //早点返回
                    break;
                }
            }
        }
        return total;
    }

    public static class U_F{

        int[] p;
        int n;
        int[] rank;

        public U_F(int n){
            this.n = n;
            p = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
                rank[i] = 1;
            }
        }

        public int findParent(int x){
            while(x != p[x]) {
                p[x] = p[p[x]];
                x = p[x];
            }
            return x;
        }

        public boolean unionSet(int x, int y){

            int xP = findParent(x);
            int yP = findParent(y);
            if(xP == yP) {
                return false;
            }
            // 这里大意了
            if(rank[xP] < rank[yP]) {
                p[xP] = yP;
                rank[yP] += rank[xP];
            }else {
                p[yP] = xP;
                rank[xP] += rank[yP];
            }
            return true;
        }

    }

    public static class Edge {
        int len;
        int x;
        int y;
        public Edge(int l, int x, int y) {
            this.len = l;
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return String.format("%d-%d-%d", len, x, y);
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {20, ArrayUtils.buildArray2Dimension("[[0,0],[2,2],[3,10],[5,2],[7,0]]")},
                {18, ArrayUtils.buildArray2Dimension("[[3,12],[-2,5],[-4,1]]")},
                {85, ArrayUtils.buildArray2Dimension("[[7,18],[-15,19],[-18,-15],[-7,14],[4,1],[-6,3]]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] points;


    @Test
    public void test() {
        Assert.assertEquals(expect, minCostConnectPoints(points));
    }

}
