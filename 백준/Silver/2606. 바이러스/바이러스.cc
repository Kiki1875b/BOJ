#include <iostream>
#include <vector>
#include <cstring>

using namespace std;
vector<int> graph[100];
bool visited[100];
int trueCount = 0;

void dfs(int temp){
    visited[temp] = true;
    trueCount+=1;
    for(int i = 0; i < graph[temp].size(); i++){
        int y = graph[temp][i];
        if(!visited[y]) {
            dfs(y);
        }
    }
}

int main() {
    int n, m, a, b; //컴퓨터의 수, 네트워크상 직접 연결되어 있는 컴퓨터 쌍의 수
    cin >> n;

    for(int i = 0; i < 100; i++){
        visited[i] = false;
    }

    cin >> m;

    for (int i = 0; i < m; i++){
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a); //양방향 그래프이므로, a와 b를 연결
    }

    dfs(1);
    cout << trueCount - 1;

    return 0;
}
