class Solution {
  int[] diffs, times;
  long limit;
  public int solution(int[] diffs, int[] times, long limit) {

    int answer = Integer.MAX_VALUE;
    this.diffs = diffs;
    this.times = times;
    this.limit = limit;

    int left = 1;
    int right = 1;
    for(int i : diffs){
      right = Math.max(i, right);
    }

    while(left <= right){
      int mid = (left + right) / 2;

      if(isPossible(mid)){
        answer = Math.min(mid, answer);
        right = mid - 1;
      }else {
        left = mid + 1;
      }
    }

    return answer;
  }

boolean isPossible(int level) {
    long totalTime = 0;
    long prevTime = 0;

    for (int i = 0; i < diffs.length; i++) {
        long diff = diffs[i];
        long time = times[i];

        long wrongs = Math.max(0, diff - level);
        totalTime += wrongs * (prevTime + time) + time;

        if (totalTime > limit) return false;

        prevTime = time;
    }

    return true;
}
}