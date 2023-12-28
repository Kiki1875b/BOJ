#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>
#include <map>
#include <set>

using namespace std;

int visited[501][501];
vector<vector<int>> maps;
map<int, int> cnt;
int depth = 1;
int dx[4] = {1,-1,0,0};
int dy[4] = {0,0,-1,1};
int n, m;


void BFS(int x, int y){
    queue<pair<int,int>> q;
    int tmp = 0;
    q.push({y,x});
    maps[y][x] = depth;
    visited[y][x] = 1;

    while(!q.empty()){
        int cx = q.front().second;
        int cy = q.front().first;
        tmp++;
        q.pop();

        for(int i = 0; i<4; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[ny][nx] || maps[ny][nx] == 0) continue;
            q.push({ny, nx});
            maps[ny][nx] = depth;
            visited[ny][nx] = 1;
        }
    }

    cnt[depth] = tmp;
    depth++;
}

int solution(vector<vector<int>> land) {
    int answer = 0;

    n = land.size(); // y
    m = land[0].size();  // x
    memset(visited, 0, sizeof(visited));
    maps = land;

    for(int i = 0; i<n; i++){
        for(int j = 0; j<m; j++){
            if(visited[i][j] == 0 && maps[i][j] > 0){
                BFS(j,i);
            }
        }
    }

    for(int i = 0; i<m; i++) {
        set<int> s;
        int temp = 0;
        for (int j = 0; j < n; j++) {
            s.insert(maps[j][i]);
        }
        for (auto t: s) {
            temp += cnt[t];
        }
        answer = max(answer, temp);
    }

    
    return answer;
}