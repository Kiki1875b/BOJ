#include <bits/stdc++.h>
using namespace std;

int bfs(int start, int n, vector<vector<int>>& graph, vector<bool>& visited) {
    queue<int> q;
    q.push(start);
    visited[start] = true;
    int count = 1; // start 노드도 카운팅해야 함

    while (!q.empty()) {
        int node = q.front();
        q.pop();

        for (auto next : graph[node]) {
            if (!visited[next]) {
                visited[next] = true;
                q.push(next);
                count++;
            }
        }
    }

    return count; // 이 서브 트리에 속한 노드 수
}

int solution(int n, vector<vector<int>> wires) {
    int answer = INT_MAX;
    vector<vector<int>> graph(n + 1);

    // 그래프 구성
    for (auto wire : wires) {
        int a = wire[0];
        int b = wire[1];
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    // 전선을 하나씩 끊어보면서 최소 차이 계산
    for (auto wire : wires) {
        int a = wire[0];
        int b = wire[1];

        // 전선 하나를 끊음
        graph[a].erase(remove(graph[a].begin(), graph[a].end(), b), graph[a].end());
        graph[b].erase(remove(graph[b].begin(), graph[b].end(), a), graph[b].end());

        // BFS로 두 개의 서브 트리 크기 계산
        vector<bool> visited(n + 1, false);
        int sizeA = bfs(a, n, graph, visited); // 첫 번째 서브 트리
        int sizeB = n - sizeA; // 두 번째 서브 트리

        // 최소 차이 갱신
        answer = min(answer, abs(sizeA - sizeB));

        // 끊었던 전선 복구
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    return answer;
}
