#include <iostream>

using namespace std;

int main(){

    string s, bomb, ans;
    cin >> s >> bomb;

    ans = "";
    int bombIdx = bomb.length() - 1;

    for(int i = 0; i<s.length(); i++){
        ans += s[i];
        int ansIdx = ans.length() - 1;
        if(ans[ansIdx] == bomb[bombIdx]){
            if (ansIdx >= bombIdx){
                int cnt = 0;
                for(int j = bombIdx - 1; j >= 0; j--){
                    if(ans[ansIdx - (bombIdx - j)] == bomb[j]){
                        cnt ++;
                    }
                }
                if(cnt == bombIdx){
                    for(int j = 0; j<= bombIdx; j++){
                        ans.pop_back();
                    }
                }
            }
        }
    }
    cout << ans;
}