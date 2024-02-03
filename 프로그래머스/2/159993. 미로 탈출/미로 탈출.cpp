#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int cdy[] = {0,0,1,-1};
int cdx[] = {1,-1,0,0};
pair<int, int> s;
pair<int,int> l;
pair<int, int> e;


int dfs(vector<string> &maps, int sx, int sy, int dx, int dy){
    queue<pair<pair<int,int>,int>> q;
    bool visited[101][101] = {0,};
    int maxX = maps[0].length();
    int maxY = maps.size();
    int time = 2147000000;
    
    q.push({{sy, sx},0});

    while(!q.empty()){
        int x = q.front().first.second;
        int y = q.front().first.first;
        int t = q.front().second;
        q.pop();

        if(x == dx && y == dy){
            time = min(time, t);
        }

        for(int i = 0; i<4; i++){
            int nx = x + cdx[i];
            int ny = y + cdy[i];
            if(nx < 0 || ny < 0 || nx >= maxX || ny >= maxY) continue;
            if(visited[ny][nx] || maps[ny][nx] == 'X') continue;
            q.push({{ny,nx}, t + 1});
            visited[ny][nx] = true;

        }
    }
    return time == 2147000000 ? 0 : time;
}

int solution(vector<string> maps) {


    for(int i = 0; i<maps.size(); i++){
        for(int j = 0; j < maps[0].length(); j++){
            if(maps[i][j] == 'S'){
                s = {i,j};
            }else if(maps[i][j] == 'L'){
                l = {i,j};
            }else if(maps[i][j] == 'E'){
                e = {i,j};
            }
        }
    }

    int tmp = dfs(maps, s.second, s.first, l.second, l.first);
    int tmp2 = tmp ? dfs(maps, l.second, l.first, e.second, e.first) : -1;



    return (tmp2 == -1 || !tmp2) ? -1 : tmp + tmp2;
}