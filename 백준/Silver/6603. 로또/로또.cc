
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


int n;
int arr[14], ans[6];

void dfs(int start, int depth){
    if(depth == 6){
        for(int i = 0; i<6; i++){
            cout << ans[i] << " ";
        }
        cout << "\n";
        return;
    }

    for(int i = start; i<n; i++){
        ans[depth] = arr[i];
        dfs(i + 1, depth + 1);
    }
}
int main(){

    while(1){
        vector<int> nums;
        cin >> n;
        if(n == 0) break;
        for(int i = 0; i<n; i++){
            cin >> arr[i];
        }
        dfs(0,0);
        cout << "\n";
    }
}