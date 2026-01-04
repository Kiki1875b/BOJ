import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        int[] indegree = new int[N + 1];
        List<List<Integer>> l = new ArrayList<>();
        
        for(int i =0 ; i<=N; i++) l.add(new ArrayList<>());
        for(int i = 0; i<M; i++){
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            l.get(a).add(b);
            indegree[b]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0) q.add(i);
        }
        
        
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);
            for(int next: l.get(cur)){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                }
            }
            
        }
        
        
        for(int a : ans){
            System.out.print(a + " ");
        }
        
    }
}