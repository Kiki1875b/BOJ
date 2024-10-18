class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        String[] dir = new String[]{"d", "l", "r", "u"};
        int[][] direction = new int[][]{{1,0}, {0,-1}, {0,1}, {-1,0}};

        int dist = Math.abs(x - r) + Math.abs(y - c);
        if((k - dist) % 2 != 0) return "impossible";

        while(k > 0){
            boolean moved = false;
            for(int i = 0; i<4; i++){
                int nx = x + direction[i][0];
                int ny = y + direction[i][1];
                int nextDist = Math.abs(nx - r) + Math.abs(ny - c);
                if(nextDist <= k - 1 && nx > 0 && ny > 0 && nx <= n && ny <= m){
                    k--;
                    moved = true;
                    answer.append(dir[i]);
                    x = nx;
                    y = ny;
                    break;
                }
            }
            if(!moved) return "impossible";
        }

        return answer.toString();
    }
}
