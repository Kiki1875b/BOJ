import java.util.*;
import java.io.*;

class Main {
    
    static int N;
    static int[] ops;
    static int[] nums;
    
    static List<String> l = new ArrayList<>();
    static int maxAns = Integer.MIN_VALUE;
    static int minAns = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        String[] input = br.readLine().split(" ");
        
        String[] input2 = br.readLine().split(" ");
        
        ops = new int[4];
        nums = new int[N];
        
        for(int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(input[i]);
        }
        
        for(int i = 0; i < input2.length; i++){
            ops[i] = Integer.parseInt(input2[i]);
        }
        

        
        dfs(nums[0], 1);
        
        System.out.println(maxAns);
        System.out.println(minAns);
    }
    
    static void dfs(int current, int cnt){
        if(cnt == N){
            maxAns = Math.max(maxAns, current);
            minAns = Math.min(minAns, current);
            return;
        }
        
        
        for(int i = 0; i < 4; i++){
            if(ops[i] == 0) continue;
            ops[i]--;
            int nxt = current;
            if(i == 0){
                nxt += nums[cnt];
            }else if(i == 1){
                nxt -= nums[cnt];
            }else if(i == 2){
                nxt *= nums[cnt];
            }else{
        if (current < 0) {
        nxt = - (Math.abs(current) / nums[cnt]);
    } else {
        nxt = current / nums[cnt];
    }
            }
            
            dfs(nxt, cnt+1);
            
            ops[i]++;
            
        }
    }
}