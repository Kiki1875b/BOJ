import java.util.*;
import java.io.*;

class Main {
    
    static int[][] dp;
    static int goalX;
    static int goalY;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int[][] m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
         m = new int[N][M];
        goalX = N - 1;
        goalY = M - 1;
        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j<M; j++){
                m[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        dp = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                dp[i][j] = -1;
            }
        }
        dp[N - 1][M - 1] = 1;
        System.out.println(dfs(0,0));
        
    }
    
    static int dfs(int x, int y){
        if(dp[x][y] != -1) return dp[x][y];
        if(x == goalX && y == goalY){
            return 1;
        }
        
        dp[x][y] = 0;
        for(int i = 0; i<4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            
            
            if(nx <0 || ny <0 || nx >goalX || ny > goalY) continue;
            
            if(m[nx][ny] < m[x][y]){
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }
}