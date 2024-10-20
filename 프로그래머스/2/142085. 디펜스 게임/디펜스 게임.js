function solution(n, k, enemy) {
    let left = 0;
    let right = enemy.length;
    let answer = 0;

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);

        if (isPossible(n, k, enemy, mid)) {
            answer = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return answer;
}

function isPossible(n, k, enemy, m) {
    const selectedEnemies = enemy.slice(0, m);
    
    // Sort the enemies for the first `m` rounds in descending order
    selectedEnemies.sort((a, b) => b - a);
    
    let totalSoldiersNeeded = 0;

    // Use invincibility on the largest `k` enemies
    for (let i = k; i < selectedEnemies.length; i++) {
        totalSoldiersNeeded += selectedEnemies[i];
    }

    // If the total soldiers needed exceeds `n`, it's not possible
    return totalSoldiersNeeded <= n;
}
