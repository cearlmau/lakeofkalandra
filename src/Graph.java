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
        }
    }

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
}
