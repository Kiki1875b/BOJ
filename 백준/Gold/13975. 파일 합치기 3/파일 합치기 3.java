import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T= Integer.parseInt(br.readLine());
        
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            String[] input = br.readLine().split(" ");
            
            for(int j = 0; j< N; j++){
                arr[j] = Integer.parseInt(input[j]);
            }
            
            Arrays.sort(arr);
            
            
            PriorityQueue<Long> pq = new PriorityQueue<>(); 
            
            long sum = 0;
           
            for(int num : arr) pq.add((long)num);
            
            while(pq.size() >= 2){
                long a = pq.poll();
                long b = pq.poll();
                
                sum += (a + b);
                
                pq.add(a + b);
            }
            
            
            System.out.println(sum);
        }
    }
}