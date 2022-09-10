import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    final int SIZE = 5;
    private int row;
    private int col;
    private Tile[][] lake;

    //Constructor
    public Graph(int row, int col) {
        this.row = row;
        this.col = col;
        lake = new Tile[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                lake[i][j] = new Tile();
            }
        }
    }

    //Default constructor
    public Graph() {
        lake = new Tile[SIZE][SIZE];
        this.row = SIZE;
        this.col = SIZE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                lake[i][j] = new Tile();
            }
        }
    }

    //Adds a tile to the graph at lake[row][col]
    public void add(int row, int col) {
        if(this.row > row && this.col > col && row > -1 && col > -1) {
            lake[row][col] = new Tile();
            lake[row][col].setLand();
        }
    }

    //Sets a tile to be the entrance
    public void entrance(int row, int col) {
        if(this.row > row && this.col > col && row > -1 && col > -1) {
            if(lake[row][col].isLand() && !lake[row][col].isEntrance()) {
                lake[row][col].setEntrance();
            }
        }
    }

    //Displays in text output the graph in its current state. E is entrance, L is tile, W is water.
    public void view() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(lake[i][j].isLand()) {
                    if(lake[i][j].isEntrance()) {
                        System.out.print("E ");
                    } else {
                        System.out.print("L ");
                    }
                } else {
                    System.out.print("W ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    //Returns row length
    public int getRow(){
        return row;
    }

    //Returns col length
    public int getCol() {
        return col;
    }

    //Resets all tiles to water
    public void reset() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(lake[i][j].isLand()) {
                    lake[i][j].setLand();
                }
            }
        }
    }

    //Randomly generates a continuous path in the lake.
    public void random() {
        this.reset();
        Random rand = new Random();
        int tiles;  //Number of tiles in the lake
        if(row * col > 20) { //Only consider 20 or 25 tiled lakes
            tiles = rand.nextInt(12, 17);
        } else  {
            tiles = rand.nextInt(10, 15);
        }

        List<int[]> list = new ArrayList<>();
        int[] first = {rand.nextInt(0, row), rand.nextInt(0, col)};
        list.add(first);
        while(tiles > 0) {
            int[] tile = list.get(list.size() - 1);
            int[] adjacent = openAdjacents(tile[0], tile[1]);
            if(adjacent[0] == -1) {
                tile = list.get(rand.nextInt(list.size()));
            }
            adjacent = openAdjacents(tile[0], tile[1]);
            if(adjacent[0] != -1) {
                if(!lake[adjacent[0]][adjacent[1]].isLand()) {
                    lake[adjacent[0]][adjacent[1]].setLand();
                    list.add(adjacent);
                    tiles--;
                }

            }


        }
        int[] ent = list.get(rand.nextInt(list.size()));
        lake[ent[0]][ent[1]].setEntrance();


    }

    //Checks if there are open adjacent tiles, then returns one randomly. Returns
    //[-1, -1] if no adjacent tiles
    private int[] openAdjacents(int r, int c) {
        int[] a = {-1, 1};
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < a.length; i++) {
            int[] row = {r + a[i], c};
            int[] col = {r, a[i] + c};
            list.add(row);
            list.add(col);
        }
        for(int[] test : list) {
            if(test[0] < 0 || test[1] < 0 || test[0] >= row || test[1] >= col) {
                test[0] = -1;
                test[1] = -1;
            }
        }
        if(list.isEmpty()) {
            int[] fail = {-1, -1};
            return fail;
        } else {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
    }
}
