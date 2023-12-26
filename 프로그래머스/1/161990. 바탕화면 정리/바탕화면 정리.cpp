#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> wallpaper) {
    vector<int> answer;
    int minX = 987654321;
    int minY = 987654321;
    int maxX = -1;
    int maxY = -1;

    for(int i = 0; i<wallpaper.size(); i++){
        for(int j = 0; j<wallpaper[i].size(); j++){
            if(wallpaper[i][j] == '.') continue;
            else{
                if(i < minY) minY = i;
                if(j < minX) minX = j;
                if(i > maxY) maxY = i;
                if(j > maxX) maxX = j;
            }
        }
    }
    answer.push_back(minY); answer.push_back(minX); answer.push_back(maxY+1); answer.push_back(maxX+1);
    return answer;
}