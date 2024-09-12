#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

unordered_map<int, pair<int,int>> graph;
vector<map<pair<int,int>, set<int>>> pathTimes;  // 각 로봇의 경로와 시간을 저장
int n = 0, m = 0;

void createPath(const vector<int>& route, int robotIndex) {
    int time = 0;
    for(int j = 0; j < route.size() - 1; j++) {
        pair<int, int> begin = graph[route[j]];
        pair<int, int> end = graph[route[j+1]];

        while (begin != end) {
            pathTimes[robotIndex][begin].insert(time);

            int xDiff = end.first - begin.first;
            int yDiff = end.second - begin.second;

            if (xDiff != 0) {
                begin.first += (xDiff > 0) ? 1 : -1;
            } else if (yDiff != 0) {
                begin.second += (yDiff > 0) ? 1 : -1;
            }
            time++;
        }
    }
    pathTimes[robotIndex][graph[route.back()]].insert(time);
}

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    for(int i = 1; i <= points.size(); i++){
        n = max(n, points[i-1][0]);
        m = max(m, points[i-1][1]);
        graph[i] = {points[i-1][0], points[i-1][1]};
    }

    pathTimes.resize(routes.size());

    for(int i = 0; i < routes.size(); i++){
        createPath(routes[i], i);
    }

    int ans = 0;
    map<pair<int,int>, map<int, int>> collisionCheck;  // {point, {time, count}}

    for(int i = 0; i < pathTimes.size(); i++) {
        for(const auto& [point, times] : pathTimes[i]) {
            for(int time : times) {
                collisionCheck[point][time]++;
                if(collisionCheck[point][time] == 2) {
                    ans++;
                }
            }
        }
    }

    return ans;
}