
import java.util.*;
import java.io.*;
/**
 N x M 크기 직사각형 방
 청소기는 바라보는 방향이 있다

 현재 칸이 청소되지 않은 경우 현재 칸 청소
 현재 칸 주변 4칸 탐색
 - 청소되지 않은 칸이 있다면 : 90도 회전 -> 전진
 - 청소되지 않은 칸이 없다면 : 후진.
 - 후진할 수 없다면: 동작 멈춤

 총 청소하는 영역의 개수를 구하라
 */

class Pos {
    int x, y;
    int[] dir;

    public Pos(int x, int y, int[] dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y, dir[0], dir[1]);
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        Pos p = (Pos) o;
        return p.x == this.x && p.y == this.y && p.dir[0] == this.dir[0] && p.dir[1] == this.dir[1];
    }
}

public class Main {
    static int N, M;
    static int[][] m;

    static Pos robot;

    static int[] north = new int[]{-1,0};
    static int[] south = new int[]{1,0};
    static int[] west = new int[]{0,-1};
    static int[] east = new int[]{0,1};

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        int d = Integer.parseInt(input[2]);
        int[] sDir = new int[2];
        if(d == 0){
            sDir[0] = north[0];
            sDir[1] = north[1];
        }else if(d == 1){
            sDir[0] = east[0];
            sDir[1] = east[1];
        }else if(d == 2){
            sDir[0] = south[0];
            sDir[1] = south[1];
        } else {
            sDir[0] = west[0];
            sDir[1] = west[1];
        }


        robot = new Pos(
            Integer.parseInt(input[0]),
            Integer.parseInt(input[1]),
            sDir
        );


        m = new int[N][M];

        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j<input.length; j++){
                m[i][j] = Integer.parseInt(input[j]);
            }
        }

        bfs();
        System.out.println(ans);
    }

    public static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.add(robot);

        if(m[robot.x][robot.y] == 0){
            ans++;
            m[robot.x][robot.y] = 2;
        }

        while(!q.isEmpty()){
            Pos cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int[] dir = cur.dir;
            int[] oDir = new int[]{dir[0], dir[1]};
            boolean uncleanedExists = false;

            for(int i = 0; i<4; i++){
                int[] next = nextDir(dir);
                int nx = x + next[0];
                int ny = y + next[1];

                if(validPos(nx, ny) && m[nx][ny] != 2){
                    uncleanedExists = true;
                    q.add(new Pos(nx, ny, next));
                    ans++;
                    m[nx][ny] = 2;
                    break;
                }

                dir = next;
            }

            if(uncleanedExists){


            } else {
                // 후진 로직
                int nx = x + (oDir[0] * -1);
                int ny = y + (oDir[1] * -1);
                if(!validPos(nx, ny)) continue;

                q.add(new Pos(nx, ny, new int[]{oDir[0], oDir[1]}));
            }

        }

    }



    public static int[] nextDir(int[] curDir){
        if(curDir[0] == -1) return west;
        else if(curDir[0] == 1) return east;
        else if(curDir[1] == -1) return south;
        else return north;
    }

    public static boolean validPos(int x, int y){
        if(x >= N || y >= M || x < 0 || y < 0) return false;
        if(m[x][y] == 1) return false;
        return true;
    }
}