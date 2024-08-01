#include <vector>
using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int timeTaken = bandage[0];
    int healPerSec = bandage[1];
    int bonusHeal = bandage[2];
    int maxHealth = health;
    int currentTime = 0;
    int consecutiveHealing = 0;

    for (const auto& attack : attacks) {
        int attackTime = attack[0];
        int damage = attack[1];

        // Heal until the attack
        while (currentTime < attackTime - 1) {
            currentTime++;
            consecutiveHealing++;
            
            health = min(maxHealth, health + healPerSec);
            
            if (consecutiveHealing == timeTaken) {
                health = min(maxHealth, health + bonusHeal);
                consecutiveHealing = 0;
            }
        }

        // Apply damage
        currentTime = attackTime;
        health -= damage;
        consecutiveHealing = 0;

        if (health <= 0) return -1;
    }

    return health;
}