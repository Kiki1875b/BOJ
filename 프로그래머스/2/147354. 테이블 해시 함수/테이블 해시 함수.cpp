#include <bits/stdc++.h>
using namespace std;


int modular(vector<int> num, int idx){
    int ret = 0;
    for(auto i : num){
        ret += (i%idx);
    }
    return ret;
//    string r = "";
//
//    if(ret == 0) return "0";
//
//    while(ret){
//        r = to_string((ret%2)) + r;
//        ret /= 2;
//    }
//    return r;
}





int solution(vector<vector<int>> data, int col, int row_begin, int row_end) {
    int answer = 0;

    sort(data.begin(), data.end(), [col](vector<int>& a, vector<int>& b){
        if(a[col - 1] == b[col - 1]){
            return a[0] > b[0];
        }else{
            return a[col - 1] < b[col - 1];
        }
    });

    vector<int> m;
    for(int i = row_begin - 1; i <= row_end - 1; i++){
        vector<int> tmp = data[i];
        m.push_back(modular(tmp, i+1));
    }

//    for(auto i : data){
//        for(auto j : i) cout << j << " ";
//        cout << endl;
//    }

    int cur = m[0];
    for(int i = 1; i<m.size(); i++){

        cur = cur ^ m[i];
    }

    return cur;
}