import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
    int answer = 0;

    Arrays.sort(score);

    for(int i = score.length; i >= m; i -= m){
      int lowestScore = score[i - m];
      answer += lowestScore * m;
    }

    return answer;
    }
}