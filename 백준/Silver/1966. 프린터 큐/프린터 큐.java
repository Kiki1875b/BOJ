import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t= 0; t<T; t++){
            Queue<int[]> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b- a));
            String[] s = br.readLine().split(" ");
            
            int N = Integer.parseInt(s[0]);
            int target = Integer.parseInt(s[1]);
            
            
            s = br.readLine().split(" ");

            for(int j =0; j<s.length; j++){
                q.add(new int[]{j, Integer.parseInt(s[j])});
                pq.add(Integer.parseInt(s[j]));
            }
            
            
            int cnt = 1;
            
            while(!pq.isEmpty()){
                int[] cur = q.poll();
                if(cur[1] != pq.peek()){
                    q.add(cur);
                }else if (cur[1] == pq.peek()){
                    pq.poll();
                    
                    if(cur[0] == target){
                        System.out.println(cnt);
                        break;
                    }
                    
                    cnt++;
                }
                
                
            }
            
        }
        
        
    }
}