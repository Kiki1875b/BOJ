import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class UnionFind {
  private int[] parent;
  private int[] size;

  public UnionFind(int n) {
    parent = new int[n];
    size = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i; // 초기화시 자신의 부모는 자신
      size[i] = 1;  // 초기화시 각 node 의 크기는 1
    }
  }

  /*
  필요한 연산
  1. root 찾기
  2. union
  3. size 찾기
   */
  public int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) return; // 이미 같은 집합

    if (size[rootX] > size[rootY]) { // 크기가 더 큰쪽을 부모로
      parent[rootY] = rootX;
      size[rootX] += size[rootY];
    } else {
      parent[rootX] = rootY;
      size[rootY] += size[rootX];
    }
  }

  public int getSize(int x) {
    return size[find(x)];
  }
}

class Solution {
  int[] dx = {1, -1, 0, 0};
  int[] dy = {0, 0, 1, -1};
  boolean[][] visited;
  int n, m;

  public int solution(int[][] land) {
    int answer = 0;

    n = land.length;
    m = land[0].length;
    visited = new boolean[n][m];
    UnionFind uf = new UnionFind(n * m);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (land[i][j] == 1) {
          for(int k = 0; k<4; k++){
            int ni = i + dx[k];
            int nj = j + dy[k];
            if((ni >= 0 && nj >=0 && ni < n && nj < m) && land[ni][nj] == 1){
              uf.union(i * m + j, ni * m + nj);
            }
          }
        }
      }
    }

    Set<Integer> roots =new HashSet<>();

    for(int i = 0; i<m; i++){
      for(int j = 0; j<n; j++){
        if(land[j][i] == 1){
          roots.add(uf.find(j *m + i));
        }
      }
      
      int sum = 0;
      for(Integer r : roots){
        sum += uf.getSize(r);
      }
      
      answer = Math.max(answer, sum);
      roots.clear();
    }

    return answer;
  }
}