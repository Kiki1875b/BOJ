


#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    vector<int> answer;
    unordered_map<string, int> m;

    for(int i = 0; i<name.size(); i++) m[name[i]] = yearning[i];

    for(int i = 0; i<photo.size(); i++){
        int points = 0;
        for(int j = 0; j<photo[i].size(); j++){
            if(m.find(photo[i][j]) != m.end()){
                points += m[photo[i][j]];
            }
        }
        answer.push_back(points);
    }

    return answer;
}