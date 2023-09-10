#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int n, m;
vector<int> list;
bool selected[9];
vector<int> ans;


void dfs(int cnt){
    if(cnt == m){
        for(int i = 0; i < ans.size(); i++){
            cout << ans[i] << " ";
        }
        cout << "\n";
    }else{
        for(int i = 0; i<n; i++){
            if(selected[i]) continue;
            selected[i] = true;
            ans.push_back(list[i]);
            dfs(cnt + 1);
            ans.pop_back();
            selected[i] = false;
        }
    }

}
int main(){
    cin >> n >> m;
    for(int i = 0; i<n; i++){

        int n;
        cin >> n;
        list.push_back(n);
    }

    sort(list.begin(), list.end());

    dfs(0);
}