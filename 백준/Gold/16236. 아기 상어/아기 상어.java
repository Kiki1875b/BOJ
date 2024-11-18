import java.util.*;

/**
 * N x N 크기 공간
 * M 마리 물고기, 1마리 아기 상어
 * 1칸에는 최대 1마리의 물고기
 * 아기상어의 처음 크기 = 2
 * 자신보다 큰 고기는 지나갈 수 없다
 */

class Pair<T,G>{
    T first;
    G second;
    public Pair(T first, G second){
        this.first = first;
        this.second = second;
    }

}


public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N;
    static int sharkSize = 2;
    static Pair<Integer, Integer> sharkPos;
    static List<Pair<Pair<Integer,Integer>, Integer>> edibleFish = new ArrayList<>();

    static void reset(){
        edibleFish.clear();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                visited[i][j] = false;
            }
        }
    }

    static void bfs(){
        reset();
        Queue<Pair<Pair<Integer,Integer>, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(sharkPos, 0));
        visited[sharkPos.first][sharkPos.second] = true;

        while(!q.isEmpty()){
            Pair<Pair<Integer, Integer>, Integer> current = q.poll();
            int x = current.first.first;
            int y = current.first.second;
            int cost = current.second;

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nCost = cost + 1;

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] > sharkSize) continue;

                visited[nx][ny] = true;

                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    edibleFish.add(new Pair<Pair<Integer, Integer>, Integer>(new Pair<Integer, Integer>(nx, ny), nCost));
                }

                q.add(new Pair<Pair<Integer,Integer>, Integer>(new Pair<Integer,Integer>(nx,ny),nCost));
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int eat = 0;
        N = sc.nextInt();
        int totalDist = 0;
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 9){
                    sharkPos = new Pair<>(i,j);
                    map[i][j]=0;
                }
            }
        }

        while(true){
            bfs();
            if(edibleFish.isEmpty()) break;

            Collections.sort(edibleFish, new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
                @Override
                public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                    int comp2 = o1.second.compareTo(o2.second);
                    if(comp2 != 0) return comp2;

                    int comp3 = o1.first.first.compareTo(o2.first.first);
                    if(comp3 != 0) return comp3;
                    return o1.first.second.compareTo(o2.first.second);
                }
            });

            Pair<Pair<Integer,Integer>, Integer> p = edibleFish.get(0);
            eat++;

            if(eat == sharkSize){
                eat = 0;
                sharkSize++;
            }
            map[p.first.first][p.first.second] = 0;
            sharkPos = new Pair<>(p.first.first, p.first.second);
            totalDist += p.second;

        }

        System.out.println(totalDist);
    }
}
