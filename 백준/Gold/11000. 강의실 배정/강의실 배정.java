import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        
        int N = Integer.parseInt(br.readLine());
        List<int[]> events = new ArrayList<>();
        
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            
            events.add(
                new int[]{Integer.parseInt(input[0]), 1}
            );
            
            events.add(
                new int[]{Integer.parseInt(input[1]), -1}
            );
        }
        
        Collections.sort(events, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    
        int ans = 0; int cur =0;
        for(int[] n : events){
            cur += n[1];
            ans = Math.max(cur, ans);
        }
        
        System.out.println(ans);
    }
}