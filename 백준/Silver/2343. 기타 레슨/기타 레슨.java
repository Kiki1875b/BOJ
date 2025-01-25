import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

  static int N, M;
  static List<Integer> arr = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] NM = bf.readLine().split(" ");
    
    N = Integer.parseInt(NM[0]);
    M = Integer.parseInt(NM[1]);
    
    String[] input = bf.readLine().split(" ");
    for(int i = 0; i< input.length; i++){
      arr.add(Integer.parseInt(input[i]));
    }
    
    int left = Collections.max(arr);
    int right = arr.stream().mapToInt(Integer::intValue).sum();
    int ans = right;
    
    while(left <= right){
      int mid = (left + right) / 2;
      if(isPossible(mid)){
        ans = mid ;
        right = mid - 1;
      }else {
        left = mid + 1;
      }
    }
    System.out.println(ans);
    
  }
  
  static boolean isPossible(int size){
    int cnt = 1;
    int sum = 0;
    
    for(int i = 0; i<arr.size(); i++){
      if(sum + arr.get(i) > size){
        sum = arr.get(i);
        cnt++;
        if(cnt > M) return false;
      }else{
        sum+=arr.get(i);
      }
    }
    return true;
  }

}