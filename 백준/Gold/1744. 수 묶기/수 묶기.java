import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        
        int N = Integer.parseInt(br.readLine());
        boolean zero = false;

        PriorityQueue<Integer> npq = new PriorityQueue<>();
        PriorityQueue<Integer> ppq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            
            if(num > 0) ppq.add(num);
            else if(num == 0) zero = true;
            else npq.add(num);
        }
        
        int sum = 0;
        
        while(npq.size() >= 2){
            int a = npq.poll();
            int b = npq.poll();
            
            sum += (a * b);
        }
        
        while(ppq.size() >= 2){
            int a = ppq.poll();
            int b = ppq.poll();
            
            sum += (a*b) > (a + b) ? (a * b) : (a + b);
        }
        
        if(!npq.isEmpty()){
            if(!zero){
                sum += npq.poll();
            } 
        }
        
        if(!ppq.isEmpty()) sum += ppq.poll();
        
        System.out.println(sum);
    }
}