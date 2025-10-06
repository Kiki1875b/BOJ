import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    
    static int N, K;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]); K = Integer.parseInt(input[1]);
        
        
        int[][] items = new int[N][2];
        
        for(int i = 0 ; i< N; i++){
            String[] tmp = br.readLine().split(" ");
            
            items[i][0] = Integer.parseInt(tmp[0]);
            items[i][1] = Integer.parseInt(tmp[1]);
        }
        
        int[] dp = new int[K + 1]; // dp[i] = i 무개일때 최대 가치
        
        for(int i = 0; i<N; i++){
            int weight = items[i][0];
            int value = items[i][1];
            
            for(int j = K; j >= weight; j--){
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }
        
        System.out.println(dp[K]);
    }
}