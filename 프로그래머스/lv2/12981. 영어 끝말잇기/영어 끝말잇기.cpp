#include <string>
#include <vector>
#include <iostream>
#include <set>

using namespace std;
int N;
set<string> temp;

vector<int> solution(int n, vector<string> words) {
    
    for(int i = 0; i<words.size(); i++){
        if(temp.count(words[i])){
            return {i%n + 1, i/n + 1 };
        }
        temp.insert(words[i]);
        if(i != 0 && words[i].front() != words[i-1].back()){
            return { i%n + 1 , i/n + 1 };
        }
    }
    return {0,0};
}