
import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] map;
    static long[][][] memo;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        memo = new long[N][N][3];

        for(int i = 0; i<N; i++){
            String[] s = br.readLine().split(" ");
            for(int j = 0; j<s.length; j++){
                map[i][j] = Integer.parseInt(s[j]);
                Arrays.fill(memo[i][j], -1);
            }
        }

        System.out.println(dfs(0,1,0));


    }

    static long dfs(int x, int y, int dir){
        if (x == N - 1 && y == N - 1) return 1;


        if(memo[x][y][dir] != -1){
            return memo[x][y][dir];
        }

        memo[x][y][dir] = 0;

        for(int[] d : nextDir(dir)){
            int nx = x + d[0];
            int ny = y + d[1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(d[2] == 0 && map[nx][ny] == 1) continue;
            if(d[2] == 1 && map[nx][ny] == 1) continue;
            if(d[2] == 2 && (map[nx][ny] == 1 || map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1))continue;

            memo[x][y][dir] += dfs(nx, ny, d[2]);
        }

        return memo[x][y][dir];
    }




    static int[][] nextDir(int cur){
        // 0 = horizontal, 1 = vertical, 2 = diagonal
        if(cur == 0){
            return new int[][]{
                {0,1,0}, {1,1,2}
            };
        } else if (cur == 1){
            return new int[][]{
                {1,0,1}, {1,1,2}
            };
        } else {
            return new int[][]{
                {0, 1, 0}, {1, 0, 1}, {1,1, 2}
            };
        }
    }
}
