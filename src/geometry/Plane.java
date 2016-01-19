package geometry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;

public class Plane {
    int x1,x2,y1,y2;

    public Plane(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public static Plane[] fromJson(JSONArray planes) {
        Plane[] build = new Plane[planes.size()];
        for(int a = 0;a < build.length;a++) {
            build[a] = fromJson((JSONObject)planes.get(a));
        }
        return(build);
    }

    public static Plane fromJson(JSONObject o) {
        return new Plane((int)((long)o.get("x1")), (int)((long)o.get("x2")), (int)((long)o.get("y1")), (int)((long)o.get("y2")));
    }

    public void draw(Graphics2D g) {
        g.drawLine(x1,y1,x2,y2);
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}
