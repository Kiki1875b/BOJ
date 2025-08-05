import java.util.*;

class Solution {

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean inRange(int n) {
            return x >= 0 && y >= 0 && x < n && y < n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pos)) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Pair {
        Pos p1, p2;

        public Pair(Pos p1, Pos p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair other = (Pair) o;
            return (p1.equals(other.p1) && p2.equals(other.p2)) ||
                   (p1.equals(other.p2) && p2.equals(other.p1));
        }

        @Override
        public int hashCode() {
            return p1.hashCode() + p2.hashCode();
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        Queue<Pair> queue = new LinkedList<>();
        Queue<Integer> times = new LinkedList<>();
        Set<Pair> visited = new HashSet<>();

        Pair start = new Pair(new Pos(0, 0), new Pos(0, 1));
        queue.offer(start);
        times.offer(0);
        visited.add(start);

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}}; // 하, 상, 우, 좌

        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            int time = times.poll();

            Pos p1 = now.p1;
            Pos p2 = now.p2;

            // 도착했는가?
            if ((p1.x == n - 1 && p1.y == n - 1) || (p2.x == n - 1 && p2.y == n - 1)) {
                return time;
            }

            // 1. 네 방향 이동
            for (int[] d : dir) {
                int nx1 = p1.x + d[0];
                int ny1 = p1.y + d[1];
                int nx2 = p2.x + d[0];
                int ny2 = p2.y + d[1];

                Pos np1 = new Pos(nx1, ny1);
                Pos np2 = new Pos(nx2, ny2);
                Pair next = new Pair(np1, np2);

                if (np1.inRange(n) && np2.inRange(n) &&
                    board[nx1][ny1] == 0 && board[nx2][ny2] == 0 &&
                    !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                    times.offer(time + 1);
                }
            }

            // 2. 회전 (가로 or 세로)
            boolean isHorizontal = p1.x == p2.x;

            if (isHorizontal) {
                for (int d : new int[]{-1, 1}) { // 위, 아래로 회전
                    int nx = p1.x + d;
                    if (nx < 0 || nx >= n) continue;
                    if (board[nx][p1.y] == 0 && board[nx][p2.y] == 0) {
                        Pair rot1 = new Pair(p1, new Pos(nx, p1.y));
                        Pair rot2 = new Pair(p2, new Pos(nx, p2.y));
                        if (!visited.contains(rot1)) {
                            visited.add(rot1);
                            queue.offer(rot1);
                            times.offer(time + 1);
                        }
                        if (!visited.contains(rot2)) {
                            visited.add(rot2);
                            queue.offer(rot2);
                            times.offer(time + 1);
                        }
                    }
                }
            } else { // 세로일 때 - 좌우로 회전
                for (int d : new int[]{-1, 1}) {
                    int ny = p1.y + d;
                    if (ny < 0 || ny >= n) continue;
                    if (board[p1.x][ny] == 0 && board[p2.x][ny] == 0) {
                        Pair rot1 = new Pair(p1, new Pos(p1.x, ny));
                        Pair rot2 = new Pair(p2, new Pos(p2.x, ny));
                        if (!visited.contains(rot1)) {
                            visited.add(rot1);
                            queue.offer(rot1);
                            times.offer(time + 1);
                        }
                        if (!visited.contains(rot2)) {
                            visited.add(rot2);
                            queue.offer(rot2);
                            times.offer(time + 1);
                        }
                    }
                }
            }
        }

        return -1; // 도달 불가능한 경우는 문제 조건상 없음
    }
}
