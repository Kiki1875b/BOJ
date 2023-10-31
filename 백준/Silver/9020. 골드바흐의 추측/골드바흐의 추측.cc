#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;
int t, n;
bool arr2[10001];



int main(){
    ios::sync_with_stdio(false);
	cin.tie(0);
    fill(arr2, arr2 + 10000, true);
    for(int i = 2; i<=floor(sqrt(10000))+1; i++){
        for(int j = i*i; j<= 10000; j += i){
            arr2[j] = false;
        }
    }

    arr2[0] = false; arr2[1] = false;

    cin >> t;

    while(t--){
        cin >> n;

        int m = floor(n/2);

        for(int i = m; i >= 2; i--){
            if(arr2[i] && arr2[n-i]){
                cout << i << " " << n - i << "\n";
                break;
            }
        }
    }
}