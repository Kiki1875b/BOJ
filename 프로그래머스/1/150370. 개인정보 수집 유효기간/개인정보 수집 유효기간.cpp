#include <string>
#include <map>
#include <vector>
#include <sstream>
#include <iostream>

using namespace std;


int stringToInt(string temp){

    istringstream iss(temp);
    string token;
    int totalHours = 0;
    int year = 0, month = 0, day = 0;

    while(getline(iss, token, '.')){
        int value = stoi(token);
        if(year == 0) year = value;
        else if(month == 0) month = value;
        else if(day == 0) day = value;
    }

    totalHours = (year*12*28 + (month-1)*28 + day);
    return totalHours;

}

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    map<char, int> m1;
    map<char, int> m2;
    int curDay = stringToInt(today);


    for (int i = 0; i < terms.size(); i++) {
        istringstream iss(terms[i]);
        string token;
        string month = terms[i].substr(2);
        while (iss >> token) {

            m1[token[0]] = stoi(month);
        }
    }

    for(int i = 0; i<privacies.size(); i++){
        int y = stoi(privacies[i].substr(0, 4));
        int m = stoi(privacies[i].substr(5, 2));
        int d = stoi(privacies[i].substr(8, 2));
        char a = privacies[i].back();

        int tmp = y * 12 * 28 + (m - 1) * 28 + d + (m1[a]*28 - 1);

        if(tmp < curDay){
            answer.emplace_back(i+1);
        }

    }




    return answer;
}