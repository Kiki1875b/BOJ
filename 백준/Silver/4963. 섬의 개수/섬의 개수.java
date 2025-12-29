
import java.util.*;
import java.io.*;

class Main {
    static int[] dx = new int[]{1,-1,0,0,1,-1,-1,1};
    static int[] dy = new int[]{0,0,1,-1,1,-1,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String[] s = br.readLine().split(" ");
            int w = Integer.parseInt(s[0]);
            int h = Integer.parseInt(s[1]);

            if(w == 0 && h == 0) break;

            int[][] arr = new int[h][w];

            for(int i = 0; i<h; i++){
                s = br.readLine().split(" ");
                for(int j = 0; j<w; j++){
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }

            boolean[][] v = new boolean[h][w];
            int res = 0;
            for(int i = 0; i<h; i++){
                for(int j = 0; j<w; j++){
                    if(arr[i][j] == 1 && !v[i][j]){
                        bfs(i, j, v, arr, h, w);
                        res++;
                    }
                }
            }

            System.out.println(res);
        }
    }

    static void bfs(int x, int y, boolean[][] v, int[][] arr, int h, int w){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{x, y});
        v[x][y] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for(int i = 0; i<8; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                if(arr[nx][ny] != 1) continue;
                if(v[nx][ny]) continue;

                v[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }

        }
    }
}
