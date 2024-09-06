#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer;
    unordered_map<string, int> reports;
    unordered_map<string, vector<string>> reporter;
    unordered_set<string> duplicate_check;
    
    for(auto r : report){
        string s1, s2;
        istringstream iss(r);
        iss >> s1 >> s2;
        
        if(duplicate_check.find(r) == duplicate_check.end()){
            duplicate_check.insert(r);
            reports[s2]++;
            reporter[s1].push_back(s2);
        }
    }
    
    for(auto id : id_list){
        int cnt = 0;
        if(reporter[id].size() == 0) answer.push_back(0);
        else{
            for(int i = 0; i < reporter[id].size(); i++){
                string rep = reporter[id][i];
                if(reports[rep] >= k) cnt++;
            }
            
            answer.push_back(cnt);
        }
    }
    
    
    return answer;
}