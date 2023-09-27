#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;
int dp[1001];
int main(){
    int N;
    cin >> N;
    fill(dp,dp+N,-1);

    dp[1] = 1;
    dp[2] = -1;
    dp[3] = 1;
    dp[4] = 1;

    for(int i = 5; i<=N; i++){
        if(min({dp[i-4], dp[i-3] ,dp[i-1]}) == 1){
            dp[i] = -1;
        }else{
            dp[i] = 1;
        }
    }
    if(dp[N] == 1){
        cout << "SK";
    }else{
        cout << "CY";
    }

}