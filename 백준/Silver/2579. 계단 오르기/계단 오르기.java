import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] points = new int[n];
        
        for(int i = 0 ;i<n; i++){
            points[i] = Integer.parseInt(br.readLine());
        }
        
        if (n == 1) {
            System.out.println(points[0]);
            return;
        }

        if (n == 2) {
            System.out.println(points[0] + points[1]);
            return;
        }
        
        int[] dp = new int[n]; // dp[i] = i번 계단까지 얻을 수 있는 최대 점수
        
        dp[0] = points[0];
        dp[1] = dp[0] + points[1];
        dp[2] = Math.max(points[0] + points[2], points[1] + points[2]);

        for(int i = 3; i < n; i++){
            dp[i] = Math.max(dp[i - 2] + points[i], dp[i - 3] + points[i - 1] + points[i]);
        }
        System.out.println(dp[n-1]);
    }
}