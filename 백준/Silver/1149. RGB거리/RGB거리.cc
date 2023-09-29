#include <iostream>
#include <algorithm>
using namespace std;
int N;


int ans[1001][3];
int main(){
    cin >> N;
    int cost[3];
    ans[0][0] = 0;
    ans[0][1] = 0;
    ans[0][2] = 0;
    for(int i = 1; i<=N; i++){
        cin >> cost[0] >> cost[1] >> cost[2];
        ans[i][0] = min(ans[i-1][1], ans[i-1][2]) + cost[0];
        ans[i][1] = min(ans[i-1][0], ans[i-1][2]) + cost[1];
        ans[i][2] = min(ans[i-1][0] , ans[i-1][1]) + cost[2];
    }
    cout << min({ans[N][0], ans[N][1], ans[N][2]});
}