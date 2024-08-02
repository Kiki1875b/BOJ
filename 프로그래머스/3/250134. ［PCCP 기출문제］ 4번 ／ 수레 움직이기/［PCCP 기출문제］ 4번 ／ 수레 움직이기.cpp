#include <bits/stdc++.h>
using namespace std;

pair<int,int> RP, BP, RG, BG;
int n, m;
int ans = INT_MAX;  // 초기값을 최대값으로 설정합니다.
int dy[] = {1,-1,0,0};
int dx[] = {0,0,1,-1};

bool possible(int x, int y, vector<vector<bool>>& visited, vector<vector<int>>& maze){
    if(x < 0 || y < 0 || x >= n || y >= m) return false;
    if(maze[x][y] == 5) return false;  // 5는 벽을 의미합니다.
    if(visited[x][y]) return false;
    return true;
}

void dfs(vector<vector<int>>& maze, vector<vector<bool>>& redVisit, vector<vector<bool>>& blueVisit, pair<int,int> red, pair<int,int> blue, int cnt) {
    if(cnt >= ans) return;  // 현재의 최소 카운트를 초과하면 중지

    if(red == RG && blue == BG){
        ans = min(ans, cnt);
        return;
    }

    for(int i = 0; i<4; i++){
        pair<int,int> nextRed = {red.first + dx[i], red.second + dy[i]};

        if(red == RG){
            nextRed = RG;
        } else {
            if(!possible(nextRed.first, nextRed.second, redVisit, maze)) continue;
        }

        for(int j = 0; j<4; j++){
            pair<int,int> nextBlue = {blue.first + dx[j], blue.second + dy[j]};
            if(blue == BG) {
                nextBlue = BG;
            } else {
                if(!possible(nextBlue.first, nextBlue.second, blueVisit, maze)) continue;
            }

            if(nextBlue == nextRed || (red != RG && blue != BG && nextRed == blue && nextBlue == red)) continue;

            redVisit[nextRed.first][nextRed.second] = true;
            blueVisit[nextBlue.first][nextBlue.second] = true;

            dfs(maze, redVisit, blueVisit, nextRed, nextBlue, cnt + 1);

            redVisit[nextRed.first][nextRed.second] = false;
            blueVisit[nextBlue.first][nextBlue.second] = false;
        }
    }
}

int solution(vector<vector<int>> maze) {
    ans = INT_MAX;  // 초기값을 다시 설정합니다.

    for(int i = 0; i<maze.size(); i++){
        for(int j = 0; j<maze[i].size(); j++){
            if(maze[i][j] == 1) RP = {i,j};
            else if(maze[i][j] == 2) BP = {i,j};
            else if(maze[i][j] == 3) RG = {i,j};
            else if(maze[i][j] == 4) BG = {i,j};
        }
    }

    n = maze.size(), m = maze[0].size();

    vector<vector<bool>> redVisited(n, vector<bool>(m, false));
    vector<vector<bool>> blueVisited(n, vector<bool>(m, false));

    redVisited[RP.first][RP.second] = true;
    blueVisited[BP.first][BP.second] = true;

    dfs(maze, redVisited, blueVisited, RP, BP, 0);

    return (ans == INT_MAX) ? 0 : ans;
}
