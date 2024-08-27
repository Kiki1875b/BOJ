#include <bits/stdc++.h>
using namespace std;

pair<int,int> start;
pair<int,int> goal;
set<pair<int, pair<int,int>>> visited;

int dx[] = {1,-1,0,0};
int dy[] = {0,0,1,-1};

int ans = INT_MAX;



int solution(vector<string> board) {
    int answer = -1;

    for(int i = 0 ; i<board.size(); i++){
        for(int j = 0; j<board[0].length(); j++){
            if(board[i][j] == 'R') start = {i,j};
            if(board[i][j] == 'G') goal = {i,j};
        }
    }
    int n = board.size();
    int m = board[0].length();

    queue<pair<pair<int,int>, int>> q; // x, y, cnt
    set<pair<int,int>> visited;

    q.push({start,0});
    visited.insert(start);

    while(!q.empty()){

        auto [pos, cnt] = q.front();
        int x = pos.first; int y = pos.second;
        q.pop();

        if(x == goal.first && y == goal.second){
            answer = cnt;
            break;
        }

        for(int i = 0; i<4; i++){
            int nx = x; int ny =y;
            while(true){
                int next_x = nx + dx[i];
                int next_y = ny + dy[i];

                if (next_x < 0 || next_y < 0 || next_x >= n || next_y >= m || board[next_x][next_y] == 'D') break;

                nx = next_x; ny = next_y;
            }

            if(visited.find({nx,ny}) == visited.end()){
                q.push({{nx,ny}, cnt+1});
                visited.insert({nx,ny});
            }
        }

    }
    //cout << answer;

    return answer;
}
