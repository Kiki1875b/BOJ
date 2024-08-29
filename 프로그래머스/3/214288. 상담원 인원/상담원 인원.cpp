#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <iostream>

using namespace std;

vector<vector<int>> orders; // 분배가능한 모든 경우의 수

// 모든 경우의 수를 구하는 함수(남은 인원, 유형, 컨테이너)
void backtracking(int remain, int idx, vector<int> order){
    // 모든 인원을 담았으면 orders에 추가
    if(remain <= 0){
        orders.push_back(order);
        return;
    }
    
    for(int i = idx; i < order.size(); i++){
        order[i]++;
        backtracking(remain-1,i,order);
        order[i]--;
    }
}

// 각 경우의 수에 맞춰 시뮬레이션을 돌림(순서,요청들)
int simulation(vector<int> order, vector<vector<int>> reqs){
    // 오름차순으로 뽑히는 우선순위큐를 유형의 수만큼 만듦
    priority_queue<int,vector<int>,greater<int>> pq[order.size()];
    int result = 0; // 대기 시간의 합
    
    
    for(int i = 1; i < order.size(); i++){
        // 각 경우의 수에 맞춰 각 우선순위큐에 담음
        for(int j = 0; j < order[i]; j++) pq[i].push(0);
    }
    
    for(auto req : reqs){
        int arrive = req[0]; // 요청 시각
        int time = req[1]; // 상담 시각
        int idx = req[2]; // 상담 유형
        
        // 해당 유형의 우선순위 큐에서 하나 뽑는다.
        int picked = pq[idx].top();
        pq[idx].pop();
        
        // 멘토의 시간 > 요청 시각
        if(picked > arrive){
            result += picked-arrive;
            pq[idx].push(picked+time);
        }
        
        // 멘토의 시간 < 요청 시각
        else if(picked < arrive) pq[idx].push(arrive+time);
        
        // 멘토의 시간 == 요청 시각
        else pq[idx].push(picked+time);
    }
    
    return result;
}
int solution(int k, int n, vector<vector<int>> reqs) {
    int answer = 99999999;
    
    vector<int> kind(k+1,1);
    backtracking(n-k,1,kind);
    
    for(auto order : orders){
        answer = min(answer,simulation(order,reqs));
    }
    return answer;
}