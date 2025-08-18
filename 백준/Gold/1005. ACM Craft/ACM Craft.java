
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


class Main {

    static int T, N, K;
    static int[] dist;
    static int[] costs;
    static List<List<Integer>> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            nodes = new ArrayList<>();
            for(int j = 0; j<=N; j++) nodes.add(new ArrayList<>());

            costs = new int[N + 1];
            dist  = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int  j = 1; j<=N; j++){
                costs[j] = Integer.parseInt(st.nextToken());
                dist[j] = costs[j];
            }

            Set<Integer> hasIncoming = new HashSet<>();
            for(int j = 0; j<K; j++){
                st = new StringTokenizer(br.readLine());
//                String[] ab = br.readLine().split(" ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                hasIncoming.add(to);
                nodes.get(from).add(to);
            }

            List<Integer> start = new ArrayList<>();
            for(int j = 1; j<= N; j++){
                if(!hasIncoming.contains(j)){
                    start.add(j);
                }
            }

            int target = Integer.parseInt(br.readLine());

            bfs(start);

            System.out.println(dist[target]);

            nodes.clear();
            hasIncoming.clear();
        }
    }

    static void bfs(List<Integer> start){
        Queue<Integer> q = new LinkedList<>();
        
        for(int s : start){
            q.add(s);
            dist[s] = costs[s];
        }

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: nodes.get(current)){
                if(dist[next] < dist[current] + costs[next]) {
                    dist[next] = Math.max(dist[current] + costs[next], dist[next]);
                    q.add(next);
                }
            }
        }
    }
}



