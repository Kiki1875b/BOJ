#include <bits/stdc++.h>

using namespace std;

int solution(string s) {
    
    int answer = 0;
    unordered_map<char,int> m;
    bool isFirst = true;
    int cnt = 0;
    char first;
    
    for(int i =0; i<s.length(); i++){
        if(isFirst){
            isFirst = false;
            first = s[i];
            m[s[i]]++;
            continue;
        }
        
        if(s[i] == first){ 
            m[s[i]]++;
        }
        else{
            cnt++;
        }
        
        if(m[first] == cnt){
            answer++;
            cnt = 0;
            m.clear();
            isFirst = true;
            first = '0';
        }
        
    }
    if(!m.empty()) answer++;
    return answer;
}