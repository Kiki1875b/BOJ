
import java.util.*;
import java.io.*;


class Pos {
    int x,y;
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        Pos p = (Pos) o;
        return p.x == this.x && p.y == this.y;
    }
}

public class Main {

    static int R, C, T;
    static int[][] m;
    static Pos top, bottom, topCur, bottomCur;

    static Set<Pos> micro = new HashSet<>();
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        m = new int[R][C];
        boolean foundTop = false;
        for(int i = 0; i<R; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j = 0; j < tmp.length; j++){
                int cur = Integer.parseInt(tmp[j]);
                m[i][j] = cur;
                if(m[i][j] != 0 || m[i][j] != -1){
                    micro.add(new Pos(i,j));
                }
                if(m[i][j] == -1 && !foundTop){
                    top = new Pos(i,j);
                    topCur = new Pos(i, j);
                    bottom = new Pos(i + 1,j);
                    bottomCur = new Pos(i + 1, j);
                    foundTop = true;
                }
            }
        }


        for(int i = 0; i<T; i++){
            diffuse();
            simulateLower();
            simulateUpper();
        }

        int ans = 0;
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m[0].length; j++){
                if(m[i][j] != -1){
                    ans += m[i][j];
                }
            }
        }

        System.out.println(ans);
    }

    static void diffuse(){
        Set<Pos> microCopy = new HashSet<>(micro);
        int[][] m2 = new int[R][C];
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m[0].length; j++){
                m2[i][j] = m[i][j];
            }
        }

        for(Pos p : microCopy){
            int val = m[p.x][p.y];
            int toSpread = val/5;
            if(toSpread == 0) continue;

            int cnt = 0;
            for(int i = 0; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if((nx == top.x && ny == top.y) || (nx == bottom.x && ny == bottom.y)) continue;

                cnt++;
                micro.add(new Pos(nx, ny));
                m2[nx][ny] += toSpread;
            }

            m2[p.x][p.y] -= (toSpread * cnt);
        }

        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m[0].length; j++){
                m[i][j] = m2[i][j];
            }
        }

    }
    static void simulateLower(){
        int carryOn = m[bottom.x][bottom.y + 1];
        m[bottom.x][bottom.y + 1] = 0;

        for(int i = 2; i<C; i++){
            int tmp = carryOn;
            carryOn = m[bottom.x][i];
            m[bottom.x][i] = tmp;
        }

        for(int i = bottom.x + 1; i<R; i++){
            int tmp = carryOn;
            carryOn = m[i][C - 1];
            m[i][C - 1] = tmp;
        }

        for(int i = C - 2; i>= 0; i--){
            int tmp = carryOn;
            carryOn = m[R - 1][i];
            m[R - 1][i] = tmp;
        }

        for(int i = R - 2; i > bottom.x; i--){
            int tmp = carryOn;
            carryOn = m[i][0];
            m[i][0] = tmp;
        }

    }

    static void simulateUpper(){
        int carryOn = m[top.x][top.y + 1];
        m[top.x][top.y + 1] = 0;
        for(int i = 2; i<C; i++){
            int tmp = carryOn;
            carryOn = m[top.x][i];
            m[top.x][i] = tmp;
        }

        for(int i = top.x - 1; i >= 0; i--){
            int tmp = carryOn;
            carryOn = m[i][C - 1];
            m[i][C - 1] = tmp;
        }

        for(int i = C - 2; i >= 0; i--){
            int tmp = carryOn;
            carryOn = m[0][i];
            m[0][i] = tmp;
        }

        for(int i = 1; i < top.x; i++){
            int tmp = carryOn;
            carryOn = m[i][0];
            m[i][0] = tmp;
        }
    }
}
