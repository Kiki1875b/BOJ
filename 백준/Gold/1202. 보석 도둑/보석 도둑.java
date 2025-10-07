import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        
        int[][] gems = new int[N][2]; // gems[i] = {weight, price}
        
        for(int i = 0; i<N; i++){
            String[] tmp = br.readLine().split(" ");
            gems[i][0] = Integer.parseInt(tmp[0]);
            gems[i][1] = Integer.parseInt(tmp[1]);
        }
        
        int[] bags = new int[K];
        for(int i = 0; i<K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(gems, (a, b) -> a[0] - b[0]);
        Arrays.sort(bags);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        long ans = 0;
        int gemIdx = 0;
        for(int bag: bags){
            
            while(gemIdx < N &&  gems[gemIdx][0] <= bag){
                pq.add(gems[gemIdx][1]);
                gemIdx++;
            }
            
            
            if(!pq.isEmpty()){
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }
}