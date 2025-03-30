#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, D;
vector<pair<int,int>> graph[10001];

int main(){
    cin >> N >> D;
    vector<int> map(D+1,987654321);
    for(int i = 0; i<N; i++){
        int a, b, c;
        cin >> a >> b >> c;
        graph[b].push_back({a,c});
    }

    map[0] = 0;
    for(int i = 1; i<=D; i++){
        if(graph[i].size() == 0) map[i] = map[i-1]+1;
        else{
            for(auto v: graph[i]){
                map[i] = min(map[i], min(map[i-1]+1,map[v.first] + v.second));
            }
        }

    }
    cout << map[D];
}