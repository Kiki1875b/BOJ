import java.util.*;

public class Main {

    static int N, M;
    static int ans = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visited;

    static boolean check(int x, int y){
        return x >= 0 && y >= 0 && x < N && y < M;
    }
    static void backtrack(int x, int y, int sum, int cnt){
        if(cnt == 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(check(nx, ny)){
                if(!visited[nx][ny]){
                    if(cnt == 2){
                        visited[nx][ny] = true;
                        backtrack(x, y, sum + map[nx][ny], cnt + 1);
                        visited[nx][ny] = false;
                    }

                    visited[nx][ny] = true;
                    backtrack(nx, ny, sum + map[nx][ny], cnt + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i< N; i++){
            for(int j =0 ; j< M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                visited[i][j]= true;
                backtrack(i,j,map[i][j],1);
                visited[i][j] = false;
            }
        }

        System.out.println(ans);
    }
}
