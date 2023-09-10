#include <iostream>
#include <vector>
#include <stdio.h>
#include <queue>
#define INF 1e9

using namespace std;
int N, M, start, destination;
vector<pair<int,int>> graph[1001];
int dist[1001];

void dijkstra(){
    priority_queue<pair<int,int>> pq;
    pq.push({0,start}); // (이 노드 까지의 cost, 노드 번호)
    dist[start] = 0;
    while(!pq.empty()){

        int cost = -pq.top().first; //노드까지의 비용
        int cur = pq.top().second; //노드의 번호
        pq.pop();
        if(dist[cur] < cost) continue; // 현재 노드의 최솟값이 이미 측정 되었다면..

        for(int i = 0; i < graph[cur].size(); i++){ // 현재 노드와 연결된 도시의 수 만큼
            int next = graph[cur][i].first; //(목적노드, 노드까지의 비용)
            int ncost = cost + graph[cur][i].second;
            if(dist[next] > ncost){
                dist[next] = ncost;
                pq.push(make_pair(-ncost, next));
            }
        }


    }
    cout << dist[destination];


}
int main(){
    cin >> N >> M;

    for(int i =0; i<M; i++){
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b,c});
    }
    fill(dist,dist + 1001, INF);
    cin >> start >> destination;

    dijkstra();
}