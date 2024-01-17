#include <string>
#include <vector>
#include <iostream>
using namespace std;

int solution(vector<string> board) {
    int answer = 1;
    int oCount = 0; int xCount = 0;
    int oWin = 0; int xWin = 0;

    for(int i = 0; i<board.size(); i++) {
        for (int j = 0; j < board[0].size(); j++) {
            if (board[i][j] == 'X') {
                xCount++;
            } else if (board[i][j] == 'O') {
                oCount++;
            }

            if (j == 0 && board[i][j] == 'X') {
                if (board[i][j + 1] == 'X' && board[i][j + 2] == 'X') {
                    xWin += 1;
                }
            } if (j == 0 && board[i][j] == 'O') {
                if (board[i][j + 1] == 'O' && board[i][j + 2] == 'O') {
                    oWin += 1;
                }
            } if (i == 0 && board[i][j] == 'X') {
                if (board[i + 1][j] == 'X' && board[i + 2][j] == 'X') {
                    xWin += 1;
                }
            } if (i == 0 && board[i][j] == 'O') {
                if (board[i + 1][j] == 'O' && board[i + 2][j] == 'O') {
                    oWin += 1;
                }
            } if (i==0 && j==0 && board[i][j] == 'X'){
                if(board[i+1][j+1] == 'X' && board[i+2][j+2] == 'X'){
                    xWin +=1;
                }
            }if (i==0 && j==0 && board[i][j] == 'O'){
                if(board[i+1][j+1] == 'O' && board[i+2][j+2] == 'O'){
                    oWin +=1;
                }
            } if(i == 2 && j == 0 && board[i][j] == 'X'){
                if(board[i-1][j+1] == 'X' && board[i-2][j+2] == 'X'){
                    xWin+=1;
                }
            }if(i == 2 && j == 0 && board[i][j] == 'O'){
                if(board[i-1][j+1] == 'O' && board[i-2][j+2] == 'O'){
                    oWin+=1;
                }
            }
        }
    }

    if(xWin + oWin > 2) answer = 0;
    if(xWin > 0 && oCount > xCount) answer = 0;
    if(abs(oCount - xCount) > 1) answer = 0;
    if(xCount > oCount) answer = 0;
    if (oCount - xCount < 0 || oCount - xCount > 1) answer = 0;
    if(oCount < xCount) answer = 0;
    if(xWin > oWin && oCount > xCount){
        answer = 0;
    }if (xWin != 0 && oWin != 0) {
        answer = 0;
    }if (oWin > 0 && oCount - xCount <=0){
        answer = 0;
    }if(xWin > 0 && oCount != xCount){
        answer = 0;
    }
    
    //cout << answer;
    return answer;
}