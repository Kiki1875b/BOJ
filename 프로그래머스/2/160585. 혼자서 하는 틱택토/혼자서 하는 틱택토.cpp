#include <bits/stdc++.h>
using namespace std;

bool win(vector<string>& board, char check){
    for(int i = 0; i<3; i++){
        if(board[i][0] == check && board[i][1] == check && board[i][2] == check) return true;
        if(board[0][i] == check && board[1][i] == check && board[2][i] == check) return true;
    }
    if (board[0][0] == check 
        && board[1][1] == check
        && board[2][2] == check) return true;
    
    if(board[0][2] == check
       && board[1][1] == check
       && board[2][0] == check) return true;
    
    return false;
}

int solution(vector<string> board) {
    int answer = -1;
    
    int xCount = 0 , oCount = 0;
    bool possible = true;
    bool oWin = win(board, 'O');
    bool xWin = win(board, 'X');
    
    for(int i = 0; i<board.size(); i++){
        for(int j = 0; j<board[i].length(); j++){
            if(board[i][j] == 'O') { 
                oCount++;
            }
            if(board[i][j] == 'X') { 
                xCount++;
            }
        }
    }
    
    if(xCount > oCount) possible = false;
    if(oWin && xCount == oCount) possible = false;
    if(xWin && oCount > xCount) possible = false;
    if(oCount - xCount >= 2) possible = false;
    if(oWin && xWin) possible = false;
    
    if(possible) answer = 1;
    else answer = 0;
    
    
    return answer;
}