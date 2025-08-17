
import java.math.BigInteger;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Edge implements Comparable<Edge>{


    int from, to, cost;

    public Edge(int from, int to, int cost) {

        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
class Main {

    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for(int i = 0; i<=N; i++) parent[i] = i;

        List<Edge> edges = new ArrayList<>();
        for(int i = 1; i<=N; i++){
            int input = Integer.parseInt(br.readLine());
            edges.add(new Edge(0, i, input));
        }

        for(int i = 1; i<=N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(input[j - 1]);
                if (i < j) {
                    edges.add(new Edge(i, j, num));
                }
            }
        }

        Collections.sort(edges);
        int ans = 0; int cnt = 0;
        for(Edge e : edges){
            if(union(e.from, e.to)){
                ans += e.cost;
                cnt++;
                if(cnt == N) break;
            }
        }

        System.out.println(ans);
    }

    static int find(int n){
        if(n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }

    static boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return false;
        parent[parentB] = parentA;
        return true;
    }
}



