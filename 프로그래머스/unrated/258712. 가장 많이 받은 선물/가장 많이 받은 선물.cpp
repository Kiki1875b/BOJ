#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <sstream>

using namespace std;

int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    int n = friends.size();
    map<string, int> num;
    map<int,map<int,int>> m;
    map<int, int> received;
    map<int, int> sent;

    for(int i = 0; i<n; i++){
        num[friends[i]] = i;

    }

    for(string g: gifts){
        int pos = g.find_first_of(' ');
        int a = num[g.substr(0,pos)];
        int b = num[g.substr(pos+1)];
        sent[a]++;
        received[b]++;
        m[a][b]++;
    }
    map<int,int> nReceive;
    for(int a = 0; a< n; a++){
        for(int b = a+1; b<n; b++){
            if(m[a][b] + m[b][a] > 0 && m[a][b] != m[b][a]){
                if(m[a][b] > m[b][a]){
                    nReceive[a]++;
                }else{
                    nReceive[b]++;
                }
                continue;
            }
            int scoreA = sent[a] - received[a];
            int scoreB = sent[b] - received[b];
            if(scoreA == scoreB) continue;
            if(scoreA > scoreB){
                nReceive[a]++;
            }else{
                nReceive[b]++;
            }
        }
    }
    int maxValue = 0;
    for(auto& it: nReceive){
        if(it.second > maxValue) maxValue = it.second;
    }
    return maxValue;


    return answer;
}