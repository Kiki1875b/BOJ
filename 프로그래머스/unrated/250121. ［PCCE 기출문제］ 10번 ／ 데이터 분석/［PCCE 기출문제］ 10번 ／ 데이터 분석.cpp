#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> data, string ext, int val_ext, string sort_by) {
    vector<vector<int>> answer;

    if(ext == "code"){
        for(auto i: data){
            if(i[0] < val_ext){
                answer.push_back(i);
            }
        }
    }else if(ext == "date"){
        for(auto i: data){
            if(i[1] < val_ext){
                answer.push_back(i);
            }
        }
    }else if(ext == "maximum"){
        for(auto i: data){
            if(i[2] < val_ext){
                answer.push_back(i);
            }
        }
    }else if(ext == "remain"){
        for(auto i: data){
            if(i[3] < val_ext){
                answer.push_back(i);
            }
        }
    }

    if(sort_by == "code"){
        sort(answer.begin(), answer.end(), [](const auto &a, const auto &b){
            return a[0] < b[0];
        });
    }else if(sort_by == "date"){
        sort(answer.begin(), answer.end(), [](const auto &a, const auto &b){
            return a[1] < b[1];
        });
    }else if(sort_by == "maximum"){
        sort(answer.begin(), answer.end(), [](const auto &a, const auto &b){
            return a[2] < b[2];
        });
    }else if(sort_by == "remain"){
        sort(answer.begin(), answer.end(), [](const auto &a, const auto &b){
            return a[3] < b[3];
        });
    }

    return answer;
}
