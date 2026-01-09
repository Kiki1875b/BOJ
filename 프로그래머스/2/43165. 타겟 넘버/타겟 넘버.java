class Solution {
    int[] numbers;
    int ans = 0;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        this.numbers = numbers;
        
        dfs(0, 0, target, numbers.length);
        
        return ans;
    }
    
    
    public void dfs(int idx, int cur, int target, int n){
        if(cur == target && idx == n){
            ans++;
            return;
        }else if(idx == n){
            return;
        }
        
        for(int i : new int[]{1, -1}){
            int next = cur + (numbers[idx] * i);
            dfs(idx + 1, next, target, n);
            
        }
    }
}