#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> park, vector<string> routes) {
    vector<int> answer;
    int X, Y;
    int pos[2]; pos[0] = 0; pos[1] = 0;
    for (int i = 0 ; i < routes.size(); i++){

        Y = pos[0]; X = pos[1];

        if(i == 0){
            for(int j = 0; j<park.size();j++){
                for(int k = 0; k<park[0].size();k++){
                    if(park[j][k] == 'S'){
                        X = k; Y = j;
                        pos[0] = Y; pos[1] = X;
                    }
                }
            }
        }


        char temp = routes[i][2];
        int num = temp - '0';
        char direction = routes[i][0];
        int dir[2];



        if(direction == 'E'){

            dir[0] = 0; dir[1] = 1;
        }else if (direction == 'W'){
            dir[0] = 0; dir[1] = -1;
        }else if (direction == 'S'){
            dir[0] = 1; dir[1] = 0;
        }else if (direction == 'N'){
            dir[0] = -1; dir[1] = 0;
        }

        for(int j = 0; j<num; j++){
            pos[0] += dir[0];
            pos[1] += dir[1];
            if(pos[0] < 0 || pos[1] < 0 || pos[0] >= park.size() || pos[1] >= park[0].size() || park[pos[0]][pos[1]] == 'X'){
                pos[0] = Y; pos[1] = X;
                break;
            }
        }


    }
    answer.push_back(pos[0]); answer.push_back(pos[1]);



    return answer;
}