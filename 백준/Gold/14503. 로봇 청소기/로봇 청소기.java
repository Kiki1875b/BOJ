import java.util.*;
import java.io.*;

class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int[][] map = new int[N][M];

        s = br.readLine().split(" ");
        
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);
        
        for(int i = 0; i<N; i++){
            s = br.readLine().split(" ");
            for(int j = 0; j<s.length; j++){
                map[i][j] = Integer.parseInt(s[j]);    
            }
        }
        int ans = 0;
        while(true){
            if(map[x][y] == 0){
                ans++;
                map[x][y] = 2;
            }
            
            boolean moved = false;
            
            for(int i = 0; i<4; i++){
                d = (d + 3) % 4;
                int nx = x + dr[d];
                int ny = y + dc[d];
                
                if (map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    moved = true;
                    break;
                }
            }
            
            if(moved) continue;
            int backDir = (d + 2) % 4;
            int bx = x + dr[backDir];
            int by = y + dc[backDir];

            if (map[bx][by] == 1) break; 

            x = bx;
            y = by;
        }
        System.out.println(ans);

    }
    
}