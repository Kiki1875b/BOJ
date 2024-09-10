#include <bits/stdc++.h>
using namespace std;
// }]()[{}]()[{
bool check(string s){
    string tm=  s;
    stack<char> st;

    for(int i = 0; i<s.length(); i++){
        if(s[i] == ']' || s[i] == ')' || s[i] == '}'){
            if(st.empty()) return false;
            if(s[i] == ']' && st.top() != '[') return false;
            if(s[i] == '}' && st.top() != '{') return false;
            if(s[i] == ')' && st.top() != '(') return false;
            st.pop();
        }else {
            st.push(s[i]);
        }
    }
    if(!st.empty()) return false;
    return true;

}
int solution(string s) {
    int answer = 0;
    string tmp = s + s;

    for(int i = 0; i < s.length(); i++){
        if(check(tmp.substr(i, s.length()))) answer++;
    }

    return answer;
}

int main(){
    solution("}]()[{");
}