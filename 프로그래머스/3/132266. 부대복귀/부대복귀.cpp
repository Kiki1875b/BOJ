#include <bits/stdc++.h>
using namespace std;

/*
 * 두 지역 간의 길을 통과 하는데 걸리는 시간은 모두 1
 * 각 부대원 은 최단 시간 부대로 복귀 하고자 한다
 * 되돌아 오는 경로가 없어져 복귀가 불가능 할 수도 있다
 * 총 지역의 수                      = n
 * 왕복할 수 있는 길 2차원 배열        = roads
 * 서로 다른 지역들을 나타내는 정수 배열 = sources
 * 부대의 지역                       = destination
 *
 * sources 의 원소 순서대로 강철부대로 복귀할 수 있는 최단시간 배열 을 구해야 한다
 * 복귀가 불가능할 경우 -1
 *
 */


vector<vector<int>> graph;
vector<int> dist;

void dijk(int node){
    priority_queue<pair<int,int>> pq;
    pq.push({node, 0});
    dist[node] = 0;

    while(!pq.empty()){
        int current = pq.top().first;
        int cost = -pq.top().second;
        pq.pop();
        if(dist[current] < cost) continue;

        for(int i = 0; i<graph[current].size(); i++){
            int next = graph[current][i];
            int nCost = cost + 1;

            if(dist[next] > nCost){
                pq.push({next, -nCost});
                dist[next] = nCost;
            }
        }
    }
}


vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    graph.resize(n+1);
    dist.resize(n+1, INT_MAX);

    for(auto path : roads){
        int a = path[0];
        int b = path[1];

        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    dijk(destination);

    for(auto s: sources){
        if(dist[s] == INT_MAX) answer.push_back(-1);
        else answer.push_back(dist[s]);
    }
  
    return answer;
}