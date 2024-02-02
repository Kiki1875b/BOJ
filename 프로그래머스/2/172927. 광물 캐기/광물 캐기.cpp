#include <string>
#include <vector>
#include <cmath>

using namespace std;

int ans = 987654321;


int stress(int cnt, int idx, vector<int> m){
    int pick = (int)pow(5, 2-idx), res = 0;

    for(int i = cnt*5; i<(cnt+1) * 5 && i<m.size();i++){
        int num = m[i] / pick;
        if(num == 0) res++;
        else res += num;
    }

    return res;
}

void dfs(vector<int>& picks, vector<int>& m, int count, int result){
    if((!picks[0] && !picks[1] && !picks[2]) || count * 5 >= m.size()){
        ans = min(result, ans);
        return;
    }

    for(int i = 0; i<3; i++){
        if(picks[i]){
            picks[i]--;
            dfs(picks, m, count + 1, result + stress(count, i, m));
            picks[i]++;
        }
    }
}


int solution(vector<int> picks, vector<string> minerals) {


    vector<int> m;
    for(auto t: minerals){
        if(t[0] == 'd') m.push_back(25);
        else if(t[0] == 'i') m.push_back(5);
        else m.push_back(1);
    }

    dfs(picks, m, 0,0);


    return ans;
}