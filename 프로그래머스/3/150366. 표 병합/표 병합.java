import java.util.ArrayList;

class UnionFind{
    int[][][] parents;
    String[][] value;
    UnionFind(){
        this.parents = new int[51][51][2];
        this.value = new String[51][51];

        for(int i = 0; i<51; i++){
            for(int j = 0; j<51; j++){
                this.parents[i][j][0] = i;
                this.parents[i][j][1] = j;
                this.value[i][j] = "";
            }
        }
    }

    int[] find(int x, int y){
        int[] root = this.parents[x][y];
        if(root[0] == x && root[1] == y) return new int[]{x,y};
        int[] newParent = this.find(root[0], root[1]);
        this.parents[x][y] = newParent;

        return newParent;
    }

    void merge(int x1, int y1, int x2, int y2){
        int[] root1 = this.find(x1, y1);
        int[] root2 = this.find(x2, y2);

        if(root1[0] == root2[0] && root1[1] == root2[1]) return;

        if(!this.value[root1[0]][root1[1]].equals("")){
            this.parents[root2[0]][root2[1]] = new int[]{root1[0], root1[1]};
        }else{
            this.parents[root1[0]][root1[1]] = new int[]{root2[0], root2[1]};
        }
    }

    void unmerge(int x, int y) {
        int[] root = this.find(x, y);
        String originalValue = this.value[root[0]][root[1]];
        
        ArrayList<int[]> toUnmerge = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                int[] currentRoot = this.find(i, j);
                if (currentRoot[0] == root[0] && currentRoot[1] == root[1]) {
                    toUnmerge.add(new int[]{i, j});
                }
            }
        }
        
        for (int[] cell : toUnmerge) {
            this.parents[cell[0]][cell[1]] = new int[]{cell[0], cell[1]};
            this.value[cell[0]][cell[1]] = "";
        }
        
        this.value[x][y] = originalValue;
    }

    void updateValue(String oldVal, String newVal){
        for(int i = 0; i<51; i++){
            for(int j = 0; j<51; j++){
                if(this.value[i][j].equals(oldVal)){
                    this.value[i][j] = newVal;
                }
            }
        }
    }

    void updateCell(int x, int y, String value){
        int[] root = this.find(x, y);
        this.value[root[0]][root[1]] = value;
    }

    String print(int x, int y){
        int[] root = this.find(x,y);
        return this.value[root[0]][root[1]].equals("") ? "EMPTY" : this.value[root[0]][root[1]];
    }

}

class Solution {
    public String[] solution(String[] commands) {

        UnionFind uf = new UnionFind();
        ArrayList<String> ans = new ArrayList<>();

        for(String command: commands){
            String[] c = command.split(" ");

            if(c[0].equals("UPDATE")){
                if(c.length == 4){
                    int x = Integer.parseInt(c[1]);
                    int y = Integer.parseInt(c[2]);
                    uf.updateCell(x, y, c[3]);
                }else{
                    uf.updateValue(c[1], c[2]);
                }
            }else if(c[0].equals("MERGE")){
                int x1 = Integer.parseInt(c[1]);
                int y1 = Integer.parseInt(c[2]);
                int x2 = Integer.parseInt(c[3]);
                int y2 = Integer.parseInt(c[4]);
                uf.merge(x1,y1,x2,y2);
            }else if(c[0].equals("UNMERGE")){
                int x = Integer.parseInt(c[1]);
                int y = Integer.parseInt(c[2]);
                uf.unmerge(x, y);
            }else if(c[0].equals("PRINT")){
                int x = Integer.parseInt(c[1]);
                int y = Integer.parseInt(c[2]);
                String res = uf.print(x, y);
                ans.add(res);
            }
        }

        return ans.toArray(new String[0]);
    }

}
