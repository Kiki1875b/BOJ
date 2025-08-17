
import java.math.BigInteger;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Edge implements Comparable<Edge>{

    int a, b, cost;

    public Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
class Main {

    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        parent = new int[N + 1];
        for(int i = 0; i<=N; i++) parent[i] = i;
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i<M; i++){
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            int cost = Integer.parseInt(tmp[2]);

            edges.add(new Edge(a, b, cost));
        }

        Collections.sort(edges);

        int ans = 0; int cnt = 0;
        int maxEdge = 0;
        for(Edge e : edges){
            if(union(e.a, e.b)){
                ans += e.cost;
                maxEdge = Math.max(maxEdge, e.cost);
                cnt++;
                if(cnt == N - 1) break;
            }
        }
        System.out.println(ans - maxEdge);
    }

    static int find(int a)  {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union (int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;
        parent[pb] = pa;
        return true;
    }


}



