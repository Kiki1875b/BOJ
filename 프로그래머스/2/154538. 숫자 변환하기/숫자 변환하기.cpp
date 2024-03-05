#include <string>
#include <queue>
#include <iostream>
#include <vector>
#include <set>

using namespace std;
bool possible = false;
int solution(int x, int y, int n) {

    if(x == y) return 0;
    int answer = 0;

    queue<pair<int,int>> q;
    set<int> set;

    set.insert(y);
    q.push({y,0});

    while(!q.empty()){
        auto data = q.front();
        q.pop();
        if(data.first == x) {
            answer = data.second;
            break;
        }else if (data.first > x){
            int x2 = data.first / 2;
            if(data.first % 2 == 0 && set.insert(x2).second ){
                q.push({x2, data.second + 1});
            }

            int x3 = data.first / 3;
            if(data.first % 3 == 0&&set.insert(x3).second ){
                q.push({x3, data.second + 1});
            }

            int xn = data.first - n;
            if(set.insert(xn).second){
                q.push({xn, data.second + 1});
            }
        }

    }

    if(answer == 0) return -1;

    return answer;
}