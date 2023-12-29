//
// Created by User on 2023-12-29.
// 요격 시스템
#include <string>
#include <vector>
#include <algorithm>

using namespace std;


bool cmp(vector<int> &t1, vector<int> &t2){
    return t1[1] < t2[1];
}

int solution(vector<vector<int>> targets) {
    int answer = 0;
    int last = -1;
    sort(targets.begin(), targets.end(), cmp);

    for(auto i: targets){
        if(i[0] < last && i[1] >= last) continue;
        else{
            answer++;
            last = i[1];
        }
    }

    return answer;
}