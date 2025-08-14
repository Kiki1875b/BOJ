
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int R,C;
    static int[][] cnt;
    static boolean[][] possible;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] RC = br.readLine().split(" ");

        R = Integer.parseInt(RC[0]);
        C = Integer.parseInt(RC[1]);

        cnt = new int[R][C];
        possible = new boolean[R ][C ];
        map = new char[R ][C ];

        for(int i = 0; i<cnt.length; i++){
          Arrays.fill(cnt[i], 0);
        }

        for(int i = 0; i< R; i++){
            char[] input = br.readLine().toCharArray();
            for(int j = 0; j<input.length; j++){
                map[i][j] = input[j];
            }
        }


        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[i].length; j++){
                if(map[i][j] == 'X') {
                    possible[i][j] = true;
                }
            }
        }

        bfs();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = -1;
        int maxY = -1;

        for(int i = 0; i<possible.length; i++){
            for(int j = 0; j<possible[i].length; j++){
                if(!possible[i][j]){
                    map[i][j] = '.';
                }else{
                    minX = Math.min(i,minX);
                    minY = Math.min(j,minY);
                    maxX = Math.max(i,maxX);
                    maxY = Math.max(j,maxY);
                }
            }
        }

        char[][] ans = new char[maxX - minX + 1][maxY - minY + 1];

        for(int i = 0; i<ans.length; i++){
            for(int j = 0; j<ans[i].length; j++){
                ans[i][j] = map[i + minX][j + minY];
            }
        }

        for(char[] a: ans){
            for(char c: a){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        boolean[][] visited = new boolean[R + 1][C + 1];
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    cnt[x][y]++;
                    if(cnt[x][y] >= 3) possible[x][y] = false;
                    continue;
                }
                if(map[nx][ny] == '.'){
                    cnt[x][y]++;
                    if(cnt[x][y] >= 3) possible[x][y] = false;
                }
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});

            }
        }
    }



}
