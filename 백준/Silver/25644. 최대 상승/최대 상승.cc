#include <iostream>
#include <algorithm>
using namespace std;

int arr[200001];
int dp[200001];
int main(){
    int N;
    int ans = 0;

    cin >> N;
    int max_num = -1;
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    for(int i = N-1; i >=0; i--){
        if(arr[i] > max_num){
            max_num = arr[i];
        }
        ans = max(ans, max_num - arr[i] );
    }
    cout << ans;

}