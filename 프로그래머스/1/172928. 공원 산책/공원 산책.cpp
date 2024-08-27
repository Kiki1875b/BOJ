#include <bits/stdc++.h>
using namespace std;

pair<int,int> south = {1,0};
pair<int,int> north = {-1,0};
pair<int,int> east = {0,1};
pair<int,int> west = {0,-1};
vector<int> solution(vector<string> park, vector<string> routes) {
    vector<int> answer;

    int n = park.size();
    int m = park[0].size();
    pair<int,int> pos;

    for(int i =0; i<n; i++){
        for(int j =0; j<m; j++){
            if(park[i][j] == 'S') pos = {i,j};
        }
    }


    for(auto r: routes){
        char dir = r[0];
        int cnt = r[2] - '0';

        pair<int,int> direction;
        if(dir == 'S') direction = south;
        else if(dir == 'N') direction = north;
        else if(dir == 'E') direction = east;
        else direction = west;
        pair<int,int> current_pos = pos;

        for(int i = 0; i<cnt; i++){
            pair<int,int> next_pos = {pos.first + direction.first, pos.second + direction.second};
            int a = next_pos.first;
            int b = next_pos.second;
            if(next_pos.first < 0 || next_pos.second < 0 || next_pos.first >= n || next_pos.second >= m) {
                pos = current_pos;
                break;
            }

            if(park[next_pos.first][next_pos.second] == 'X') {
                pos = current_pos;
                break;
            }

            pos = next_pos;
        }

    }

    answer.push_back(pos.first);
    answer.push_back(pos.second);



    return answer;
}