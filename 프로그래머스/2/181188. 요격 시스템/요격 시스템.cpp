
#include <bits/stdc++.h>
using namespace std;


bool cmp(vector<int>& t1, vector<int>& t2){
    return t1[1] < t2[1];
}

int solution(vector<vector<int>> targets) {
    int answer = 0;
    int last = -1;

    sort(targets.begin(), targets.end(), cmp);

    for(auto t : targets){
        if(t[0] < last && t[1] >= last){
            continue;
        }else{
            last = t[1];
            answer++;
        }
    }



    return answer;
}