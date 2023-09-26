#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int n;
vector<int> num;
int dp[10001];

int main(){
    cin >> n;
    for(int i = 0 ;i<n; i++){
        int a;
        cin >> a;
        num.push_back(a);
    }
    dp[0] = num[0];
    dp[1] = num[0] + num[1];

    //dp[2] = max(dp[1], num[0] + num[2]);

    for(int i = 2; i<n; i++){
        dp[i] = max(dp[i-3] + num[i-1] + num[i], max(dp[i-2] + num[i], dp[i-1]));
    }

    cout << dp[n-1];

}