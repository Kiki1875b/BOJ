#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;
int N, M;
bool selected[13];

vector<pair<int,int>> chicken;
vector<pair<int,int>> house;
vector<pair<int,int>> picked;
int final = 1e9;

int Distance(pair<int,int> a, pair<int,int> b){
    return abs(a.first - b.first) + abs(a.second - b.second);
}

void calcMin(){
    int result = 0;
    for(int i = 0; i<house.size(); i++){
        int minimum = 1e9;
        for(int j = 0; j<picked.size(); j++){
            minimum = min(minimum, Distance(house[i], picked[j]));
        }
        result += minimum;
    }
    final = min(result, final);
}
void find(int x, int m){
    if(m == M){
        calcMin();
    }
    for(int i = x; i< chicken.size(); i++){
        if(selected[i]) continue;
        selected[i] = true;
        picked.push_back(chicken[i]);
        find(i,m+1);
        selected[i] = false;
        picked.pop_back();
    }
}

int main(){
    cin >> N >> M;
    for(int i = 0; i<N; i++){
        for(int j = 0; j<N; j++){
            int temp;
            cin >> temp;
            if(temp == 1) house.push_back({i,j});
            if(temp == 2) chicken.push_back({i,j});
        }
    }
    find(0,0);
    cout << final;
}