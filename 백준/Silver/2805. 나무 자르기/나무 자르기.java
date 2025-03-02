import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 나무 M 미터가 필요하다
 * 절단기에 높이 H 를 지정 : 톱날이 땅으로부터 H 미터 위로
 * 그 다음 한 줄에 연속해있는 나무를 모두 절단
 *
 * 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면
 *
 *  나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것
 *  상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것
 *
 *  M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
 *
 *  sweeping 으로 풀수 있을듯? 효율 x
 */

class Main {

  static int N, M;
  static int[] trees;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    String[] input = bf.readLine().split(" ");
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);

    long ans = 0;
    long left = 0;
    long right = 1000000000;

    trees = new int[N];

    String[] input2 = bf.readLine().split(" ");

    for(int i =0 ; i<N; i++){
      trees[i] = Integer.parseInt(input2[i]);
      //right = Math.max(right, trees[i]);
    }

    while(left <= right)  {
      long mid = (left + right) / 2;

      if(isPossible(mid)){
        left = mid + 1;
        ans = Math.max(ans, mid);
      }else{
        right = mid - 1;
      }
    }

    System.out.println(ans);
  }

  static boolean isPossible(long height){
    long possibleToTake = 0;

    for(int tree : trees ){
      if(height >= tree) continue;
      possibleToTake += (tree - height);
    }

    return possibleToTake >= M;
  }

}

