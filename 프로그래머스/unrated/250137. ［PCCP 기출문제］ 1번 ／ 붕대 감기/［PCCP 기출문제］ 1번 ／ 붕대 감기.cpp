#include <string>
#include <iostream>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;

    int time_total = bandage[0];
    int heal_sec = bandage[1];
    int heal_final = bandage[2];

    int current_health = health;

    int monster_count = 0;
    int continuous = 0;
    int time = 0;

    while(true){

        if(attacks[monster_count][0] == time){

            current_health -= attacks[monster_count][1];

            if(monster_count + 1 == attacks.size()){
                break;
            }

            monster_count += 1;
            time += 1;
            continuous = 0;

            if(current_health <= 0) return -1;

            continue;
        }

        if(current_health < health){
            if((continuous + 1) >= time_total){ // 연속 성공시
                current_health += heal_sec + heal_final;
                continuous = 0;
            }else{
                current_health += heal_sec;
                continuous += 1;
            }
            if(current_health > health) current_health = health;


        }
        time += 1;
    }


    if(current_health <= 0){
        answer = -1;
    }else{
        answer = current_health;
    }

    return answer;
}