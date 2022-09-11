import java.util.*;

public class Graph {
    final int SIZE = 5;
    private int row;
    private int col;
    private Tile[][] lake;

    private boolean initialized = false;

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
                    lake[i][j].setWater();
                }
            }
        }
        initialized = false;
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
        initialized = true;

    }

    //Checks if there are open adjacent tiles, then returns one randomly. Returns
    //[-1, -1] if not an adjacent tiles
    private int[] openAdjacents(int r, int c) {
        int[] a = {-1, 1};
        List<int[]> list = new ArrayList<>();
        for (int j : a) {
            int[] row = {r + j, c};
            int[] col = {r, j + c};
            list.add(row);
            list.add(col);
        }
        for(int[] test : list) {
            if(test[0] < 0 || test[1] < 0 || test[0] >= row || test[1] >= col) {
                test[0] = -1;
                test[1] = -1;
            }
        }

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));

    }


    //Solve applies an optimized arrangement of pathing of tiles
    //We allow at most 2 swap tiles. It can be assumed the player will
    //place the entrance at the most optimal location.
    public void solve() {
        if(!initialized) {
            return;
        }
        int[][] distances = new int[this.row][this.col];
        int entRow = 0;
        int entCol = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(lake[i][j].isEntrance()) {
                    entRow = i;
                    entCol = j;
                }
            }
        } //Find current entrance.
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                distances[i][j] = -1;
            }
        } //Initialize all distances as -1

        distances[entRow][entCol] = 0;
        manhattan(distances, entRow, entCol);
        //FInd furthest from entrance
        //Find furthest from furthest
        //
        for(int i = 0; i < distances.length; i++) {
            for(int j = 0; j < distances[0].length; j++) {
                if(distances[i][j] == -1 || distances[i][j] > 9) {
                    System.out.print(distances[i][j] + " ");
                } else {
                    System.out.print(" " + distances[i][j] + " ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    //Calculates the manhattan distance of all tiles from entrance.
    private int manhattan(int[][] distances, int entRow, int entCol) {
        int max = 0;
        //System.out.println(r + " " + c);
        for(int i = 0; i < this.row; i++) {
            for(int j = 0; j < this.col; j++) {
                if(lake[i][j].isLand()) {
                    distances[i][j] = Math.abs(entRow - i) + Math.abs(entCol - j);
                    if(max < distances[i][j]) {
                        max = distances[i][j];
                    }
                }

            }
        }

        return max;

    }
}
