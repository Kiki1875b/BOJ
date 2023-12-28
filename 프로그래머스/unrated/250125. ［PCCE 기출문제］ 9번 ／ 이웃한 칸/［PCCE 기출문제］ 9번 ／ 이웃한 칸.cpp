#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<vector<string>> board, int h, int w) {
    int answer = 0;
    int dx[4] = {0,0,1,-1};
    int dy[4] = {1,-1,0,0};
    
    string start = board[h][w];
    
    for(int i = 0; i<4; i++){
        int nx = w + dx[i];
        int ny = h + dy[i];
         if(nx < 0 || ny < 0 || nx >= board[0].size() || ny >= board.size()) continue;

        if(board[ny][nx] == start) answer+=1;
    }
    
    return answer;
}