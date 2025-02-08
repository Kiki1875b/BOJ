
/**
 * n 개의 퍼즐
 * 퍼즐 난이도 : diff
 * 퍼즐 소요시간 time_cur
 * 이전 퍼즐 소요시간 time_prev
 * 숙련도 : level
 *
 * diff <= level : time_cur 만큼 사용
 * diff > level : diff - level 만큼 틀림
 * 틀리면 time_cur 만큼 추가 시간 사용, 추가로 time_prev 만큼 퍼즐을 풀고 와야 한다
 *
 * 제한 시간내에 퍼즐을 풀기 위한 숙련도의 최소값을 구해야 한다
 */
class Solution {
  public int solution(int[] diffs, int[] times, long limit) {
   int left = 1;
   int right = 100000;

   while(left <= right){
     int mid = (left + right) / 2;
     if (isPossible(mid, diffs, times, limit)) {
       right = mid - 1;
     }else{
       left = mid + 1;
     }
   }

   return left;
  }

  boolean isPossible(int level, int[] diffs, int[] times, long limit){
    long totalTime = 0;
    int prevTime = times[0];

    for(int i =0 ; i<diffs.length; i++){
      int curTime = times[i];
      int curDiff = diffs[i];

      if(curDiff <= level){
        totalTime += curTime;
      }else{
        totalTime += (curDiff - level) * (curTime + prevTime) + curTime;
      }

      prevTime = curTime;
      
      if(totalTime > limit) return false;

    }
    return true;
  }
}
