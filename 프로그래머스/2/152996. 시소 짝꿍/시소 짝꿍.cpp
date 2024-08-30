#include <bits/stdc++.h>
using namespace std;

// 2/3, 2/4, 3/4


long long solution(vector<int> weights) {
    long long answer = 0;

    unordered_map<int, long long> m;
    for(auto w : weights) m[w]++;

    for(auto& [w, cnt] : m){
        answer += cnt * (cnt - 1) / 2;
        
        if(m.find(w*2/3) != m.end() && (w*2) % 3 == 0) answer += m[w*2/3] * cnt;
        if(m.find(w*2/4) != m.end() && (w*2)%4 == 0) answer+= m[w*2/4] * cnt;
        if(m.find(w*3/4) != m.end() && (w*3) %4 == 0) answer += m[w*3/4] * cnt;
    }
    return answer;
}