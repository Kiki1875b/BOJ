#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

vector<string> solution(vector<string> players, vector<string> callings) {
    vector<string> answer;
    map<string, int> m2;
    map<int, string> m1;
    
    for(int i = 0; i<players.size(); i++){
        m1[i] = players[i];
        m2[players[i]] = i;
    }
    for(int i = 0; i<callings.size(); i++){
        int idx = m2[callings[i]];
        string change = m1[idx - 1];
        m1[idx - 1] = callings[i];
        m1[idx] = change;
        m2[callings[i]] = idx - 1;
        m2[change] = idx;
    }
    for(auto i : m1){
        answer.push_back(i.second);
    }
    

    return answer;
}