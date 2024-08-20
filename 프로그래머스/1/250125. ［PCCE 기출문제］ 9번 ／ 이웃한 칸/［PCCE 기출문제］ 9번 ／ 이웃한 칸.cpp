
#include <bits/stdc++.h>
using namespace std;

int dx[] = {1,-1,0,0};
int dy[] = {0,0,1,-1};

int solution(vector<vector<string>> board, int h, int w) {
    int answer = 0;
    
    string color = board[h][w];
    queue<pair<int,int>> q;
    for(int i = 0 ; i<4; i++){
        q.push({h + dx[i], w + dy[i]});
    }
    
    while(!q.empty()){
        int x = q.front().first; int y = q.front().second;
        q.pop();
        if(x < 0 || y < 0 || x >= board.size() || y >= board[0].size()) continue;
        if(board[x][y] == color) answer++;
    }

    return answer;
}