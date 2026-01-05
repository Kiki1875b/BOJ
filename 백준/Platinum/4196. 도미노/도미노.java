import java.util.*;
import java.io.*;

class Main {
    static int[] disc;
    static int[] low;
    static boolean[] visited;
    static boolean[] inStack;
    static int[] sccId;
    static int sccCnt =0;
    static int[] indegree;
    static Stack<Integer> s;
    static List<List<Integer>> graph;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t< T; t++){
            String[] ss = br.readLine().split(" ");
            int N = Integer.parseInt(ss[0]);
            int M = Integer.parseInt(ss[1]);
            
            disc = new int[N + 1];
            low = new int[N + 1];
            indegree = new int[N + 1];
            visited = new boolean[N + 1];
            inStack = new boolean[N + 1];
            sccId = new int[N + 1];
            graph = new ArrayList<>();
            s = new Stack<>();
            sccCnt =0;
            time = 0;
            
            for(int i = 0; i<=N; i++) graph.add(new ArrayList<>());
            
            for(int i = 0 ;i<M; i++){
                ss = br.readLine().split(" ");
                int a = Integer.parseInt(ss[0]);
                int b = Integer.parseInt(ss[1]);
                
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
            
            int answer = 0;
            for(int i = 0; i < sccCnt; i++){
                if(indegree[i] == 0) answer++;
            }

            System.out.println(answer);
        }
    }
    
    
    static void dfs(int node){
        
        visited[node] = true;
        disc[node] = low[node] = time++;
        s.push(node);
        inStack[node] = true;
        
        for(int next :graph.get(node)){
            if(!visited[next]){
                dfs(next);
                low[node] = Math.min(low[node], low[next]);
            }else if(inStack[next]){
                low[node] = Math.min(low[node], disc[next]);
            }
        }
        
        if(low[node] == disc[node]){
            while(true) {
                int x = s.pop();
                inStack[x] = false;
                sccId[x] = sccCnt;
                if(x == node) break;
            }
            sccCnt++;
        }
        
        
        
    }
}