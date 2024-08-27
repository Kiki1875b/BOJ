#include <bits/stdc++.h>
using namespace std;

int calc(int pick, string mineral){
    if(pick == 0) return 1;
    else if(pick == 1){
        if(mineral == "diamond") return 5;
        else return 1;
    }else{
        if(mineral == "diamond") return 25;
        else if( mineral == "iron") return 5;
        else return 1;
    }
}

void DFS(vector<int>& picks, vector<string>& minerals, int& ans, int sum, int pos){
    if((picks[0] == 0 && picks[1] == 0 && picks[2] == 0) || pos == minerals.size()){
        ans = min(ans, sum);
        return;
    }

    for(int i = 0; i<3; i++){
        if(picks[i] != 0){
            picks[i]--;
            int tmpSum = sum;
            int tmpPos = pos;

            for(;tmpPos < pos + 5 && tmpPos < minerals.size(); tmpPos++){
                tmpSum += calc(i, minerals[tmpPos]);
            }
            DFS(picks, minerals, ans, tmpSum, tmpPos);
            picks[i]++;
        }
    }
}

int solution(vector<int> picks, vector<string> minerals) {
    int answer = INT_MAX;

    DFS(picks, minerals, answer, 0,0);
    return answer;
}