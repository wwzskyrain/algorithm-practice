/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.grid;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumNumberOfDaysToDisconnectIsland.java, v 0.1 2023年05月29日 02:00 yueyi Exp $
 */
public class MinimumNumberOfDaysToDisconnectIsland {

    @LetCodeCommit(title = "1568. Minimum Number of Days to Disconnect Island",
    diff = "h",
    selfRemark = "这个题目看着是个难题，其实是个巧题目。"
            + "只要意识到，最多用两步就能割裂出最角落(无论四个角落中的哪一个)的那个岛屿。")
    public int minDays(int[][] grid) {
        if(noOfIsland(grid) != 1){
            return 0;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    if(noOfIsland(grid) != 1){
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private int noOfIsland(int [][]grid){
        int ans = 0;
        boolean [][]visited = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    ans ++;
                    dfs(visited, grid,i,j);
                }
            }
        }
        return ans;
    }

    private void dfs(boolean [][]visited, int [][]grid,int i, int j){
        if(i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == 0){
            return ;
        }
        visited[i][j] = true;
        dfs(visited, grid, i-1, j);
        dfs(visited, grid, i+1, j);
        dfs(visited, grid, i, j-1);
        dfs(visited, grid, i, j+1);
    }

}