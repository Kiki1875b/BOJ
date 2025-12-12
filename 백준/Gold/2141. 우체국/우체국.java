import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        long[][] cities = new long[N][2];
        long total = 0;
        for(int i =0 ; i<N; i++){
            String[] input = br.readLine().split(" ");
            cities[i][0] = Long.parseLong(input[0]);
            cities[i][1] = Long.parseLong(input[1]);
            total += cities[i][1];
            
        }
        
        Arrays.sort(cities, (a,b) -> Long.compare(a[0], b[0]));
        
        long target = (total + 1)/ 2;
        long current = 0;
        long ans = -1;
        for(long[] city : cities){
            current += city[1];
            if(current >= target){
                ans = city[0];
                break;
            }
        }
        
        System.out.println(ans);
    }
}