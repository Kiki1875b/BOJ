class Solution {
    private int maxCount = 0;

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, 0);
        return maxCount;
    }

    private void dfs(int k, int[][] dungeons, boolean[] visited, int count) {
        maxCount = Math.max(maxCount, count);

        for (int i = 0; i < dungeons.length; i++) {
            int minRequired = dungeons[i][0];
            int consume = dungeons[i][1];

            if (!visited[i] && k >= minRequired) {
                visited[i] = true;
                dfs(k - consume, dungeons, visited, count + 1);
                visited[i] = false;
            }
        }
    }
}
