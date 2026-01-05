
import java.util.*;
import java.io.*;

class Main {
    static int[] disc;
    static int[] low;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();

    static int time = 0;
    static Deque<Integer> stack = new ArrayDeque<>();
    static boolean[] inStack;
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int V = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        disc = new int[V + 1];
        low = new int[V + 1];
        visited = new boolean[V + 1];
        inStack = new boolean[V + 1];

        for(int i = 0; i<=V; i++) graph.add(new ArrayList<>());

        for(int i = 0; i<K; i++){
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);

            graph.get(a).add(b);
        }

        for(int i = 1; i<=V; i++) {
            if(!visited[i]) dfs(i);
        }

        Arrays.fill(visited, false);


        Collections.sort(ans, (a,b) -> a.get(0) - b.get(0));
        System.out.println(ans.size());
        for(List<Integer> a : ans){
            for(int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }

    }


    static void dfs(int node){
        visited[node] = true;
        disc[node] = low[node] = time++;
        stack.push(node);
        inStack[node] = true;

        for(int next : graph.get(node)){
            if(!visited[next]){
                dfs(next);
                low[node] = Math.min(low[node], low[next]);
            }else if(inStack[next]){
                low[node] = Math.min(low[node], disc[next]);
            }

        }

        if(low[node] == disc[node]){
            List<Integer> r = new ArrayList<>();
            while(true){
                int n = stack.pop();
                r.add(n);
                inStack[n] = false;
                if(n == node) break;
            }

            Collections.sort(r);
            r.add(-1);
            ans.add(r);

        }

    }
}
