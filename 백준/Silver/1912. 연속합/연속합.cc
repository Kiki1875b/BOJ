#include <iostream>
#include <algorithm>
using namespace std;
int n;
int num[100001];
int dp[100001]={0,};
int main(){
    cin >> n;
    for(int i = 0; i<n; i++){
        cin >> num[i];
        dp[i] = num[i];
    }

    int ans = dp[0];
    for(int i = 1; i<n; i++){
        dp[i] = max(dp[i-1] + num[i], dp[i]);
        ans = max(ans, dp[i]);
    }

    cout << ans;
}