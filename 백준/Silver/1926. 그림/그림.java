import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        int[][] arr = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0; i<N; i++){
            s = br.readLine().split(" ");
            for(int j = 0; j<M; j++){
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        
        int ans = 0;
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(arr[i][j] == 1 && !visited[i][j]){
                    cnt++;
                    ans = Math.max(ans, bfs(visited, arr, i, j));
                }
            }
        }
        
        System.out.println(cnt);
        System.out.println(ans);
        
        
    }
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0,0,1,-1};
    static int bfs(boolean[][] visited, int[][] arr, int x, int y){
        int n = visited.length;
        int m = visited[0].length;
        int ret = 1; 
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for(int i = 0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(arr[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                ret++;
            }
        }
        
        return ret;
    }
}