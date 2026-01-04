import java.util.*;
import java.io.*;

class Main {
    static int[] parent;
    
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static boolean union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) return false;
        parent[rb] = ra;
        
        return true;
        
    }
    
    static boolean check(int a , int b){
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) return true;
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        
        parent = new int[n + 1];
        
        for(int i =0 ; i<=n; i++) parent[i] = i;
        
        for(int i = 0; i<m; i++){
            s = br.readLine().split(" ");
            int query = Integer.parseInt(s[0]);
            int a = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            
            if(query == 0){
                union(a, b);
            }else{
                if(check(a, b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
}