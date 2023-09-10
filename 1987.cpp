#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int R, C;
int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};
bool visited[26];
char graph[21][21];
int ans;

void dfs(int x, int y, int count){
    ans = max(ans,count);
    for(int i = 0; i<4; i++){
        int nx = x+dx[i];
        int ny = y+dy[i];
        char next = graph[nx][ny];
        if(nx>=0 && ny >=0 && nx < R && ny <C){
            if(!visited[graph[nx][ny] - 'A']){
                visited[graph[nx][ny]-'A'] = true;
                dfs(nx,ny,count + 1);
                visited[graph[nx][ny]-'A'] = false;
            }
        }
    }
}
int main(){
    cin >> R >> C;
    for(int i = 0; i<R; i++){
        for(int j = 0; j<C; j++){
            cin >> graph[i][j];
        }
    }
    visited[graph[0][0]-'A'] = true;
    dfs(0,0,1);
    cout <<ans;
}
