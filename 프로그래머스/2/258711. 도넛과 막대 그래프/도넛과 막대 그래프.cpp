#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer(4, 0);
    vector<vector<int>> graph(1000001);
    vector<int> indegree(1000001, 0), outdegree(1000001, 0);
    int max_node = 0;

    for (const auto& edge : edges) {
        int from = edge[0], to = edge[1];
        max_node = max({max_node, from, to});
        graph[from].push_back(to);
        outdegree[from]++;
        indegree[to]++;
    }

    // Find the central node
    for (int i = 1; i <= max_node; i++) {
        if (outdegree[i] >= 2 && indegree[i] == 0) {
            answer[0] = i;
            break;
        }
    }

    // Analyze each subgraph
    for (int node : graph[answer[0]]) {
        int nodes = 1, edges = 0;
        queue<int> q;
        vector<bool> visited(max_node + 1, false);
        bool is_cycle = false;
        
        q.push(node);
        visited[node] = true;
        
        while (!q.empty()) {
            int current = q.front();
            q.pop();
            
            for (int next : graph[current]) {
                edges++;
                if (visited[next]) {
                    if (next == node) is_cycle = true;
                    continue;
                }
                visited[next] = true;
                nodes++;
                q.push(next);
            }
        }
        
        if (is_cycle) {
            if (nodes == edges) {
                answer[1]++;  // Donut
            } else {
                answer[3]++;  // Figure-8
            }
        } else {
            answer[2]++;  // Stick
        }
    }

    return answer;
}