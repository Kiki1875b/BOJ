import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 비의 양에 따라 일정 높이 이하의 모든 지점은 물에 잠긴다
 * N x N 크기 지역 높이 정보가 주어진다
 * 안전 구역의 수
 *
 */

class Pair{
    int first;
    int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

public class Main {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] map;
    static boolean[][] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new int[N ][N ];
        visited = new boolean[N ][N ];


        int minHeight = Integer.MAX_VALUE;
        int maxHeight = 0;
        for(int i = 0; i<N; i++){
            String[] tmp = bf.readLine().split(" ");
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
                minHeight = Math.min(map[i][j], minHeight);
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }

        for(int i = -1; i <= maxHeight; i++){

            int[][] tmpMap = new int[map.length][];
            for (int j = 0; j < map.length; j++) {
                tmpMap[j] = Arrays.copyOf(map[j], map[j].length);
            }

            boolean[][] tmpVisited = new boolean[visited.length][];
            for (int j = 0; j < visited.length; j++) {
                tmpVisited[j] = Arrays.copyOf(visited[j], visited[j].length);
            }


            for(int j = 0; j<N; j++){
                for(int k = 0; k< N; k++){
                    if(tmpMap[j][k] <= i) tmpVisited[j][k] = true;
                }
            }


            int number = numberOfSafeArea(tmpMap, tmpVisited);

            ans = Math.max(number, ans);
        }

        System.out.println(ans);
    }

    static int numberOfSafeArea(int[][] curMap, boolean[][] curVisited){

        int ret = 0;

        for(int i = 0; i<curMap.length; i++){
            for(int j = 0; j<curMap[0].length; j++){
                if(!curVisited[i][j]){
                    bfs(curMap, curVisited, i, j);
                    ret++;
                }
            }
        }
        return ret;
    }

    static void bfs(int[][] curMap, boolean[][] curVisited, int ix, int iy){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(ix,iy));
        curVisited[ix][iy] = true;

        while(!q.isEmpty()){
            Pair current = q.poll();
            int x = current.first;
            int y = current.second;

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(curVisited[nx][ny]) continue;

                curVisited[nx][ny] = true;
                q.add(new Pair(nx,ny));
            }
        }
    }

}
