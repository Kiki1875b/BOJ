#include <bits/stdc++.h>
using namespace std;
typedef long long ll;


/*
 * 알고력 , 코딩력은 0 이상 정수
 * 문제를 풀기 위해서는 문제가 요구하는 일정 이상의 알고력, 코딩력 필요
 * 알고력을 높이기 위해 1 의 시간 투자
 * 코딩력을 높이기 위해 1 의 시간 투자
 * 풀 수 있는 문제중 하나를 풀기
 * 같은 문제를 여러번 풀 수 있다
 * 모든 문제를 풀 수 있는 알고력, 코딩력을 얻는 최단시간
 *
 */


int solution(int alp, int cop, vector<vector<int>> problems) {
    int answer = 0;

    int maxAlp = alp, maxCop = cop;
    
    for(auto p : problems){
        maxAlp = max(maxAlp, p[0]);
        maxCop = max(maxCop, p[1]);
    }
    
    maxAlp = max(maxAlp , alp);
    maxCop = max(maxCop, cop);
    
    vector<vector<int>> dp(maxAlp + 2 , vector<int>(maxCop + 2 , INT_MAX));
    dp[alp][cop] = 0;
    
    for(int i = alp; i<=maxAlp; i++){
        for(int j = cop; j<= maxCop; j++){
            
            if(i+1 <= maxAlp) dp[i+1][j] = min(dp[i+1][j], dp[i][j] + 1);
            else dp[i][j] = min(dp[i][j], dp[i][j] + 1);
            
            if(j + 1 <= maxCop) dp[i][j+1] = min(dp[i][j + 1], dp[i][j] + 1);
            else dp[i][j] = min(dp[i][j], dp[i][j] + 1);
            
            for(auto p  : problems){
                int reqAlp = p[0], reqCop = p[1];
                int rwdAlp = p[2], rwdCop = p[3];
                int cost = p[4];
                
                if(i >= reqAlp && j >= reqCop){
                    int nextAlp = min(maxAlp, i + rwdAlp);
                    int nextCop = min(maxCop, j + rwdCop);
                    dp[nextAlp][nextCop] = min(dp[nextAlp][nextCop], dp[i][j] + cost);
                }
                
            }
        }
    }
    

    return dp[maxAlp][maxCop];
}

