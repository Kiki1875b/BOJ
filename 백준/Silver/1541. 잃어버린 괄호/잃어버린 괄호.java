import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] splitByMinus = br.readLine().split("-");
        
        int[] nums = new int[splitByMinus.length];
        
        for(int i = 0; i<splitByMinus.length; i++){
            int current = processString(splitByMinus[i]);
            nums[i] = current;
        }
        
        int ans = nums[0];
        for(int i = 1; i<nums.length; i++){
            ans -= nums[i];
        }
        
        System.out.println(ans);
    }
    
    public static int processString(String current){
        String[] splitByPlus = current.split("\\+");
        int[] nums = new int[splitByPlus.length];
        int ret = 0;
        for(int i = 0;  i<nums.length; i++) ret += Integer.parseInt(splitByPlus[i]);
       
        return ret;
    }
    
    public static String remove(String current){
        return "";
    }
}