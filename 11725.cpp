#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N;
vector<int> list[100001];
int ans[100001];
bool visit[100001];

void bfs(){
    queue<int> q;
    visit[1] = true;
    q.push(1);

    while(!q.empty()){
        int parent = q.front();
        q.pop();
        for(int i = 0; i<list[parent].size();i++){
            int child = list[parent][i];
            if(!visit[child]){
                ans[child] = parent;
                visit[child] = true;
                q.push(child);
            }
        }
    }
}
int main(){
    cin >> N;
    for (int i = 0; i<N; i++){
        int temp1, temp2;
        cin >> temp1 >> temp2;
        list[temp1].push_back(temp2);
        list[temp2].push_back(temp1);
    }
    bfs();
    for(int i = 2; i<=N; i++){
        cout << ans[i] << "\n";
    }


}
