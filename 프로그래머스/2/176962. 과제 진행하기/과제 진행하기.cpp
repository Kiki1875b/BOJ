#include <bits/stdc++.h>
using namespace std;

int timeToInt(string time) {
    int hours = 0, minutes = 0;
    sscanf(time.c_str(), "%d:%d", &hours, &minutes);
    return hours * 60 + minutes;
}

vector<string> solution(vector<vector<string>> plans) {
    vector<string> answer;
    vector<tuple<int, string, int>> sortedPlans;
    stack<pair<string, int>> paused;

    for (const auto& plan : plans) {
        sortedPlans.emplace_back(timeToInt(plan[1]), plan[0], stoi(plan[2]));
    }

    sort(sortedPlans.begin(), sortedPlans.end());

    int currentTime = get<0>(sortedPlans[0]);  

    for (int i = 0; i < sortedPlans.size(); i++) {
        int startTime = get<0>(sortedPlans[i]);
        string subject = get<1>(sortedPlans[i]);
        int duration = get<2>(sortedPlans[i]);


        while (currentTime < startTime && !paused.empty()) {
            auto [pausedSubject, pausedDuration] = paused.top();
            paused.pop();

            int availableTime = startTime - currentTime;
            if (availableTime >= pausedDuration) {
                answer.push_back(pausedSubject);
                currentTime += pausedDuration;
            } else {
                paused.push({pausedSubject, pausedDuration - availableTime});
                currentTime = startTime;
                break;
            }
        }

        currentTime = max(currentTime, startTime);


        if (i == sortedPlans.size() - 1 || currentTime + duration <= get<0>(sortedPlans[i+1])) {

            answer.push_back(subject);
            currentTime += duration;
        } else {

            int remainingTime = get<0>(sortedPlans[i+1]) - currentTime;
            paused.push({subject, duration - remainingTime});
            currentTime = get<0>(sortedPlans[i+1]);
        }
    }

 
    while (!paused.empty()) {
        answer.push_back(paused.top().first);
        paused.pop();
    }

    return answer;
}