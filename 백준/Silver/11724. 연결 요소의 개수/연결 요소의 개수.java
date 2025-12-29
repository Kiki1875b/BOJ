import java.util.*;
import java.io.*;

class Main {
    static int ans = 0;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        graph = new ArrayList<>();
        for(int i = 0; i<=N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i<M; i++){
            s = br.readLine().split(" ");
            
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        boolean[] v = new boolean[N + 1];
        
        for(int i = 1; i<=N; i++){
            if(v[i]) continue;
            dfs(i, v, 1);
            ans++;
        }
        
        System.out.println(ans);
    }
    
    static void dfs(int cur, boolean[] visited, int cnt){
        if(visited[cur]) return;
        visited[cur] = true;
        
        for(int i = 0; i<graph.get(cur).size(); i++){
            int next = graph.get(cur).get(i);
            if(!visited[next]){
                dfs(next, visited, cnt + 1);
            }
        }
        
    }
}