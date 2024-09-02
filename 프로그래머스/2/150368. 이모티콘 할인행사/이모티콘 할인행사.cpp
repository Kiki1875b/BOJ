#include <bits/stdc++.h>
using namespace std;

/*
 * 1. 서비스 가입자를 최대로
 * 2. 판매액 최대로
 * n 명의 사용자들에게 m개의 이모티콘을 할인
 * 할인율은 10, 20, 30, 40 중 하나
 * 사용자들은 자신의 기준에 따라 {이모티콘 구매 가격 합} 이 일정 이상의 가격이 된다면
 *      -> 서비스 가입
 * 가입자를 최대로 늘리면서 판매액을 최대로 하였을때, {가입자 수, 매출액}
 */

int rate[] = {10,20,30,40};

vector<vector<int>> sales;

void dfs(int size, int cnt, int idx, vector<int> perm){
    if(cnt == size){
        sales.push_back(perm);
        return;
    }
    for(int i = 0; i<4; i++){
        perm[idx] += rate[i];
        dfs(size, cnt + 1, idx + 1, perm);
        perm[idx] -= rate[i];
    }
}

vector<int> highest(vector<vector<int>>& users, vector<int> emoticons){
    vector<int> ret(2,0);
    for(int i = 0; i<sales.size(); i++){

        vector<int> tmp = emoticons;
        vector<int> curRate = sales[i];



        for(int j = 0; j<sales[i].size(); j++){
            tmp[j] = (100 - sales[i][j]) * emoticons[j] / 100;
        }

        vector<int> cur(2,0);
        for(auto u : users){
            int buyRate = u[0];
            int serviceJoin = u[1];
            int curCost = 0;

            for(int k = 0; k<curRate.size(); k++){ // 하나의 user 에 대해 하나의 할인율 set 계산
                if(curRate[k] >= buyRate){
                    curCost += tmp[k];
                }
                if(curCost >= serviceJoin){
                    cur[0]++;
                    curCost = 0;
                    break;
                }
            }
            cur[1] += curCost;

        }

        if(ret[0] < cur[0]){
            ret = cur;
        }else if(ret[0] == cur[0] && ret[1] < cur[1]) ret = cur;
    }

    return ret;
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {

    vector<int> temp(emoticons.size(), 0);
    dfs(emoticons.size(), 0, 0, temp);

    vector<int> ans = highest(users, emoticons);

    return ans;
}
