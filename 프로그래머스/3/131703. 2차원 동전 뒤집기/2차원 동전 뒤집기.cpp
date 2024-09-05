#include <bits/stdc++.h>
using namespace std;

/*
 * beginning 의 돌 상태를 target 의 돌 상태로
 * 행, 혹은 열 단위의 뒤집기만 할 수 있다
 *
 */

int n, m;
bool isEqual(const vector<vector<int>>& a, const vector<vector<int>>& b) {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (a[i][j] != b[i][j]) return false;
        }
    }
    return true;
}
void rowFlip(vector<vector<int>>& matrix, int row){
    for(int i = 0; i<m; i++){
        matrix[row][i] = 1 - matrix[row][i];
    }
}

void colFlip(vector<vector<int>>& matrix, int col){
    for(int i = 0; i<n; i++){
        matrix[i][col] = 1 - matrix[i][col];
    }
}

int solution(vector<vector<int>> beginning, vector<vector<int>> target) {
    n = beginning.size();
    m = beginning[0].size();
    int minFlip = -1;

    for(int rowMask = 0; rowMask < (1 << n); rowMask++){

        vector<vector<int>> tmp = beginning;
        int flip = 0;

        for(int i=0; i<n; i++){
            if(rowMask & (1 << i)){
                rowFlip(tmp, i);
                flip++;
            }
        }

        for(int i = 0; i<m; i++){
            if(tmp[0][i] != target[0][i]){
                colFlip(tmp, i);
                flip++;
            }
        }

        if(isEqual(target, tmp)){
            if(minFlip == -1 || minFlip > flip){
                minFlip = flip;
            }
        }
    }

    return minFlip;
}