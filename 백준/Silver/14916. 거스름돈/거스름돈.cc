#include <iostream>
#include <algorithm>

using namespace std;
int ans;
int dp[100001];
int main(){
    int n;
    cin >> n;

    fill(dp, dp + n, -1);
    dp[0] = 0;
    dp[1] = -1;
    dp[2] = 1;
    dp[3] = -1;
    dp[4] = 2;
    dp[5] = 1;
    for(int i = 6; i<=n; i++){
        if(dp[i-2] == -1 && dp[i-5] == -1) continue;
        else if(dp[i-2] == -1){
            dp[i] = dp[i-5] + 1;
        }else if(dp[i-5] == -1){
            dp[i] = dp[i-2] + 1;
        }
        else{
            dp[i] = min(dp[i-5] + 1, dp[i-2] + 1);
        }
    }
    cout << dp[n];
}