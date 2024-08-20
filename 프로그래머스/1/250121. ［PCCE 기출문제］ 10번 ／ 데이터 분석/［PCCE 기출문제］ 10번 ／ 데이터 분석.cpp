


#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> solution(vector<vector<int>> data, string ext, int val_ext, string sort_by) {
    vector<vector<int>> answer;
    int idx = 0;
    int idx2= 0;
    if(ext == "code") idx = 0;
    else if(ext =="date") idx = 1;
    else if(ext == "maximum") idx = 2;
    else idx = 3;

    if(sort_by == "code") idx2 = 0;
    else if(sort_by == "date") idx2 = 1;
    else if(sort_by == "maximum") idx2 = 2;
    else idx2 = 3;

    for(auto d : data){
        if(d[idx] > val_ext) continue;
        else{
            answer.push_back(d);
        }
    }

    sort(answer.begin(), answer.end(), [idx2](const vector<int>& a, const vector<int>& b) {
        return a[idx2] < b[idx2];
    });


    return answer;
}