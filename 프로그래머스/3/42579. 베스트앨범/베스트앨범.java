import java.util.*;
class Song {
    int idx, plays;
    
    public Song(int idx, int plays){
        this.idx = idx;
        this.plays = plays;
    }

//     @Override
//     public boolean equals(Object o){
//         if(o == this) return true;
//         if(o == null || getClass() != o.getClass()) return false;
//         Song s = (Song) s;
//         return s.idx == idx && s.plays == plays;
//     }
    
//     @Override
//     public int hashCode(){
//         return Objects.hashCode(idx, plays);
//     }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, PriorityQueue<Song>> sMap = new HashMap<>();
        
        Map<String, Integer> stat = new HashMap<>();
        
        for(int i = 0; i<genres.length; i++){
            sMap.computeIfAbsent(genres[i],  k -> new PriorityQueue<Song>((a,b) -> {
                if(a.plays != b.plays) return Integer.compare(b.plays, a.plays);
                return Integer.compare(a.idx, b.idx);
            })).add(new Song(i, plays[i]));
            
            stat.put(genres[i], stat.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> sortedGenres = new ArrayList<>(stat.keySet());
        
        sortedGenres.sort((a,b) -> Integer.compare(stat.get(b), stat.get(a)));
        
        List<Integer> ans = new ArrayList<>();
        
        for(String genre : sortedGenres) {
            PriorityQueue<Song> tmp = sMap.get(genre);
            for(int i = 0; i<2;i++){
                if(tmp.isEmpty()) continue;
                ans.add(tmp.poll().idx);
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}