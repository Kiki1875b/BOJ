
import java.security.KeyPair;
import java.util.*;




class Solution {
  boolean[][][] visited;

  List<int[][]> locks = new ArrayList<>();
  int needToFill = 0;
  int n;
  public boolean solution(int[][] key, int[][] lock) {
    n = lock.length;

    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = -1;
    int maxY = -1;

    for(int i = 0; i< lock.length; i++){
      for(int j = 0; j<lock.length; j++){
        if (lock[i][j] == 0){
          needToFill++;
          minX = Math.min(minX, i);
          minY = Math.min(minY, j);

          maxX = Math.max(maxX, i);
          maxY = Math.max(maxY, j);
        }
      }
    }

    if (needToFill == 0 ) return true;

    int[][] minLock = new int[maxX - minX + 1][maxY - minY + 1];

    for(int i = 0; i<minLock.length; i++){
      for(int j = 0; j < minLock[0].length; j++){
        minLock[i][j] = lock[i + minX][j + minY];
      }
    }

    for(int k = 0; k<4; k++){
      locks.add(minLock);

      int m = minLock.length;
      int n = minLock[0].length;

      int[][] newLock = new int[n][m];

      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          newLock[j][m - 1 - i] = minLock[i][j];
        }
      }

      minLock = newLock;
    }

    int m = key.length;

    for (int[][] curLock : locks) {
      int lh = curLock.length;
      int lw = curLock[0].length;
      for (int x = -m + 1; x < m; x++) {
        for (int y = -m + 1; y < m; y++) {
          if (matches(key, curLock, x, y)) return true;
        }
      }
    }
//    for(int[][] l : locks){
//
//      for(int i = 0; i<key.length - l.length; i++){
//        for(int j = 0; j<key[0].length - l[0].length; j++){
//          if(matches(key, l, i, j)) return true;
//        }
//      }
//
//    }

    return false;
  }

  boolean matches(int[][] key, int[][] lock, int xOffset, int yOffset){
    int m = key.length;
    int h = lock.length; int w = lock[0].length;
    int filled = 0;

    for(int i = 0; i<h; i++){
      for(int j = 0; j<w; j++){
        int keyX = i + xOffset;
        int keyY = j + yOffset;

        int keyVal = 0;
        if(keyX >= 0 && keyY >= 0 && keyX < m && keyY < m){
          keyVal = key[keyX][keyY];
        }

        if(keyVal == 1 && lock[i][j] == 0){
          filled++;
        }else if(keyVal == 1 && lock[i][j] == 1) return false;
        else if(lock[i][j] == 0 && keyVal != 1) return false;
      }
    }

    return filled == needToFill;
  }


}
