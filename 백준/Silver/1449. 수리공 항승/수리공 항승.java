import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        double L =(double) Integer.parseInt(input[1]);
        
        List<Double> leaks = new ArrayList<>();
        
        String[] input2 = br.readLine().split(" ");
        
        for(int i = 0; i<N; i++){
            leaks.add((double) Integer.parseInt(input2[i]));
            
        }
        
        Collections.sort(leaks);
        
        double cs = leaks.get(0) - 0.5; double ce = cs + L;
        int cnt = 1;
        
        for(double leak : leaks){
            double curStart = leak - 0.5;
            double curEnd = leak + 0.5;
            
            if(cs <= curStart && ce >= curEnd) continue;
            else if(cs <= curStart && ce < curEnd){
                cs = leak;
                ce = leak + L;
                cnt++;
            }else{
                cs = curStart;
                ce = cs + L;
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}