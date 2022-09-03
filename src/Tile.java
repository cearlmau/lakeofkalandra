public class Tile {
    private boolean entrance;
    private boolean land = false;
    public Tile() {
        entrance = false;
    }

    public void setEntrance() {
        if(land) {
            this.entrance = !entrance;
        }

    }

    public boolean isEntrance() {
        return entrance;
    }

    public void setLand() {
        this.land = !land;
    }

    public boolean isLand() {
        return land;
    }


}
