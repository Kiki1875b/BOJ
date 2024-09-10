#include <bits/stdc++.h>
using namespace std;

/*
 *
 * 파티션으로 막혀있지 않은 경우 : 거리 2 이하로 앉으면 안된다
 * 파티션으로 막혀있는 경우 : 상관 없다
 */

int dx[] = {0,0,1,-1};
int dy[] = {1,-1,0,0};

int dist(int x1, int y1, int x2, int y2){
    return abs(x1 - x2) + abs(y1 - y2);
}

bool bfs(vector<string>& room, int x, int y){

    vector<vector<bool>> visited(room.size(), vector<bool>(room[0].length(), false));
    queue<pair<int,pair<int,int>>> q;

    q.push({0,{x,y}});
    visited[x][y] = true;

    while(!q.empty()){
        int cost = q.front().first;
        int cx = q.front().second.first;
        int cy = q.front().second.second;
        q.pop();

        for(int i = 0 ; i<4; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            int ncost = cost + 1;

            if(nx < 0 || ny < 0 || nx >= room.size() || ny >= room[0].size()) continue;
            if(visited[nx][ny]) continue;
            if(room[nx][ny] == 'X') continue;
            //if(ncost < 3) continue;
            int distance = dist(nx,ny,x,y);
            if(room[nx][ny] == 'P' && (distance < 2 || ncost < 3)) return false;

            visited[nx][ny] = true;
            q.push({ncost,{nx,ny}});
        }
    }

    return true;
}

bool check(vector<string> room){


    for(int i = 0; i<room.size(); i++){
        for(int j = 0; j<room[i].length(); j++){
            if(room[i][j] == 'P'){
                if(!bfs(room, i, j)) return false;
            }
        }
    }

    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;

    for(auto room : places){
        if(check(room)) answer.push_back(1);
        else answer.push_back(0);
    }

    return answer;

}