#include <bits/stdc++.h>
using namespace std;
int answer = 0;
string convert(int n, int k){
    string s = "";

    while(n){
        s = to_string(n%k) + s;
        n /= k;
    }

    return s;
}

bool isPrime(string num){

    long long number = stoll(num);
    if(number <= 1) return false;
    if(number == 2) return true;
    if(number % 2 == 0) return false;
    for(long long i = 3; i*i <= number; i++){
        if(number % i == 0) return false;
    }

    return true;
}

int solution(int n, int k) {

    string converted = convert(n,k);

    string tmp = "";

    for(int i = 0; i<converted.length(); i++){
        int s = answer;
        if(converted[i] == '0' && tmp != ""){
            if(isPrime(tmp)){
                answer++;
            }
            tmp = "";
        }else {
            tmp += converted[i];
            if(i == converted.length() - 1 && tmp!=""&& isPrime(tmp)) answer++;
        }
    }

    return answer;
}

int main(){
    solution(437674,3);
}