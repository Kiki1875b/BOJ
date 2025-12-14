import java.util.*;
import java.io.*;

class Main{
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        
        long[] arr = new long[6];
        
        for(int i = 0; i<6; i++) arr[i] = Long.parseLong(input[i]);
        
        long threeMin = Long.MAX_VALUE;
        long twoMin = Long.MAX_VALUE;
        long oneMin = Long.MAX_VALUE;
        
        // (ABC), (ABD), (ADE), (ACE)
        // (FBC), (FBD), (FDE), (FCE)
        threeMin = Math.min(arr[0] + arr[1] + arr[2], threeMin);
        threeMin = Math.min(arr[0] + arr[1] + arr[3], threeMin);
        threeMin = Math.min(arr[0] + arr[2] + arr[4], threeMin);
        threeMin = Math.min(arr[0] + arr[3] + arr[4], threeMin);
        
        threeMin = Math.min(arr[5] + arr[1] + arr[2], threeMin);
        threeMin = Math.min(arr[5] + arr[1] + arr[3], threeMin);
        threeMin = Math.min(arr[5] + arr[2] + arr[4], threeMin);
        threeMin = Math.min(arr[5] + arr[3] + arr[4], threeMin);
        
        // (AF), (BE), (CD)
        for(int i = 0; i<6; i++){
            oneMin = Math.min(oneMin, arr[i]);
            
            for(int j = i + 1; j < 6; j++){
                if((i == 0 && j == 5) || (i == 1 && j == 4) || (i == 2 && j==3)) continue;
                twoMin=Math.min(arr[i] + arr[j], twoMin);
            }
        }
        
        if(N == 1) {
            long l = Long.MAX_VALUE;
            for(int i = 0; i<6; i++){
                int cur = 0;
                for(int j = 0; j<6; j++){
                    if(j== i) continue;
                    cur += arr[j];
                } 
                l = Math.min(l, cur);
            }
            
            System.out.println(l);
            return;
        }
        
        long ans = 0;
        ans += threeMin * 4;
        ans += twoMin * ((N - 2) * 4) + twoMin * (N - 1) * 4;
        ans += (oneMin * (N - 1) * (N - 2)) * 4 + (oneMin * (N-2) * (N-2));
        
        System.out.println(ans);
    }
}