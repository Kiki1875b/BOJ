// package org.example;

import java.util.*;


class Pos {
  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean inRange(int n) {
    return x >= 0 && y >= 0 && x < n && y < n;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pos pos = (Pos) o;
    return x == pos.x && y == pos.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}

class Pair {
  Pos pos1, pos2;

  public Pair(Pos pos1, Pos pos2) {
    this.pos1 = pos1;
    this.pos2 = pos2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pair pair = (Pair) o;
    return (Objects.equals(pos1, pair.pos1) && Objects.equals(pos2, pair.pos2)) || (Objects.equals(pos1, pair.pos2) && Objects.equals(pos2, pair.pos1));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(pos1) + Objects.hashCode(pos2);
  }
}
class Solution {

  int[] dx = {1,-1,0,0};
  int[] dy = {0,0,1,-1};
  int n;
  public int solution(int[][] board) {
    n = board.length;
    Queue<Pair> q = new LinkedList<>();
    Queue<Integer> cost = new LinkedList<>();
    Set<Pair> visited = new HashSet<>();

    Pair start = new Pair(new Pos(0,0), new Pos(0,1));
    q.add(start);
    cost.add(0);
    visited.add(start);

    while(!q.isEmpty()){
      Pair cur = q.poll();
      int c = cost.poll();

      Pos pos1 = cur.pos1;
      Pos pos2 = cur.pos2;

      if((pos1.x == n - 1 && pos1.y == n - 1 ) || (pos2.x == n - 1 && pos2.y == n - 1)) {
        return c;
      }

      for(int i = 0; i < 4; i++){
        int nx1 = pos1.x + dx[i];
        int ny1 = pos1.y + dy[i];

        int nx2 = pos2.x + dx[i];
        int ny2 = pos2.y + dy[i];

        Pos np1 = new Pos(nx1, ny1);
        Pos np2 = new Pos(nx2, ny2);

        Pair nPair = new Pair(np1, np2);
        if(!np1.inRange(n) || !np2.inRange(n) || board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;
        if(visited.contains(nPair)) continue;

        visited.add(nPair);
        q.add(nPair);
        cost.add(c + 1);
      }

      boolean isHorizontal = pos1.x == pos2.x;

      if (isHorizontal){
        for(int d : new int[]{-1,1}){
          int nx = d + pos1.x;
          if(nx < 0 || nx >= n) continue;

          if(board[nx][pos1.y] == 0 && board[nx][pos2.y] == 0){
            Pair np1 = new Pair(pos1, new Pos(nx, pos1.y));
            Pair np2 = new Pair(pos2, new Pos(nx, pos2.y));

            if(!visited.contains(np1)){
              visited.add(np1);
              q.add(np1);
              cost.add(c + 1);
            }

            if(!visited.contains(np2)){
              visited.add(np2);
              q.add(np2);
              cost.add(c + 1);
            }
          }
        }
      } else {
        for(int d : new int[]{-1,1}){
          int ny = d + pos1.y;
          if(ny < 0 || ny >= n) continue;
          if(board[pos1.x][ny] == 0 && board[pos2.x][ny] == 0){
            Pair np1 = new Pair(pos1, new Pos(pos1.x, ny));
            Pair np2 = new Pair(pos2, new Pos(pos2.x, ny));

            if(!visited.contains(np1)){
              visited.add(np1);
              q.add(np1);
              cost.add(c + 1);
            }

            if(!visited.contains(np2)){
              visited.add(np2);
              q.add(np2);
              cost.add(c + 1);
            }
          }
        }
      }

    }

    return -1;
  }
}
