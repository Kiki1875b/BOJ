#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;

    vector<vector<int>> tmp(rows, vector<int>(columns , 0));
    int cnt = 1;
    for(int i = 0; i<rows;i++){
        for(int j = 0; j<columns; j++){
            tmp[i][j] = cnt;
            cnt++;
        }

    }

    vector<vector<int>> turned = tmp;

    for(auto query : queries){
        int x = query[0] - 1;
        int y = query[1] - 1;
        int nx = query[2] - 1;
        int ny = query[3] - 1;
        int minNum = INT_MAX;
        for(int i = x; i<=nx; i++){
            for(int j = y; j<=ny; j++){
                if((i != x && i != nx) && (j != y && j != ny)) continue;
                if(i == x){
                    if( j != y ) {
                        turned[i][j] = tmp[i][j - 1];
                    }else if (j == y){
                        turned[i][j] = tmp[i+1][j];
                    }
                }else if(i == nx){
                    if(j != ny){
                        turned[i][j] = tmp[i][j+1];
                    }else if (j == ny){
                        turned[i][j] = tmp[i-1][j];
                    }
                }else if(j == y){
                    if(i != nx){
                        turned[i][j] = tmp[i+1][j];
                    }else if( i == nx ){
                        turned[i][j] = tmp[i][j+1];
                    }
                }else if(j == ny){
                    if ( i != x ){
                        turned[i][j] = tmp[i-1][j];
                    }else if(i == x){
                        turned[i][j] = tmp[i][j-1];
                    }
                }
                minNum = min(minNum, turned[i][j]);
            }
        }
        answer.push_back(minNum);
        tmp = turned;
    }
     return answer;
}
