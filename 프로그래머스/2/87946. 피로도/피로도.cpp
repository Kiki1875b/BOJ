#include <bits/stdc++.h>
using namespace std;


/*
 * 피로도를 사용하여 던전을 탐험
 * {최소 피로도, 소모 피로도}
 * 최소 피로도 이상이어야 탐험이 가능, 탐험 후에는 소모 피로도 만큼 소모
 * 탐험할 수 있는 최대 던전 수
 */

int ans = 0;
vector<vector<int>> t;
void dfs(int k, int cnt, vector<vector<int>>& dungeons,
         unordered_set<int> s){


    if(ans < cnt) {
        ans = max(ans, cnt);

    }

    for(int i = 0; i < dungeons.size(); i++){
        if(k >= dungeons[i][0] && s.find(i) == s.end()){
            s.insert(i);
            
            dfs(k - dungeons[i][1],cnt + 1, dungeons, s);
            
            s.erase(i);
        }
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    unordered_set<int> s;
    
    dfs(k, 0, dungeons, s);
    
    return ans;
}

int main(){
    solution(80, {{80,20},{50,40},{30,10}});
}