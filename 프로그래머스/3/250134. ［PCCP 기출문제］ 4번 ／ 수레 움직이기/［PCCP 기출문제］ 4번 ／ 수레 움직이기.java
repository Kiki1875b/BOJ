import java.util.Objects;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class Solution {
    int ans = Integer.MAX_VALUE;
    int n, m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    Point redPos, bluePos, redGoal, blueGoal;

    // goal 파라미터를 추가하고, "자신의 골 칸에 머물기"는 예외적으로 허용한다.
    boolean isPossible(Point cur, Point next, int[][] maze, boolean[][] visited, Point goal) {
        // 이미 골에 있고 그대로 머무는 경우 허용
        if(cur.equals(goal) && next.equals(goal)) {
            return true;
        }
        // 격자 범위 밖 or 벽 -> 불가능
        if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m) return false;
        if(maze[next.x][next.y] == 5) return false;

        // (다른 칸으로 이동하는 경우) 방문했던 칸이면 불가능
        if(visited[next.x][next.y]) return false;

        return true;
    }

    void dfs(int cnt, Point curRed, Point curBlue,
             int[][] maze, boolean[][] redVisited, boolean[][] blueVisited) {

        if(cnt >= ans) return;
        // 두 수레 모두 골에 도달 시 갱신
        if(curRed.equals(redGoal) && curBlue.equals(blueGoal)) {
            ans = Math.min(ans, cnt);
            return;
        }

        // 빨강 방향 4가지, 파랑 방향 4가지
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                // 현재 빨강이 골에 있으면 그대로, 아니면 다음 칸
                Point nextRed = curRed.equals(redGoal)
                                ? redGoal
                                : new Point(curRed.x + dx[i], curRed.y + dy[i]);

                // 현재 파랑이 골에 있으면 그대로, 아니면 다음 칸
                Point nextBlue = curBlue.equals(blueGoal)
                                 ? blueGoal
                                 : new Point(curBlue.x + dx[j], curBlue.y + dy[j]);

                // 빨간 수레 이동 가능 여부
                if(!isPossible(curRed, nextRed, maze, redVisited, redGoal)) continue;
                // 파란 수레 이동 가능 여부
                if(!isPossible(curBlue, nextBlue, maze, blueVisited, blueGoal)) continue;

                // 두 수레가 같은 칸으로 이동하면 불가능
                if(nextRed.equals(nextBlue)) continue;

                // 서로 자리 바꾸는 경우도 불가능
                // (둘 다 골에 있지 않을 때) curRed <-> nextBlue, curBlue <-> nextRed
                if(!curRed.equals(redGoal) && !curBlue.equals(blueGoal)
                   && nextRed.equals(curBlue) && nextBlue.equals(curRed)) {
                    continue;
                }

                // 방문 처리
                redVisited[nextRed.x][nextRed.y] = true;
                blueVisited[nextBlue.x][nextBlue.y] = true;

                // 재귀
                dfs(cnt + 1, nextRed, nextBlue, maze, redVisited, blueVisited);

                // 백트래킹
                redVisited[nextRed.x][nextRed.y] = false;
                blueVisited[nextBlue.x][nextBlue.y] = false;
            }
        }
    }

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        // 시작점/도착점 찾기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(maze[i][j] == 1) redPos = new Point(i, j);
                if(maze[i][j] == 2) bluePos = new Point(i, j);
                if(maze[i][j] == 3) redGoal = new Point(i, j);
                if(maze[i][j] == 4) blueGoal = new Point(i, j);
            }
        }

        boolean[][] redVisited = new boolean[n][m];
        boolean[][] blueVisited = new boolean[n][m];

        // 시작점 방문 체크
        redVisited[redPos.x][redPos.y] = true;
        blueVisited[bluePos.x][bluePos.y] = true;

        dfs(0, redPos, bluePos, maze, redVisited, blueVisited);

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
