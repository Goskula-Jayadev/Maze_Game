/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphAlgo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jayad
 */
public class DFS {
    public static boolean searchPath(int[][] maze,int x,int y,List<Integer> path){
        if(maze[x][y] == 9){
        path.add(x);
        path.add(y);
        return true;
        }
        
        if(maze[x][y] == 0){
        maze[x][y]=2;//marking as visited
        int[] dx ={1,0,-1,0};
        int[] dy = {0,1,0,-1};
        
        for(int d=0; d<dx.length; d++){
            int newx=x+dx[d];
            int newy=y+dy[d];
           
            if(newx>=0 && newx<maze.length && newy>=0 && newy<maze[0].length && searchPath(maze,newx,newy,path)){path.add(x); path.add(y); return true;}
            }
        }
        return false; //cell is already visited(2) or it is wall(1)
        }
    public static void main(String[] args){
         int[][] maze ={ //0-path 1-walls 9-end point
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1},

    };
      DFS dfs = new DFS();
      List<Integer> path = new ArrayList<Integer>();
      boolean result = dfs.searchPath(maze, 1, 1, path);
      System.out.print(result);
    }
}
