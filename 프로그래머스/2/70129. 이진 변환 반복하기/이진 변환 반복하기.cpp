#include <string>
#include <vector>

using namespace std;
vector<int> ans = {0,0};
string toBinary(int num){
    string ret = "";
    while(num){
        ret = to_string(num%2) + ret;
        num /= 2;
    }
    return ret;
}

string remove(string s){
    ans[0]++;
    string ret = "";
    for(int i = 0; i<s.length(); i++){
        if(s[i] == '1') ret += "1";
        else{
            ans[1]++;
        }
    }
    int length = ret.length();
    return toBinary(length);
}

vector<int> solution(string s) {
    vector<int> answer;
    
    string result = s;
    while(result != "1"){
        result = remove(result);
    }
    
    
    return ans;
}