#include <bits/stdc++.h>
using namespace std;

string hexToDec(long long num){
    string ret = "";
    
    while(num){
        ret = to_string(num % 2) + ret;
        num /= 2;
    }
    return ret;
}

string fullDec(string s){
    string ret = s;
    int idx = 2;
    while(true){
        if(s.length() < idx) break;
        idx *= 2;
    }
    for(int i = 0; i < idx - 1 - s.length(); i++) ret = "0" + ret;
    return ret;
}

bool possible(string s){
    if(s == "1") return true;
    
    bool allZero = true;
    for(int i = 0; i<s.length(); i++){
        if(s[i] == '1') allZero = false;
    }
    
    if(allZero) return true;
    
    int mid = s.length()/2;
    string left = s.substr(0,mid);
    string right = s.substr(mid + 1);
    if(s[mid] == '1' && possible(left) && possible(right)) return true;
    return false;
}

vector<int> solution(vector<long long> numbers) {
    vector<int> answer;
    for(const auto num : numbers){
        string dec = hexToDec(num);
        string fDec = fullDec(dec);
        answer.push_back(possible(fDec));
    }
    return answer;
}