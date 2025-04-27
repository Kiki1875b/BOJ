

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Pair{
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
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
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class Solution {

    Pair downDir = new Pair(1,0);
    Pair rightDir = new Pair(0,1);
    Pair upDir = new Pair(-1, -1);
    public int[] solution(int n) {
        int[] answer = {};

        if(n == 1) return new int[]{1};
        
        int[][] triangle = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int i = 0, j = 0, num = 1;
        Pair curDir = downDir;
        while(true){
            triangle[i][j] = num;
            num++;
            visited[i][j] = true;
            int nx = i + curDir.x;
            int ny = j + curDir.y;

            if(nx == n || ny == n || visited[nx][ny]){
                curDir = nextDir(curDir);
                nx = i + curDir.x;
                ny = j + curDir.y;
            }

            if(visited[nx][ny]) break;

            i = nx;
            j = ny;
        }

        List<Integer> tmp = new ArrayList<>();
        for(int[] a: triangle){
            for(int b: a){
                if (b != 0){
                    tmp.add(b);
                }
            }
        }

        int[] ret = new int[tmp.size()];
        for(int a = 0; a<tmp.size(); a++){
            ret[a] = tmp.get(a);
        }
        
        return ret;
    }

   public Pair nextDir(Pair curDir){
       if (curDir == downDir) return rightDir;
       else if (curDir == rightDir) return upDir;
       else return downDir;
   }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.solution(4 );
//    }
}

