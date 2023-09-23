#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>

#define INF 987654321

using namespace std;

int N, P, K;
vector<pair<int,int>> graph[10001];
int dist[10001];


bool dijkstra(int x){

    priority_queue<pair<int,int>> pq;
    pq.push({0, 1});



    while(!pq.empty()){

        int node = pq.top().second;
        int cost = -pq.top().first;
        pq.pop();
        if(dist[node] < cost) continue;

        for(int i = 0; i< graph[node].size(); i++){
            int nNode = graph[node][i].first;
            int nCost = graph[node][i].second;

            int w = cost + (nCost > x);
            if(dist[nNode] > w){
                dist[nNode] = w;
                pq.push({-w, nNode});
            }
        }
    }
    return dist[N] <= K;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> P >> K;
    for(int i = 0; i<P; i++){
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b,c});
        graph[b].push_back({a,c});

    }

    int l = 0, r = 1e7, ans = -1;

    while(l<=r){
        memset(dist, 0x3f, sizeof dist);
        dist[1] = 0;
        int mid = (l+r) >> 1; // 2 로 나누는 것과 동일
        if(dijkstra(mid)){
            r = mid -1;
            ans = mid;
        }else{
            l = mid + 1;
        }
    }
    cout << ans;


}