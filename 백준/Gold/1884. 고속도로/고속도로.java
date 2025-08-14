
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Road {
    int start, dest, length, cost;
    public Road(int start, int dest, int length, int cost){
        this.start = start;
        this.dest = dest;
        this.length = length;
        this.cost = cost;
    }
}


class Main {
    static int K, N, R;
    static List<List<Road>> roads;

    static int ans = Integer.MAX_VALUE;
    static List<List<Integer>> paths = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());
        roads = new ArrayList<>();

        for(int i = 0; i<=N; i++) {
            roads.add(new ArrayList<>());
        }

        for(int i = 0; i<R; i++){
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int dest = Integer.parseInt(input[1]);
            int length = Integer.parseInt(input[2]);
            int cost = Integer.parseInt(input[3]);

            roads.get(start).add(new Road(start, dest, length, cost));

        }

        bfs(1);

        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        System.out.println(ans);
    }

    static void bfs(int start){
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        q.add(new int[]{start, 0,0}); // node, time, cost

        int[][] dist = new int[N + 1][K + 1];

        for(int i = 0 ;i<dist.length; i++){
            for(int j = 0 ; j<dist[i].length; j++){
                dist[i][j] = Integer.MAX_VALUE  ;
            }
        }

        dist[1][0] = 0;

        while (!q.isEmpty()){
            int[] current = q.poll();
            int city = current[0];
            int time = current[1];
            int cost = current[2];

            for(Road r : roads.get(city)){
                int nextCity = r.dest;
                int nextTime = time + r.length;
                int nextCost = cost + r.cost;
                if(nextCost > K) continue;
                if(dist[nextCity][nextCost] <= nextTime) continue;
                dist[nextCity][nextCost] = nextTime;
                q.add(new int[]{nextCity, nextTime, nextCost});
            }
        }

        for(int i = 0; i<dist[N].length; i++){
            ans = Math.min(ans, dist[N][i]);
        }
    }
}
