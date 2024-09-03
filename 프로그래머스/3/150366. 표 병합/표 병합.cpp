#include <bits/stdc++.h>
using namespace std;

struct cell{
    int parent;
    string value = "EMPTY";
};

vector<cell> table;

// Find 함수에서 최상위 부모를 찾고, 경로 압축을 통해 성능을 최적화합니다.
int Find(int num){
    if(table[num].parent == num){
        return num;
    }
    return table[num].parent = Find(table[num].parent);
}

// Union 함수에서 두 셀을 병합할 때, 값이 없는 셀을 값이 있는 셀로 병합합니다.
void Union(int a, int b){
    a = Find(a); 
    b = Find(b);

    if(table[a].value == "EMPTY" && table[b].value != "EMPTY"){
        table[a].parent = b;
    } else {
        table[b].parent = a;
    }
}

vector<string> solution(vector<string> commands) {
    vector<string> answer;

    table.resize(50*50);
    for(int i = 0; i < table.size(); i++) {
        table[i].parent = i;
    }

    for(string s: commands){
        vector<string> tmp;
        string token;
        istringstream iss(s);
        while(iss >> token){
            tmp.push_back(token);
        }

        if(tmp[0] == "UPDATE"){
            if(tmp.size() == 4){
                int r = stoi(tmp[1]) - 1;
                int c = stoi(tmp[2]) - 1;
                int idx = Find(r * 50 + c);
                table[idx].value = tmp[3];
            }else{
                for(int i = 0; i < table.size(); i++){
                    if(table[i].value == tmp[1]) {
                        table[i].value = tmp[2];
                    }
                }
            }
        }else if(tmp[0] == "MERGE"){
            int r1 = stoi(tmp[1]) - 1;
            int c1 = stoi(tmp[2]) - 1;
            int r2 = stoi(tmp[3]) - 1;
            int c2 = stoi(tmp[4]) - 1;

            int idx1 = r1 * 50 + c1;
            int idx2 = r2 * 50 + c2;

            if (Find(idx1) != Find(idx2)) {
                Union(idx1, idx2);
            }
        }else if(tmp[0] == "UNMERGE"){
            int r = stoi(tmp[1]) - 1;
            int c = stoi(tmp[2]) - 1;
            int idx = r * 50 + c;

            int root = Find(idx);
            string val = table[root].value;
            vector<int> target;

            for(int i = 0; i < table.size(); i++){
                if(Find(i) == root) target.push_back(i);
            }

            for(auto i : target){
                table[i].parent = i;
                table[i].value = "EMPTY";
            }

            table[idx].parent = idx;
            table[idx].value = val;
        }else if(tmp[0] == "PRINT"){
            int r = stoi(tmp[1]) - 1;
            int c = stoi(tmp[2]) - 1;
            int idx = Find(r * 50 + c);
            answer.push_back(table[idx].value);
        }
    }

    return answer;
}
