#include <bits/stdc++.h>
using namespace std;

vector<string> data_parse(string data){
    vector<string> ret;
    string tmp = "";

    for(auto d : data){
        if(d == ' '){
            if(tmp != "and") ret.push_back(tmp);
            tmp = "";
        }else{
            tmp += d;
        }
    }
    ret.push_back(tmp);
    return ret;
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    unordered_map<string, vector<int>> m;

    for(auto data : info){
        vector<string> key = data_parse(data);
        int num = stoi(key[4]);

        for(int i = 0; i<16; i++){
            string tmp = "";
            for(int j = 0; j<4; j++){
                if(i & (1 << j)){
                    tmp += key[j];
                }else{
                    tmp += "-";
                }
            }
            m[tmp].push_back(num);
        }
    }

    for(auto& it : m){
        std::sort(it.second.begin(), it.second.end());
    }

    for(auto q : query){
        vector<string> key = data_parse(q);
        string s = key[0] + key[1] + key[2] + key[3];
        int num = stoi(key[4]);

        int cnt = m[s].end() - lower_bound(m[s].begin(), m[s].end(), num);
        answer.push_back(cnt);
    }

    return answer;
}