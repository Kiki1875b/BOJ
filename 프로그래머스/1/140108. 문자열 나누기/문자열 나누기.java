class Solution {
  public int solution(String s) {
    int ans = 0;
    char[] charAry = s.toCharArray();
    char target = 0;
    int targetCnt = 0;
    int otherCnt = 0;
    int i = 0;
    int j = 1;

    while(i < charAry.length){

      if(targetCnt == 0){
        target = charAry[i];
        targetCnt++;
      }

      while(targetCnt != otherCnt && j <= charAry.length){
        if(j >= charAry.length) break;
        if(charAry[j] == target){
          targetCnt++;
        }else{
          otherCnt++;
        }
        j++;
      }

      if(i >= charAry.length-1 && targetCnt != otherCnt) {
        ans++;
        break;
      }

      ans++;
      i = j;
      j = i + 1;
      targetCnt = 0;
      otherCnt = 0;
    }
    return ans;
  }
}