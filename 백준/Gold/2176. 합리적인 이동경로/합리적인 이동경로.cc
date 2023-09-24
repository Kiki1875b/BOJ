#include <bits/stdc++.h>
 
using namespace std;
using pii = pair<int, int>;
using graph = vector<vector<pii>>;
 
int n, m;
int dist[1001];
int dp[1001];
 
void dijkstra(graph &al);
 
int main()
{
    fill(dist, dist + 1001, numeric_limits<int>::max());
    ios::sync_with_stdio(false); cin.tie(0);
 
    cin >> n >> m;
 
    graph adj(n + 1);
    
    for (int i = 0; i < m; ++i)
    {
        int go, to, fee;
        cin >> go >> to >> fee;
        adj[go].push_back({to, fee});
        adj[to].push_back({go, fee});
    }
 
    dijkstra(adj);
    cout << dp[1];
}
 
void dijkstra(graph &al)
{
    priority_queue<pii, vector<pii>, greater<pii>> pq;
 
    pq.push({0, 2});
    dist[2] = 0;
    dp[2] = 1;
 
    while (pq.size())
    {
        auto cur = pq.top();
        pq.pop();
 
        if (cur.first > dist[cur.second])
            continue;
        
        for (auto item: al[cur.second])
        {
            int ndist = item.second + cur.first;
 
            if (ndist < dist[item.first])
            {
                dist[item.first] = ndist;
                pq.push({ndist, item.first});
            }
 
            if (cur.first > dist[item.first])
                dp[cur.second] += dp[item.first];
        }
    }
}
 