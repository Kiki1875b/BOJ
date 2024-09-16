#include <bits/stdc++.h>
using namespace std;

unordered_set<int> summit_set;
unordered_set<int> gate_set;
vector<vector<pair<int, int>>> graph;

vector<int> dijkstra(int n, vector<int>& gate_list) {
    vector<int> dist(n + 1, INT_MAX);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    
    // 모든 출입구에서 시작
    for (int gate : gate_list) {
        dist[gate] = 0;
        pq.push({0, gate});
    }
    
    while (!pq.empty()) {
        int current_intensity = pq.top().first;
        int node = pq.top().second;
        pq.pop();
        
        // 현재 노드가 산봉우리라면 더 이상 진행하지 않음
        if (summit_set.find(node) != summit_set.end()) continue;
        
        for (auto &neighbor : graph[node]) {
            int next_node = neighbor.first;
            int path_cost = neighbor.second;
            int new_intensity = max(current_intensity, path_cost);
            
            // intensity가 더 작을 경우 갱신
            if (dist[next_node] > new_intensity) {
                dist[next_node] = new_intensity;
                pq.push({new_intensity, next_node});
            }
        }
    }
    return dist;
}

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gate_list, vector<int> summit_list) {
    vector<int> answer(2, INT_MAX);
    graph.resize(n + 1);
    
    // 출입구와 산봉우리를 셋에 저장
    for (int gate : gate_list) gate_set.insert(gate);
    for (int summit : summit_list) summit_set.insert(summit);
    
    // 그래프 구성 (양방향)
    for (auto &p : paths) {
        int a = p[0], b = p[1], cost = p[2];
        graph[a].push_back({b, cost});
        graph[b].push_back({a, cost});
    }
    
    // 출입구에서 다익스트라 실행
    vector<int> min_intensity = dijkstra(n, gate_list);
    
    // 가장 낮은 intensity를 가지는 산봉우리를 찾음
    sort(summit_list.begin(), summit_list.end()); // 번호가 낮은 산봉우리 우선
    for (int summit_point : summit_list) {
        if (min_intensity[summit_point] < answer[1]) {
            answer[0] = summit_point;
            answer[1] = min_intensity[summit_point];
        }
    }
    
    return answer;
}