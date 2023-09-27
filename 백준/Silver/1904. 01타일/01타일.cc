#include <iostream>

using namespace std;

int dp[1000001];

int main(){
    int N;
    cin >> N;

    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;
    for(int i = 4; i<=N; i++){
        int temp1 = dp[i-1] % 15746;
        int temp2 = dp[i-2] % 15746;
        dp[i] = temp1 + temp2;
        if(i == N) break;
    }
    cout << dp[N]%15746;
}
