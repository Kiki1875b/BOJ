
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N, M;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        List<int[]> pos = new ArrayList<>();
        for(int i = 0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j<map[i].length; j++){
                if(map[i][j] == 'o'){
                    pos.add(new int[]{i,j});
                }
            }
        }

        bfs(pos.get(0), pos.get(1));

        System.out.println(ans);

    }

    static int ans = Integer.MAX_VALUE;

    static void bfs(int[] pos1, int[] pos2){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{pos1[0], pos1[1], pos2[0], pos2[1], 0}); // x1, y1, x2, y2, cost
        Set<String> visited = new HashSet<>();
        String s = String.valueOf(pos1[0]) + String.valueOf(pos1[1]) + String.valueOf(pos2[0]) + String.valueOf(pos2[1]);
        visited.add(s);

        while(!q.isEmpty()){
            int[] current = q.poll();
            int x1 = current[0], y1 = current[1], x2 = current[2], y2 = current[3], cost = current[4];

            boolean dropped1 = false, dropped2 = false;
            if(cost > 10) {
                ans = -1;
                return;
            }
            if(x1 < 0 || y1 < 0 || x1 >= N || y1 >= M){
                dropped1 = true;
            }

            if(x2 < 0 || y2 < 0 || x2 >= N || y2 >= M){
                dropped2 = true;
            }

            if(dropped1 && dropped2) continue;
            else if(dropped1 || dropped2) {
                ans = cost;
                return;
            }

            for(int i = 0; i<4; i++){
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];

                if(!possible(nx1, ny1)){
                    nx1 = x1;
                    ny1 = y1;
                }

                if(!possible(nx2, ny2)){
                    nx2 = x2;
                    ny2 = y2;
                }

                String numString = String.valueOf(nx1) + String.valueOf(ny1) + String.valueOf(nx2) + String.valueOf(ny2);

                if(visited.contains(numString)) continue;
                visited.add(numString);
                q.add(new int[]{nx1, ny1, nx2, ny2, cost + 1});
            }
        }

        ans = -1;
    }


    static boolean possible(int x, int y){
//        try {
            if (x < 0 || y < 0 || x >= N || y >= M)
                return true;
            if (map[x][y] == '#')
                return false;
            return true;
//        }catch (Exception e){
//            System.out.println(x + " " + y);
//        }
//        return true;
    }
}
