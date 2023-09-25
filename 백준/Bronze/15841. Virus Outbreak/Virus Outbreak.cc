#include <iostream>
#include <bits/stdc++.h>

using namespace std;
string dp[491];

string sums(string a, string b) {
    int diff = max(a.length(), b.length()) - min(a.length(), b.length());

    if(a.length() < b.length())
        for(int i=0; i<diff; i++) a = "0" + a;
    else if(a.length() > b.length())
        for(int i=0; i<diff; i++) b = "0" + b;

    vector<int> c;
    for(int i=0; i<a.length(); i++) c.push_back(a[i] - '0' + b[i] - '0');

    reverse(c.begin(), c.end());

    for(int i=0; i<c.size(); i++) {
        if(c[i] < 10) continue;

        if(i < c.size()-1) c[i+1] += c[i]/10;
        else c.push_back(c[i]/10);

        c[i] %= 10;
    }

    reverse(c.begin(), c.end());

    string ret;

    int i = 0; while(c[i] == 0) i++;
    if(i >= c.size()) ret.push_back('0');

    while(i < c.size()) {
        ret.push_back(char(c[i] + '0'));
        i++;
    }

    return ret;
}
int main(){
    dp[0] = "0";
    dp[1] = "1";
    for(int i=2; i<=490; i++){
        dp[i] = sums(dp[i-2] , dp[i - 1]);
    }
    while(1){
        int n;
        cin >> n;
        if(n == -1) break;

        cout <<"Hour " << n <<": " << dp[n] <<" cow(s) affected"<<"\n";
    }
}