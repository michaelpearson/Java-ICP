package geometry;

public class Transformation {
    int dx,dy;
    double dt;

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

    public double getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Transformation(int dx, int dy, double dt) {

        this.dx = dx;
        this.dy = dy;
        this.dt = dt;
    }


    @Override
    public String toString() {
        return "Transformation{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", dt=" + dt +
                '}';
    }
}
