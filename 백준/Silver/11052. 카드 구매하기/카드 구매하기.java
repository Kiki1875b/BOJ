import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N=Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] cards = new int[N];
        for(int i = 0; i<N; i++){
            cards[i] = Integer.parseInt(input[i]);
        }
        
        int[] dp = new int[N + 1]; // dp[i] = i개를 사기 위한 최대 값
        
        dp[0] = 0;
        
        for(int i = 1; i<=N; i++){
            dp[i] = cards[i - 1];
            for(int j = i - 1; j > 0; j--){
                dp[i] = Math.max(dp[i], dp[i - j] + cards[j - 1]);
            }
        }
        
        
        System.out.println(dp[N]);
        
    }
}