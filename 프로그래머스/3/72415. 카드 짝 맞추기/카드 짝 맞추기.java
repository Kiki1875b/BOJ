// package org.example;

import java.util.*;

class Pos{
  int x, y;

  public Pos (int x, int y){
    this.x = x;
    this.y = y;
//    this.cnt = cnt;
  }
}
class Solution {
  int N = 4;
  int[] dx = new int[]{1,-1,0,0};
  int[] dy = new int[]{0,0,-1,1};

  List<List<Integer>> candidates = new ArrayList<>();
//  int ans = Integer.MAX_VALUE;
  int[][] board;
  public int solution(int[][] board, int r, int c) {

    int answer = Integer.MAX_VALUE;
    this.board = board;
    Set<Integer> distinctNumbers = new HashSet<>();
    Map<Integer, List<Pos>> m = new HashMap<>();

    for(int i = 0; i<board.length; i++){
      for(int j = 0; j<board[0].length; j++){
        if(board[i][j] != 0){
          distinctNumbers.add(board[i][j]);
          m.computeIfAbsent(board[i][j], k -> new ArrayList<>()).add(new Pos(i,j));
        }
      }
    }


    List<Integer> numbers = new ArrayList<>(distinctNumbers);

    return dfs(board, r, c, numbers, m, new boolean[7]);
  }


  int dfs(int[][] board, int r, int c, List<Integer> numbers, Map<Integer, List<Pos>> posMap, boolean[] removed){

    boolean completed = true;
    for(int num : numbers){
      if(!removed[num]){
        completed = false;
        break;
      }
    }
    if(completed) return 0;

    int best = Integer.MAX_VALUE;
    for(int num : numbers){
      if(removed[num]) continue;

      List<Pos> ps = posMap.get(num);
      Pos p1 = ps.get(0), p2 = ps.get(1);

      int d1 = bfs(board, r, c, p1.x, p1.y);
      int backup = board[p1.x][p1.y];
      board[p1.x][p1.y] = 0;

      int d2 = bfs(board, p1.x, p1.y, p2.x, p2.y);
      int backup2 = board[p2.x][p2.y];
      board[p2.x][p2.y] = 0;

      removed[num] = true;

      int cost1 = d1 + d2 + 2 + dfs(board, p2.x, p2.y, numbers, posMap, removed);

      removed[num] = false;
      board[p1.x][p1.y] = backup;
      board[p2.x][p2.y] = backup2;

      int d3 = bfs(board, r, c, p2.x, p2.y);
      int backup3 = board[p2.x][p2.y];
      board[p2.x][p2.y] = 0;

      int d4 = bfs(board, p2.x, p2.y, p1.x, p1.y);
      int backup4 = board[p1.x][p1.y];
      board[p1.x][p1.y] = 0;

      removed[num] = true;
      int cost2 = d3 + d4 + dfs(board, p1.x, p1.y, numbers, posMap, removed) + 2;
      removed[num] = false;
      board[p2.x][p2.y] = backup3;
      board[p1.x][p1.y] = backup4;

      best = Math.min(best, Math.min(cost1, cost2));
    }

    return best;
  }

  int bfs(int[][] board, int startX, int startY, int goalX, int goalY){

    if(startX == goalX && startY == goalY) return 0;

    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{startX, startY, 0});
    visited[startX][startY] = true;

    while(!q.isEmpty()){
      int[] cur = q.poll()  ;
      int cx = cur[0];
      int cy = cur[1];
      int cost = cur[2];


      for(int i = 0 ; i<4; i++){
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(!inRange(nx, ny)) continue;
        if(visited[nx][ny]) continue;
        if(nx == goalX && ny == goalY) return cost + 1;
        visited[nx][ny] = true;
        q.add(new int[]{nx,ny,cost + 1});
      }

      for(int i = 0; i<4; i++){
        int nx = cx;
        int ny = cy;

        while(true){
          nx += dx[i];
          ny += dy[i];

          if(!inRange(nx, ny)){
            nx -= dx[i];
            ny -= dy[i];
            break;
          }
          if(board[nx][ny] != 0){
            break;
          }
        }

        if(visited[nx][ny]) continue;
        if(nx == goalX && ny == goalY) return cost + 1;
        visited[nx][ny] = true;
        q.add(new int[]{nx, ny, cost + 1});
      }
    }

    return -1;
  }

  boolean inRange(int x, int y){
    return x >= 0 && y >= 0 && x < N && y < N;
  }

  public static void main(String[] args) {
    Solution s = new Solution();

//    s.solution(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,1,0}}, 3,0);
    s.solution(new int[][]{{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}}, 1, 0);

    s.solution(new int[][]{{3, 0, 0, 2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1);
  }
}
