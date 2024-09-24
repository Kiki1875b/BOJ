#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    vector<vector<int>> sum(board.size() + 1 , vector<int>(board[0].size() + 1, 0));

    for(auto s : skill){
        int val = s[5];
        if(s[0] == 1){
            val = -val;
        }

        sum[s[1]][s[2]] += val;
        if (s[3] + 1 < sum.size()) sum[s[3] + 1][s[2]] -= val;
        if (s[4] + 1 < sum[0].size()) sum[s[1]][s[4] + 1] -= val;
        if (s[3] + 1 < sum.size() && s[4] + 1 < sum[0].size()) sum[s[3] + 1][s[4] + 1] += val;
    }

    // 행을 기준으로 누적 합 계산
    for(int i = 0; i < sum.size(); i++){
        int current = 0;
        for(int j = 0; j < sum[0].size(); j++){
            current += sum[i][j];
            sum[i][j] = current;
        }
    }

    // 열을 기준으로 누적 합 계산
    for(int i = 0; i < sum[0].size(); i++){
        int current = 0;
        for(int j = 0; j < sum.size(); j++){
            current += sum[j][i];
            sum[j][i] = current;
        }
    }

    // 최종적으로 누적 합을 board에 반영하고 파괴되지 않은 건물 개수 계산
    for(int i = 0 ; i < board.size(); i++){
        for(int j = 0; j < board[i].size(); j++){
            board[i][j] += sum[i][j];
            if(board[i][j] > 0) {
                answer++; // 내구도가 1 이상인 건물은 파괴되지 않음
            }
        }
    }

    return answer;
}
