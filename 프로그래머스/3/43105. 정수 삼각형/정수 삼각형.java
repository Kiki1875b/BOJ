class Solution {

  public int solution(int[][] triangle) {
    int answer = 0;
    int n = triangle.length;
    int[][] dp = new int[n][n];
    dp[0][0] = triangle[0][0];
    dp[1][0] = dp[0][0] + triangle[1][0];
    dp[1][1] = dp[0][0] + triangle[1][1];

    for (int i = 1; i < n; i++) {
      dp[i][0] = dp[i - 1][0] + triangle[i][0];

      for (int j = 1; j <= i; j++) {
        if (j == i) {
          dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
        }
      }
    }

    for (int num : dp[n - 1]) {
      answer = Math.max(num, answer);
    }

    return answer;
  }
}
