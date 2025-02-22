import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int n = park.length;
        int m = park[0].length;


        Arrays.sort(mats);

        int left = 0, right = mats.length - 1;
        int maxMatSize = -1;


        while (left <= right) {
            int mid = (left + right) / 2;
            int size = mats[mid];

            if (canPlaceMat(size, n, m, park)) {
                maxMatSize = size;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return maxMatSize;
    }


    private boolean canPlaceMat(int size, int n, int m, String[][] park) {
        for (int i = 0; i + size <= n; i++) {
            for (int j = 0; j + size <= m; j++) {
                if (isValid(i, j, size, park)) {
                    return true;
                }
            }
        }
        return false;
    }

    
    private boolean isValid(int x, int y, int size, String[][] park) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}
