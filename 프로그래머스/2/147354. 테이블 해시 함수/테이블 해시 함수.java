/*

  첫 컬럼은 기본키 이다. 중복이 없다
  col 번째 컬럼 값 오름차순 정렬
  만약 col 번째 값이 같다면, 첫번째 값 정렬
  정렬된 데이터에서 S_i 는 i 번째 행의 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지의 합
  row begin -> end 까지 모두 XOR

*/

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (int[] a, int[] b) ->{
            if(a[col - 1] != b[col - 1]) return a[col - 1] - b[col - 1];
            return b[0] - a[0];
        });

        ArrayList<Integer> parsedInt = new ArrayList<>();

        for(int i = row_begin - 1; i<row_end; i++){
            int finalI = i;
            int sum = Arrays.stream(data[i])
                    .map(value -> value % (finalI + 1))
                    .sum();
            parsedInt.add(sum);
        }

        int res = parsedInt.get(0);
        for(int i = 1; i< parsedInt.size(); i++){
            res ^= parsedInt.get(i);
        }

        answer = res;

        return answer;
    }
}
