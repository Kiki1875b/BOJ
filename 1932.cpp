#include <iostream>
#include <vector>

using namespace std;
int n;
int dp[505][505] = {0,};

int main(){
    cin >> n;
    for(int i = 1; i<=n; i++){
        for(int j=1; j<=i;j++){
            cin >> dp[i][j];

        }
    }

    int temp = dp[1][1];

    for (int i = 2; i <=n; i++){
        for(int j = 1; j<=i+1; j++){
            if(j==1) dp[i][j] += dp[i-1][j];
            else if(j == i) dp[i][j] += dp[i-1][j-1];
            else{
                dp[i][j] += max(dp[i-1][j-1],dp[i-1][j]);

            }
            temp = max(temp, dp[i][j]);
        }
    }

    cout << temp;




}