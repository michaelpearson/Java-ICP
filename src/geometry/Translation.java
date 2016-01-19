package geometry;

public class Translation {
    int dx,dy,dt;

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Translation(int dx, int dy, int dt) {

        this.dx = dx;
        this.dy = dy;
        this.dt = dt;
    }
}
