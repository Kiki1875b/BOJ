#include <iostream>

using namespace std;

long long int dp[10000001];

int main(){
    long long int N;
    cin >> N;

    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;
    for(int i = 4; i<=N; i++){
        dp[i] = (dp[i-1] + dp[i-2])%10;
    }
     cout << dp[N];
}