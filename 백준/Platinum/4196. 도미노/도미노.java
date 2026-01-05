import java.util.*;
import java.io.*;

class Main {
    
    static int[] disc;
    static int[] low;
    static boolean[] visited;
    static boolean[] inStack;
    static Stack<Integer> st;
    static List<List<Integer>> graph;
    static int[] sccId;
    static int time = 0;
    static int sccCnt = 0;
    static int[] indegree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        for(int T = 0; T < t; T++){
            String[] s = br.readLine().split(" ");
            int N = Integer.parseInt(s[0]);
            int M = Integer.parseInt(s[1]);
            
            disc = new int[N + 1];
            low = new int[N + 1];
            visited = new boolean[N + 1];
            inStack = new boolean[N + 1];
            st = new Stack<>();
            graph = new ArrayList<>();
            sccId = new int[N + 1];
            time = 0;
            sccCnt = 0;
            indegree = new int[N + 1];
            
            for(int i = 0; i<=N; i++) graph.add(new ArrayList<>());
            for(int i = 0; i<M; i++){
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                graph.get(a).add(b);
                
            }
            
            for(int i = 1; i<=N; i++){
                if(!visited[i]) dfs(i);
            }
            
            for(int i = 1; i<=N; i++){
                for(int j : graph.get(i)){
                    if(sccId[i] != sccId[j]){
                        indegree[sccId[j]]++;
                    }
                }
            }
            
            int ans = 0;
            for(int i = 0; i< sccCnt; i++){
                if(indegree[i] == 0){
                    ans++;
                }
            }
            
            System.out.println(ans);
            
        }
        
        
    }
    
    
    static void dfs(int node){
        visited[node] = true;
        inStack[node] = true;
        st.push(node);
        disc[node] = low[node] = time++;
        
        for(int next : graph.get(node)){
            if(!visited[next]){
                dfs(next);
                low[node] = Math.min(low[node], low[next]);
                
            }else if(inStack[next]){
                low[node] = Math.min(low[node], disc[next]);
            }
        }
        
        
        if(low[node] == disc[node]){
            while(true){
                int x = st.pop();
                inStack[x] = false;
                sccId[x] = sccCnt;
                if(x == node) break;
            }
            sccCnt++;
        }
    }
}