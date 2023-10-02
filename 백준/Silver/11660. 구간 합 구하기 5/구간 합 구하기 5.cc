#include <iostream>

using namespace std;

int N, M;
int dp[1206][1206] = {0,};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cin >> N >> M;

    for(int i = 1; i<=N; i++){
        for(int j = 1; j<=N; j++){
            
            int temp;
            cin >> temp;
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + temp - dp[i-1][j-1];
        }
    }

    for(int i = 0; i<M; i++){
        int result = 0;
        int x1, x2, y1, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        result = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
        cout << result << "\n";
    }

}