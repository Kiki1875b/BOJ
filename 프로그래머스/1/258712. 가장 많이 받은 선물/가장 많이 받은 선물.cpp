#include <string>
#include <bits/stdc++.h>
#include <vector>

using namespace std;

int solution(vector<string> friends, vector<string> gifts) {
    unordered_map<string, unordered_map<string,int>> m;
    unordered_map<string, int> m2;
    unordered_map<string, int> ans;

    for(int i = 0; i<friends.size(); i++){
        for(int j = 0; j<friends.size(); j++){
            if(i == j) continue;
            m[friends[i]][friends[j]] = 0;
        }
        m2[friends[i]] = 0; ans[friends[i]] = 0;
    }

    for(auto g: gifts){
        string given, received;
        stringstream s(g);
        s >> given >> received;

        m[given][received]++;
        m2[given]++;
        m2[received]--;
    }

//    for (const auto& pair1 : m) {
//        cout << pair1.first << " => { ";
//        for (const auto& pair2 : pair1.second) {
//            cout << pair2.first << ": " << pair2.second << " ";
//        }
//        cout << "}" << endl;
//    }


    int res = 0;

    for(auto i : friends){
        for(auto it : m[i]){
          if(m[i][it.first] > m[it.first][i]){
              ans[i]++;
              res = max(res, ans[i]);
          }else if(m[i][it.first] == m[it.first][i]){
              if(m2[i] > m2[it.first]) {
                  ans[i]++;
                  res = max(res, ans[i]);
              }
          }
        }
    }

    //cout << res;

    return res;
}