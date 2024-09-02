#include <bits/stdc++.h>
using namespace std;


int dateToInt(string date){
    int year, month, day;
    char delimiter;
    istringstream d(date);

    d >> year >> delimiter >> month >> delimiter >> day;

    return (year * 12 * 28) + ((month - 1) * 28) + day;
}

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    unordered_map<char, int> t;
    unordered_map<char, vector<int>> p_map;


    int curDate = dateToInt(today);

    for(auto temp : terms){
        t[temp[0]] = stoi(temp.substr(2)) * 28;
    }

    int cnt = 1;

    for(auto priv: privacies){
        string num;
        char c;
        istringstream temp(priv);
        temp >> num >> c;

        int startDay = dateToInt(num);
        int days = t[c];

        if(startDay + days -1 < curDate){
            answer.push_back(cnt);
        }
        cnt++;
    }

    return answer;
}

