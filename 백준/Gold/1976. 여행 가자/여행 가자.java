import java.util.*;
import java.io.*;

class Main {
    static int[] parent;
    
    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) return;
        parent[rb] = ra;
    }
    
    static boolean check(int a, int b){
                int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) return true;
        return false;
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N];
        
        for(int i = 0; i<N; i++) parent[i] = i;
        
        for(int i = 0; i<N; i++){
            String[] s = br.readLine().split(" ");
            
            for(int j = 0; j<s.length; j++){
                if(i == j) continue;
                if(s[j].equals("1")){
                    union(i, j);
                }
            }
        }
        
        String[] s = br.readLine().split(" ");
        
        int[] input = new int[s.length];
        
        for(int i = 0; i<s.length; i++){
            input[i] = Integer.parseInt(s[i]);
        }
        
        for(int i = 0; i<input.length - 1; i++){
            if(!check(input[i] - 1, input[i + 1] - 1)){
               System.out.println("NO");
                return;
            }
        }
        
        System.out.println("YES");
        
    }
}