#include <bits/stdc++.h>
using namespace std;


int dx[] = {1,-1,0,0};
int dy[] = {0,0,1,-1};
vector<vector<bool>> visited(101, vector<bool>(101, false));
int bfs(int x, int y, vector<string>& maps){

    int cnt = 0;
    queue<pair<int,int>> q;

    visited[x][y] = true;
    cnt += maps[x][y] -'0';

    q.push({x,y});

    while(!q.empty()){
        int x1 = q.front().first;
        int y1 = q.front().second;

        q.pop();

        for(int i = 0 ; i<4; i++){
            int nx = x1 + dx[i];
            int ny = y1 + dy[i];
            if(nx < 0 || ny < 0 || nx >= maps.size() || ny >= maps[0].length()) continue;

            if(maps[nx][ny] != 'X' && !visited[nx][ny]){
                q.push({nx,ny});
                visited[nx][ny] = true;
                //cout << maps[nx][ny] - '0' << " ";
                cnt += maps[nx][ny] - '0';
            }
        }
    }
    return cnt;
}
vector<int> solution(vector<string> maps) {
    vector<int> answer;

    for(int i = 0 ; i<maps.size(); i++){
        for(int j = 0; j<maps[i].length();j++){
            if(maps[i][j] != 'X' && !visited[i][j]){
                answer.push_back(bfs(i, j, maps));
            }
        }
    }

    std::sort(answer.begin(), answer.end());
    if(answer.size() == 0){
        answer.push_back(-1);
    }
    return answer;
}