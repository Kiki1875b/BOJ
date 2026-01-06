import java.util.*;
import java.io.*;

class Main {
    static int[] disc;
    static int[] low;
    static boolean[] visited;
    static boolean[] inStack;
    
    static List<List<Integer>> graph;
    static Stack<Integer> st;
    
    static int time = 0;
    static List<List<Integer>> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        int V = Integer.parseInt(s[0]);
        int E = Integer.parseInt(s[1]);
        graph = new ArrayList<>();
        st = new Stack<>();
        
        for(int i = 0; i<=V; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i<E; i++){
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            graph.get(a).add(b);
        }
        
        disc = new int[V + 1];
        low = new int[V + 1];
        visited = new boolean[V + 1];
        inStack = new boolean[V + 1];
        
        for(int i = 1; i<=V; i++){
            if(!visited[i]) dfs(i);
        }    
        
        System.out.println(ans.size()); Collections.sort(ans, (a,b) -> a.get(0) - b.get(0));
        for(List<Integer> l : ans){
            for(int i : l){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    
    
    static void dfs(int node){
        
        visited[node] = true;
        disc[node] = low[node] = time++;
        inStack[node] = true;
        st.push(node);
        
        for(int next : graph.get(node)){
            if(!visited[next]){
                dfs(next);
                low[node] = Math.min(low[node], low[next]);
               
            } else if(inStack[next]){
                low[node] = Math.min(low[node], disc[next]);
            }
        }
        
        if(low[node] == disc[node]){
            List<Integer> ret = new ArrayList<>();
            while(true){
                int x = st.pop();
                inStack[x] = false;
                ret.add(x);
                if(x == node) break;
            }
            
            Collections.sort(ret);
            ret.add(-1);
            ans.add(ret);
        }
        
    }
}