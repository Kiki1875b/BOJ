import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Main {
    static int N, M;
    static int[][] map;
    static Set<Point> cleaned = new HashSet<>();
    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    static int[] dy = {0, 1, 0, -1}; // 북, 동, 남, 서

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int x, y, dir;
        x = sc.nextInt();
        y = sc.nextInt();
        dir = sc.nextInt();

        map = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0 ;j <M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int res = simulate(x, y, dir);
        System.out.println(res);
    }


    static int simulate(int x, int y, int dir){
        int ret = 0;

        while(true){
            if(!cleaned.contains(new Point(x,y))){
                cleaned.add(new Point(x,y));
                ret++;
            }

            boolean moved = false;
            for(int i = 0; i <4 ; i++){
                dir = (dir+3) % 4;
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(map[nx][ny] == 0 && !cleaned.contains(new Point(nx, ny))){
                    x = nx;
                    y = ny;
                    moved = true;
                    break;
                }
            }

            if(!moved){
                int backDir = (dir+2)%4;
                int backX = x + dx[backDir];
                int backY = y + dy[backDir];

                if(map[backX][backY] == 1){
                    break;
                }
                x = backX;
                y = backY;
            }
        }

        return ret;
    }


}
