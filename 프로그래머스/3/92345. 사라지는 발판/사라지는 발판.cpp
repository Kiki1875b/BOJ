#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

/*
 * A 와 B 의 게임
 * 양 플레이어가 몇번 움직이게 될 지
 * 발판이 있어야만 이동 가능
 * 움직일 상황인데, 캐릭터의 상하좌우 4칸 모두 발판이 없거나 보드 밖이라 이동 불가 = 패
 * 두 캐릭터가 같은 발판 & 기존에 있던 사람 = 패
 * A 가 항상 먼저 시작
 */


int dx[] = {0,0,1,-1};
int dy[] = {1,-1,0,0};
pair<int,bool> dfs(vector<vector<int>>& board, int ax, int ay, int bx, int by, bool a_turn){
    if(board[ax][ay] == 0) return {0, false};
    int maxMoves = 0;
    int minMoves = INT_MAX;
    bool canWin = false;

    for(int i = 0; i<4; i++){
        int nax = ax + dx[i];
        int nay = ay + dy[i];

        if(nax < 0 || nay < 0 || nax >= board.size() || nay >= board[0].size() || board[nax][nay] == 0){
            continue;
        }

        board[ax][ay] = 0;
        auto res = dfs(board, bx, by, nax, nay, !a_turn);
        board[ax][ay] = 1;

        if(!res.second){
            minMoves = min(minMoves, res.first + 1);
            canWin = true;
        }else{
            maxMoves = max(maxMoves, res.first + 1);
        }
    }

    if(canWin){
        return {minMoves, true};
    }else{
        return {maxMoves , false};
    }
}

int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {

    return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], true).first;
}


int main(){
    vector<vector<int>> t = {{1,1,1},{1,1,1},{1,1,1}};
    solution(t, {1,0},{1,2});
}