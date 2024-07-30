#include <bits/stdc++.h>
using namespace std;

void dfs(int idx, int N, int target, vector<vector<int>>& comb, vector<int>& container){
    if(container.size() == target){
        comb.push_back(container);
        return;
    }
    
    for(int i = idx; i<N; i++){
        container.push_back(i+1);
        dfs(i + 1, N, target, comb, container);
        container.pop_back();
    }
}

void calcSum(int idx, int currentSum, vector<int>& tmp, vector<int>& currentComb, vector<vector<int>> dice){
    if(idx == currentComb.size()){
        tmp.push_back(currentSum);
        return;
    }
    
    for(int i = 0; i< dice[currentComb[idx] - 1].size(); i++){
        currentSum += dice[currentComb[idx] - 1][i];
        calcSum(idx + 1, currentSum, tmp, currentComb, dice);
        currentSum -= dice[currentComb[idx] - 1][i];
    }
}

vector<int> solution(vector<vector<int>> dice) {
    vector<int> answer;
    vector<vector<int>> comb; 
    vector<int> container;
    int N = dice.size();
    int target = N/2;
    
    dfs(0, N, target, comb, container);
    
    int start = 0, end = comb.size() - 1;
    int maxWin = 0;
    
    while(start < end){
        int sum1 = 0, sum2 = 0, win1= 0, win2 = 0;
        vector<int> tmp1, tmp2;
        calcSum(0, sum1, tmp1, comb[start], dice);
        calcSum(0, sum2, tmp2, comb[end], dice);
        
        std::sort(tmp1.begin(), tmp1.end());
        std::sort(tmp2.begin(), tmp2.end());
        
        for(auto n : tmp1){
            int win = lower_bound(tmp2.begin(), tmp2.end(), n) - tmp2.begin();
            if(win >= 0) win1 += win;
        }
        for(auto n : tmp2){
            int win = std::lower_bound(tmp1.begin(), tmp1.end(),n) - tmp1.begin();
            if(win >= 0) win2 += win;
        }
        
        if(win1 > win2 && win1 > maxWin){
            answer = comb[start];
            maxWin = win1;
        }else if(win2 > win1 && win2 > maxWin){
            answer = comb[end];
            maxWin = win2;
        }
        tmp1.clear();
        tmp2.clear();
        start++;
        end--;
        
    }
    
    

    return answer;
}