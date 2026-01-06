import java.util.*;
import java.io.*;

class Main {
    static int[][] grid;
    static int N, M;
    static int[][][] dist;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        
        grid = new int[N][M];
        
        for(int i = 0; i<N; i++){
            String tmp = br.readLine();
            
            for(int j = 0; j<tmp.length(); j++){
                grid[i][j] = Character.getNumericValue(tmp.charAt(j));
            }
        }
        dist = new int[N][M][2];
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                for(int k =0; k<2; k++){
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> (a[2] - b[2]));
        q.add(new int[]{0,0,1,1});
        
        dist[0][0][1] = 1;
        int ans = Integer.MAX_VALUE;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x=  cur[0];
            int y = cur[1];
            int cost = cur[2];
            int canBreak = cur[3];
            
            if(x == N - 1 && y == M - 1){
                ans = Math.min(ans, dist[x][y][canBreak]);
                continue;
            }
            
            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >=M) continue;
                int nCost = cost + 1;
                
                if(grid[nx][ny] == 1){
                    if(canBreak == 0 ) continue;
                    if(dist[nx][ny][0] > nCost){
                        dist[nx][ny][0] = nCost;
                        q.add(new int[]{nx, ny, nCost, 0});
                    }
                }else{
                    if(dist[nx][ny][canBreak] > nCost){
                        dist[nx][ny][canBreak] = nCost;
                        q.add(new int[]{nx, ny, nCost, canBreak});
                    }
                }
            }
        }
        
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}