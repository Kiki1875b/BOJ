import java.util.*;
import java.io.*;

class Main{
        
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static int ans = Integer.MAX_VALUE;
    static String[] map;
    static int N, M;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        
        map = new String[N];
        visited = new boolean[N][M];
        
        for(int i = 0; i<N; i++){
            String tmp = br.readLine();
            
            map[i] = tmp;
        }
        
        bfs();
        System.out.println(ans + 1);
    }
    
    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0});
        
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            
            if(x == N -1 && y == M - 1){
                ans = Math.min(ans, cost);
                continue;
            }
            
            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    continue;
                }
                
                if(visited[nx][ny] || map[nx].charAt(ny) == '0') continue;
                
                q.add(new int[]{nx,ny,cost + 1});
                visited[nx][ny] = true;
                
            }
        }
    }


}