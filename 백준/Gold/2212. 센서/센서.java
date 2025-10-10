import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        
        String[] input = br.readLine().split(" ");
        int[] sensors = new int[N];
        
        
        for(int i = 0; i<input.length; i++) sensors[i] = Integer.parseInt(input[i]);
        
        if(N <= K){
            System.out.println(0);
            return;
        }
        
        Arrays.sort(sensors);
        
        int[] diffs= new int[N - 1];
        
        for(int i = 0; i<N-1; i++){
            diffs[i] = sensors[i + 1] - sensors[i];
        }
        
        Arrays.sort(diffs);
        
        int total = sensors[N - 1] - sensors[0];
        
        for(int i = N - 2; i >= N - K; i--){
            total -= diffs[i];
        }
        
        System.out.println(total);
    
    }
}