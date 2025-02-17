#include <iostream>
#include <queue>
#include <algorithm>


using namespace std;



vector<int> graph[10001];
int n,m,V;
vector<bool> visited(1001);


void dfs(int v){
    visited[v] = true;
    cout << v << " ";
    for(int i = 0; i<graph[v].size(); i++){
        if(!visited[graph[v][i]]){
            dfs(graph[v][i]);
        }
    }

}

void bfs(int v){

    queue<int> q;

    visited[v] = true;
    q.push(v);

    while(!q.empty()){
        int x = q.front();
        q.pop();
        cout << x << ' ';

        for(int i = 0; i < graph[x].size(); i++){
            int next = graph[x][i];
            if(!visited[next]){
                visited[next] = true;
                q.push(next);
            }
        }
    }
}


int main(){
    int a,b;


    cin >> n >> m >> V;

    for(int i = 0; i<m; i++){
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int i = 1; i <= n;i++){
        sort(graph[i].begin(), graph[i].end());
    }

    dfs(V);
    fill(visited.begin(),visited.end(), false);
    cout << endl;
    bfs(V);



}