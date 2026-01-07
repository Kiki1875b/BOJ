
import java.util.*;
import java.io.*;

class Main {
    static class Edge{
        int a, b;
        public Edge (int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode(){
            return Objects.hash(a, b);
        }

        @Override
        public boolean equals(Object o){
            Edge e = (Edge) o;
            return e.a == this.a && e.b == this.b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String[] s = br.readLine().split(" ");

            int N = Integer.parseInt(s[0]);
            int M = Integer.parseInt(s[1]);
            if(N ==0 && M == 0) break;

            s = br.readLine().split(" ");
            int S = Integer.parseInt(s[0]);
            int D = Integer.parseInt(s[1]);

            List<List<int[]>> graph = new ArrayList<>();
            for(int i = 0; i<N; i++) graph.add(new ArrayList<>());
            for(int i = 0; i<M; i++){
                s = br.readLine().split(" ");

                int a = Integer.parseInt(s[0]);
                int b=  Integer.parseInt(s[1]);
                int cost = Integer.parseInt(s[2]);

                graph.get(a).add(new int[]{b, cost});
            }

            List<List<Integer>> parents = new ArrayList<>();
            for(int i =0; i<N; i++) parents.add(new ArrayList<>());
            int[] dist = new int[N];
            Arrays.fill(dist, Integer.MAX_VALUE);
            PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
            q.add(new int[]{S, 0});
            dist[S] = 0;

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int node = cur[0];
                int cost = cur[1];

                for(int[] next: graph.get(node)){
                    int nNode = next[0];
                    int nCost = cost + next[1];

                    if(dist[nNode] < nCost) continue;
                    else if(dist[nNode] == nCost){
                        parents.get(nNode).add(node);

                    }else{
                        dist[nNode] = nCost;
                        parents.get(nNode).clear();
                        parents.get(nNode).add(node);
                        q.add(new int[]{nNode, nCost});
                    }
                }
            }

            Set<Edge> min = new HashSet<>();
            Queue<Integer> toCheck = new LinkedList<>();
            boolean[] visited = new boolean[N];
            toCheck.add(D);
            visited[D] = true;

            while(!toCheck.isEmpty()){
                int cur = toCheck.poll();
                if(cur == S) continue;

                for(int prev : parents.get(cur)){

                    Edge e = new Edge(prev, cur);
                    min.add(e);
                    if(visited[prev]) continue;
                    visited[prev] = true;
                    
                    toCheck.add(prev);
                }
            }


            PriorityQueue<int[]> almost = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
            almost.add(new int[]{S, 0});
            int[] almostVisited = new int[N];
            Arrays.fill(almostVisited, Integer.MAX_VALUE);
            almostVisited[S] = 0;
            boolean found = false;

            while(!almost.isEmpty()){
                int[] cur = almost.poll();
                int node = cur[0];
                int cost = cur[1];

                if(node == D){
                    System.out.println(cost);
                    found = true;
                    break;
                }
                for(int[] next : graph.get(node)){
                    int nNode = next[0];
                    int nCost = next[1] + cost;

                    if(almostVisited[nNode] <= nCost) continue;
                    if(min.contains(new Edge(node, nNode))) continue;

                    almostVisited[nNode] = nCost;
                    almost.add(new int[]{nNode, nCost});
                }
            }

            if(!found){
                System.out.println(-1);
            }

        }
    }
}
