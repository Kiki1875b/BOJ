import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        List<List<Integer>> l = new ArrayList<>();
        int[] indegree = new int[N + 1];
        
        for(int i =0 ; i<=N; i++) l.add(new ArrayList<>());
        for(int i = 0; i<M; i++){
            s = br.readLine().split(" ");
int k = Integer.parseInt(s[0]);
int[] tmp = new int[k];
for (int j = 0; j < k; j++) {
    tmp[j] = Integer.parseInt(s[j + 1]);
}
            for(int j = 0; j<tmp.length - 1; j++){
                int a = tmp[j];
                int b= tmp[j + 1];
                
                indegree[b]++;
                l.get(a).add(b);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0) q.add(i);    
        }
        
        List<Integer> ans = new ArrayList<>();
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            ans.add(cur);
            for(int next : l.get(cur)){
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        if(ans.size() != N){
            System.out.println(0);
            return;
        }
        
        for(int i : ans){
            System.out.println(i);
        }
    }
}