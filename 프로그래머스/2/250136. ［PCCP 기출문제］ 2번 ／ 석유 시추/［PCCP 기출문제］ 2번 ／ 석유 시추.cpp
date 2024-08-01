#include <bits/stdc++.h>
using namespace std;

int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};
bool visited[501][501];
int n,m;
map<int, int> m2;

int search(int x, int y, vector<vector<int>>& land){

    
    set<int> track;
    queue<pair<int,int>> q;
    q.push({x,y});
    int s = 1;
    visited[x][y] = true;
    track.insert(y);
    while(!q.empty()){


        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();



        for(int i = 0; i<4; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(nx < n && ny < m && nx >= 0 && ny >= 0){
                if(!visited[nx][ny] && land[nx][ny] == 1){
                    q.push({nx,ny});
                    visited[nx][ny] = true;
                    track.insert(ny);
                    s++;
                }
            }
        }
    }

    for(auto it = track.begin(); it != track.end(); it++){
        m2[*it] += s;
        
    }


    return s;
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    n = land.size(); m = land[0].size();

    for(int i = 0; i < land.size(); i++){
        for(int j = 0; j < land[0].size(); j++){
            if(land[i][j] != 0 && !visited[i][j]){
                search(i,j, land);
            }
        }
    }

    for(int i = 0; i< m; i++){
        answer = max(answer, m2[i]);
    }

    return answer;
}
