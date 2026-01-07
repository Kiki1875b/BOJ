
import java.util.*;
import java.io.*;

class Main {
    static int R, C;
    static String[] graph;
    static int[][] fire;

    static int  sx, sy;
    static List<int[]> fireStarts = new ArrayList<>();
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);

        graph = new String[R];
        fire = new int[R][C];
        for(int i =0 ;i <R; i++) Arrays.fill(fire[i], Integer.MAX_VALUE);

        for(int i =0 ; i<R; i++){
            graph[i] = br.readLine();
            for(int j = 0; j<graph[i].length(); j++){
                if(graph[i].charAt(j) == 'J'){
                    sx =i;
                    sy =j;
                }else if(graph[i].charAt(j) == 'F'){
                    fireStarts.add(new int[]{i, j});
                }
            }
        }

        simulateFire();
        int ans = simulate();
        System.out.println(ans == -1 ? "IMPOSSIBLE" : ans + 1);

    }

    static void simulateFire(){
        Queue<int[]> q = new LinkedList<>();

        for(int[] f : fireStarts){
            fire[f[0]][f[1]] = 0;
            q.add(new int[]{f[0], f[1], 0});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                int nCost = cost + 1;

                if(graph[nx].charAt(ny) == 'J' || graph[nx].charAt(ny) == '#') continue;
                if(fire[nx][ny] <= nCost) continue;
                fire[nx][ny] = nCost;
                q.add(new int[]{nx, ny, nCost});
            }
        }


    }

    static int simulate(){
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q= new LinkedList<>();
        visited[sx][sy] = true;

        q.add(new int[]{sx, sy, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x=  cur[0];
            int y = cur[1];
            int cost = cur[2];

            if(x == 0 || x == R - 1 || y == 0 || y == C- 1){
                return cost;
            }

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visited[nx][ny]) continue;
                if(graph[nx].charAt(ny) != '.') continue;
                if(fire[nx][ny] <= cost + 1) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, cost + 1});
            }
        }

        return -1;
    }
}
