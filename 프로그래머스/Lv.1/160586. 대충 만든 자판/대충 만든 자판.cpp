#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> answer;

    vector<int> table(26, 100000);
    for(const auto& v : keymap){
        for(int i = 0 ; i<v.size(); i++){
            table[v[i] - 'A'] = min(table[v[i] - 'A'], i + 1);
        }
    }

    for(const auto& str: targets){
        int total = 0;
        for(const auto c: str){
            if(table[c - 'A'] == 100000){
                total = -1;
                break;
            }else{
                total += table[c-'A'];
            }
        }
        answer.push_back(total);
    }



    return answer;
}