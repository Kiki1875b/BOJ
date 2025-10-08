import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            
            String[] input = br.readLine().split(" ");
            int[] prices = new int[N];
            
            for(int i = 0; i<N; i++) prices[i] = Integer.parseInt(input[i]);
            
            int maxPrice = 0;
            long profit = 0;
            
            for(int i = N - 1; i >= 0; i--){
                if(maxPrice < prices[i]){
                    maxPrice = prices[i];
                } else {
                    profit += (maxPrice - prices[i]);
                }
            }
            
            System.out.println(profit);
        }
    }
}