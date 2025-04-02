
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Main {

  static int N, M, limit;
  static int[] request;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int ans = 0;
    N = Integer.parseInt(bf.readLine());
    String[] input = bf.readLine().split(" ");
    limit = Integer.parseInt(bf.readLine());

    request = new int[N + 1];
    int sum = 0;
    int max = 0;

    for (int i = 0; i < input.length; i++) {
      request[i] = Integer.parseInt(input[i]);
      sum += request[i];
      max = Math.max(max, request[i]);
    }

    if(sum <= limit) {
      System.out.println(max);
      return;
    }

    int left = 0;
    int right = max;
    while(left <= right){
      int mid = (left + right) / 2;
      if(isPossible(mid)){
        left = mid + 1;
        ans = Math.max(ans, mid);
      }else{
        right = mid - 1;
      }
    }

    System.out.println(ans);
  }

  public static boolean isPossible(int mid){
    int currentTotal = limit;
    for(int i = 0; i<request.length; i++){
      if(request[i] < mid){
        currentTotal -= request[i];
      }else{
        currentTotal -= mid;
      }

      if(currentTotal < 0) return false;
    }
    return true;
  }

}

