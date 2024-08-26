
#include <bits/stdc++.h>
using namespace std;


vector<string> solution(vector<string> players, vector<string> callings) {
    vector<string> answer;
    map<string, int> m;

    for(int i = 0; i<players.size(); i++){
        m[players[i]] = i;
    }

    for(auto c : callings){
        int current_pos = m[c];
        string prev_player = players[current_pos - 1];
        swap(players[current_pos], players[current_pos - 1]);

        m[c]--;
        m[prev_player]++;
    }

    return players;
}