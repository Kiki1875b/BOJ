#include <iostream>
#include <algorithm>

using namespace std;
int N;
int dp[101];
int p[2][21] ={0,};

int main(){
    cin >> N;
    for(int i = 1; i<=N; i++){
        cin >> p[0][i]; // 잃는 체력
    }

    for(int i = 1; i<=N; i++){
        cin >> p[1][i]; // 얻는 기쁜
    }

    for(int i = 1; i<=N; i++){
        for(int j = 100; j>p[0][i]; j--){
            dp[j] = max({dp[j], dp[j - p[0][i]] + p[1][i]});
        }
    }
    cout << dp[100];
}