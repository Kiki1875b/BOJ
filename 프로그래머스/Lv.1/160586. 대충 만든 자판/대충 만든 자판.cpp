#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> answer;

    for(int i = 0 ; i<targets.size(); i++){
        answer.push_back(0);
        for(int j = 0; j<targets[i].size(); j++){
            char a = targets[i][j];
            int ans = 101;

            for(int k = 0; k<keymap.size(); k++){
                for(int z = 0; z<keymap[k].size(); z++){
                    if(keymap[k][z] == a){
                        ans = min(ans, z+1);
                    }
                }
            }
            if(ans == 101){
                answer[i] = -1;
                break;
            }
            answer[i] += ans;
        }
    }

    return answer;
}