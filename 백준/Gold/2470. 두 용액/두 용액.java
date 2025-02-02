import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * 산성 용액 값 : 1 ~ 1,000,000,000
 * 같은 양의 두 용액을 혼합한 용액의 값 : 사용된 각 용액의 특성값의 합
 * 특성값이 0에 가까워 지는것이 목표
 *
 * 예 ) [-2, 4, -99, -1, 98] 인 경우, 특성값 -99 + 98 = -1 로 가장 0에 가깝다
 * [-99, -2, -1, 4, 98]
 */
class Main {

  static int N;
  static int[] arr;
  public static void main(String[] args) throws IOException {

    int ans = Integer.MAX_VALUE;

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(bf.readLine());

    arr = new int[N];

    String[] tmp = bf.readLine().split(" ");
    for(int i = 0; i<tmp.length; i++){
      arr[i] = Integer.parseInt(tmp[i]);
    }

    Arrays.sort(arr);

    int liq1 = 0 ;
    int liq2 = 0;
    int start = 0;
    int end = arr.length - 1;

    while(start < end){
      int sum = arr[start] + arr[end];

      if(Math.abs(sum) < ans){
        liq1 = arr[start];
        liq2 = arr[end];
        ans = Math.abs(sum);
      }

      if(sum < 0){
        start++;
      }else if(sum > 0){
        end--;
      }else{
        break;
      }
    }

    System.out.println(liq1 + " " + liq2);
  }
}
