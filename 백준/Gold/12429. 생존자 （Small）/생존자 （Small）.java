import java.util.*;
import java.io.*;

class Main {
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            List<int[]> foods = new ArrayList<>();
            
            for(int j = 0; j<N; j++){
                String[] tmp = br.readLine().split(" ");
                int[] tmp2 = new int[]{
                    Integer.parseInt(tmp[0]),
                    Integer.parseInt(tmp[1])
                };
                
                foods.add(tmp2);
            }
            boolean[] visited = new boolean[N];
            
            backtrack(foods, visited, 0);

            System.out.println("Case #" + (i + 1) +": " + ans);
            
            ans = -1;
           
        }
         
    }
    
    static void backtrack(List<int[]> foods, boolean[] visited, int time){
        
        ans = Math.max(time, ans);
        
        
        for(int i = 0; i<foods.size(); i++){
            if(visited[i]) continue;
            
            if(foods.get(i)[0] < time) continue;
            
            visited[i] = true;
            backtrack(foods, visited, time + foods.get(i)[1]);
            visited[i] = false;
        }
       

    }
}