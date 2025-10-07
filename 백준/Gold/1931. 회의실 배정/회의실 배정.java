import java.io.*; 
import java.util.*;

public class Main {
    
    public static void main(String[] args ) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for(int i = 0; i< n; i++){
            String[] tmp = br.readLine().split(" ");
            int[] arr = new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])};
            pq.add(arr);
        }
        
        
        int ans = 0;
        int prevEnd = 0;
        
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            if(cur[0] < prevEnd) continue;
            
            ans++;
            prevEnd = cur[1];
        }
        
        System.out.println(ans);
        
    }
}