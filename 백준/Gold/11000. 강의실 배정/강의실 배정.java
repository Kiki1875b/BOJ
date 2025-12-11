import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[][] arr = new int[N][2];
        
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            
            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
        }
        
        Arrays.sort(arr, (a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        
        int ans = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for(int i = 0; i<N; i++){
            int[] cur = arr[i];
            int start = cur[0]; int end = cur[1];
            
            if(!q.isEmpty() && q.peek() <= start){
                q.poll();
            }
            
            q.add(end);
            ans = Math.max(ans, q.size());
        }
        
        System.out.println(ans);
    }
}