#include <iostream>

using namespace std;
int dp[1000001] = {0,1};
int main(){

    int t;
    cin >> t;

    for(int i = 2; i<= t; i++){
        dp[i] = (dp[i-1] + dp[i-2])%1000000007;
    }
    cout << dp[t];
}