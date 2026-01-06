

import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");

        int W = Integer.parseInt(s[0]);
        int H = Integer.parseInt(s[1]);

        int[][] graph = new int[H][W];

        for(int i = 0; i<H; i++){
            s = br.readLine().split(" ");
            for(int j=0; j<s.length; j++){
                graph[i][j] = Integer.parseInt(s[j]);
            }
        }

        int[][] horseMove = new int[][]{
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2}
        };

        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};

        Queue<int[]> q = new LinkedList<>();
        int[][][] visited = new int[H][W][K  + 1];

        q.add(new int[]{0,0,0,K});

        for(int i =0 ; i<visited.length; i++){
            for(int j = 0; j<visited[0].length; j++){
                for(int k = 0; k<visited[i][j].length; k++){
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        visited[0][0][K] = 0;

        while(!q.isEmpty()){
            int[] cur =q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            int k = cur[3];

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(visited[nx][ny][k] <= cost + 1) continue;
                if(graph[nx][ny] == 1) continue;

                visited[nx][ny][k] = cost + 1;
                q.add(new int[]{nx, ny, cost + 1, k});
            }

            if(k <= 0) continue;
            for(int[] dir : horseMove){
                int nk = k - 1;
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(visited[nx][ny][nk] <= cost + 1) continue;
                if(graph[nx][ny] == 1) continue;

                visited[nx][ny][nk] = cost + 1;
                q.add(new int[]{nx, ny, cost + 1, nk});
            }

        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<=K; i++){
            ans=Math.min(ans, visited[H -1][W-1][i]);
        }

        System.out.println( ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
