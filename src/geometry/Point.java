package geometry;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Point {
    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawPoint(Graphics2D g) {
        g.drawLine(x,y,x,y);
    }

    public static Point[] generatePointPlane(Plane plane, double percentageOfR, double randomness) {
        Random r = new Random();
        int pointCount = (int)Math.ceil(Math.sqrt(Math.pow(Math.abs(plane.getX1() - plane.getX2()), 2) + Math.pow(Math.abs(plane.getY1() - plane.getY2()), 2)) * percentageOfR);
        Point[] build = new Point[pointCount];
        for(int a = 0;a < pointCount;a++) {
            int x = plane.getX1() + (int)Math.ceil((plane.getX2() - plane.getX1()) * ((double)a / (double)pointCount)) + (int)((r.nextDouble() * 2 - 1) * randomness);
            int y = plane.getY1() + (int)Math.ceil((plane.getY2() - plane.getY1()) * ((double)a / (double)pointCount)) + (int)((r.nextDouble() * 2 - 1) * randomness);
            build[a] = new Point(x, y);
        }
        return(build);
    }

    public static void applyTranslation(Point[] points, int dx, int dy) {
        for(Point p : points) {
            Point.applyTranslation(p, dx, dy);
        }
    }

    public static void applyTranslation(Point point, int dx, int dy) {
        point.x += dx;
        point.y += dy;
    }

    public static void applyRotation(Point[] points, Point origin, double rotation) {
        for(Point p : points) {
            Point.applyRotation(p, origin, rotation);
        }
    }

    public static void applyRotation(Point p, Point origin, double rotation) {
        int ox = p.x,oy = p.y;
        p.x = (int)(origin.x + (ox-origin.x)*Math.cos(rotation) - (oy-origin.y)*Math.sin(rotation));
        p.y = (int)(origin.y + (ox-origin.x)*Math.sin(rotation) + (oy-origin.y)*Math.cos(rotation));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
