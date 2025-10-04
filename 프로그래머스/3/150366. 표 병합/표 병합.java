import java.util.*;


class UF {
    List<List<int[]>> parent;
    String[][] val;
    
    public UF(){
        parent = new ArrayList<>();
        for(int i = 0; i<51; i++){
            parent.add(new ArrayList<>());
        }
        
        for(int i = 0; i<51; i++){
            for(int j = 0; j<51; j++){
                parent.get(i).add(new int[]{i,j});
            }
        }
        
        val = new String[51][51];
    }
    
    
    void merge(int x1, int y1, int x2, int y2){
        int[] r1 = find(x1, y1);
        int[] r2 = find(x2, y2);
        
        if(r1[0] == r2[0] && r1[1] == r2[1]) return;
        
        String v1 = val[r1[0]][r1[1]];
        String v2 = val[r2[0]][r2[1]];
        String keep = (v1 != null) ? v1 : v2;
        
        parent.get(r2[0]).set(r2[1], r1);
        val[r1[0]][r1[1]] = keep;
        val[r2[0]][r2[1]] = null;
    }
    
    void unmerge(int x, int y){
        
        int[] root = find(x, y);
        String value = val[root[0]][root[1]];
        
        List<int[]> members = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                int[] r = find(i, j);
                if (r[0] == root[0] && r[1] == root[1]) {
                    members.add(new int[]{i, j});
                }
            }
        }
        
        for(int[] cell : members) {
            int i = cell[0], j = cell[1];
            parent.get(i).set(j, new int[]{i,j});
            val[i][j] = null;
        }
        
        val[x][y] = value;
    }
    
    void update(int x, int y, String value){
        int[] root = find(x, y);
        
        val[root[0]][root[1]] = value;
    }
    
    void updateFromVal(String val1, String val2){
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                int[] r = find(i, j);
                String cur = val[r[0]][r[1]];
                if (val1.equals(cur)) {
                    val[r[0]][r[1]] = val2;
                }
            }
        }
    }
    
    int[] find(int x, int y) {
        int[] cur = parent.get(x).get(y);
        if (cur[0] == x && cur[1] == y) return cur;
        int[] root = find(cur[0], cur[1]);
        parent.get(x).set(y, root);
        return root;
    }
    
    String print(int x, int y){
        int[] root = find(x, y);
        
        return findValue(root[0], root[1]);
    }
    
    String findValue(int x, int y){
        return val[x][y];
    }
}


class Solution {
    public String[] solution(String[] commands) {
        String[] answer;
        
        UF uf = new UF();
        List<String> ans = new ArrayList<>();
        for(String command: commands){
            String[] tmp = command.split(" ");
            String c = tmp[0];
            
            if(c.equals("UPDATE") && tmp.length == 4) { 
                int x = Integer.parseInt(tmp[1]);
                int y = Integer.parseInt(tmp[2]);
                String val = tmp[3];
                
                uf.update(x, y, val);
            }else if(c.equals("MERGE")){
                int x1 = Integer.parseInt(tmp[1]);
                int y1 = Integer.parseInt(tmp[2]);
                int x2 = Integer.parseInt(tmp[3]);
                int y2 = Integer.parseInt(tmp[4]);
                
                uf.merge(x1, y1, x2, y2);
                
            }else if(c.equals("UPDATE")){
                String val1 = tmp[1];
                String val2 = tmp[2];
                
                uf.updateFromVal(val1, val2);
            }else if(c.equals("UNMERGE")){
                int x1 = Integer.parseInt(tmp[1]);
                int y1 = Integer.parseInt(tmp[2]);
                uf.unmerge(x1, y1);
            } else {
                int x1 = Integer.parseInt(tmp[1]);
                int y1 = Integer.parseInt(tmp[2]);
                String res = uf.print(x1, y1);
                res = (res == null || res.equals(""))? "EMPTY" : res;
                ans.add(res);
            }   
        }
        
        answer = new String[ans.size()];
        for(int i = 0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}