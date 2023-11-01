#include <iostream>

using namespace std;

int arr[100001];
int sum[100001];

int main(){
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
    int n, k, ans = -99999999;
    cin >> n >> k;

    for(int i = 1; i<=n; i++){
        cin >> arr[i];
        sum[i] = sum[i-1] + arr[i];
    }
    for(int i = k; i<=n; i++){
        ans = max(ans, sum[i] - sum[i-k]);
    }
    cout << ans;


}
