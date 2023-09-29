#include <iostream>
#include <algorithm>

using namespace std;
int dp[1001];

int N;

int main(){
    cin >> N;
    cin >> dp[1];
    for(int i = 2; i<=N; i++){
        cin >> dp[i];
        for(int j =1; j<i; j++){
            dp[i] = max({dp[i], dp[j] + dp[i-j]});
        }
    }
    cout << dp[N];

}