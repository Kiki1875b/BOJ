#include <bits/stdc++.h>
using namespace std;

set<int> s;


int solution(vector<int> elements) {
    vector<int> v = elements;
    for(int i = 0; i<elements.size(); i++){
        v.push_back(elements[i]);
    }

    int i = 1;


    while(i < elements.size()){
        for(int j = 0; j<elements.size(); j++){
            int sum = 0;
            for(int k = j; k<j+i; k++){
                sum += v[k];
            }
            s.insert(sum);
        }
        i++;
    }
    
    return s.size()+1;
}
