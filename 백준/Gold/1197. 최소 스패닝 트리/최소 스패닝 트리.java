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

    static int V, E;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        parent = new int[V + 1];

        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i<E; i++){
            String[] input2 = br.readLine().split(" ");

            int from = Integer.parseInt(input2[0]);
            int to = Integer.parseInt(input2[1]);
            int cost = Integer.parseInt(input2[2]);

            edges.add(new Edge(from, to, cost));
        }

        for(int i = 0; i<=V; i++){
            parent[i] = i;
        }


        Collections.sort(edges);

        int ans = 0;
        int cnt = 0;
        for(Edge edge: edges){
            if(union(edge.from, edge.to)){
                cnt++;
                ans+=edge.cost;
                if(cnt == V-1) break;;
            }
        }

        System.out.println(ans);
    }

    static int find(int x)  {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y){
        int xParent = find(x);
        int yParent = find(y);

        if(xParent == yParent) return false;
        parent[xParent] = yParent;

        return true;
    }
}



