import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int K = Integer.parseInt(s[2]);
        
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        int[][] graph = new int[N][M];
        for(int i = 0 ; i < N; i++){
            String tmp = br.readLine();
            for(int j = 0; j<tmp.length();j++){
                graph[i][j] = tmp.charAt(j) - '0';
            }
        }
        
        boolean[][][] visited = new boolean[N][M][K+1];
        
        
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1,K});
        visited[0][0][K] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            int k = cur[3];
            
            if(x == N - 1 && y == M - 1){
                System.out.println(cost);
                return;
            }
            
            for(int i =0 ; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nk = k;
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                int nCost = cost + 1;
                
                 
                
                if(graph[nx][ny] == 1 && nk > 0){
                    nk--;
                    if(visited[nx][ny][nk]) continue;
                    visited[nx][ny][nk] = true;
                    q.add(new int[]{nx, ny, nCost, nk});
                }else if(graph[nx][ny] == 0){
                    if(visited[nx][ny][nk]) continue;
                    visited[nx][ny][nk] = true;
                    q.add(new int[]{nx,ny,nCost, nk});
                }
            }
        }
        
         System.out.println(-1);   
    }
}