import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int N =Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        
        input = br.readLine().split(" ");
        
        Map<Integer, List<Integer>> m = new HashMap<>();
        int[] nums = new int[input.length];
        
        for(int i = 0; i<input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
            List<Integer> tmp = m.getOrDefault(nums[i], new ArrayList<>());
            tmp.add(i);
            m.put(nums[i], tmp);
        }
        
        Set<Integer> current = new HashSet<>();
        int ans = 0;
        
        for(int i = 0; i<nums.length; i++){
            int num = nums[i];
            List<Integer> tmp = m.get(num);
            
            if(!tmp.isEmpty()){
                tmp.remove(0);
            }
            
            if(current.contains(num) || current.size() < N){
                current.add(num);
            } else {
                  int maxIdx = 0;
                  int toRemove = -1;
                
                  for(int c : current){
                      List<Integer> l = m.get(c);
                      if(l.isEmpty()){
                          toRemove = c;
                          break;
                      }
                      else if(l.get(0) > maxIdx){
                          maxIdx = l.get(0);
                          toRemove = c;
                      }
                  }

                  ans++;
                  current.remove(toRemove);
                  current.add(num);
            }
        }
         System.out.println(ans);
       
    }
}