import geometry.*;
import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Scene extends JFrame {
    private Point[] points;
    private Plane[] planes;
    private boolean showClosestPoint = false;
    private Point lastMousePosition;

    public Scene(String title, Plane[] planes, Point[] points) throws HeadlessException {
        super(title);
        this.points = points;
        this.planes = planes;

        setSize(500,520);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new DrawingPanel());
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lastMousePosition = new Point(e.getX() + (int)(Scene.this.getContentPane().getSize().getWidth() - Scene.this.getWidth()), e.getY() + (int)(Scene.this.getContentPane().getSize().getHeight() - Scene.this.getHeight()));
                showClosestPoint = true;
                Scene.this.repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                showClosestPoint = false;
                Scene.this.repaint();
            }
        });
        setVisible(true);
    }

    private Point findClosestPlane(Point p) {
        ArrayList<Point> cPoints = new ArrayList<>();
        for(Plane plane : planes) {
            Point AP = new Point(p.getX() - plane.getX1(), p.getY() - plane.getY1());
            Point AB = new Point(plane.getX2() - plane.getX1(), plane.getY2() - plane.getY1());
            float ab2 = AB.getX()*AB.getX() + AB.getY()*AB.getY();
            float ap_ab = AP.getX()*AB.getX() + AP.getY()*AB.getY();
            float t = ap_ab / ab2;
            t = t > 1 ? 1 : t < 0 ? 0 : t;
            cPoints.add(new Point((int)(plane.getX1() + (AB.getX() * t)), (int)(plane.getY1() + (AB.getY() * t))));
        }

        int minDistance = 0;
        Point minPoint = null;
        for(Point a : cPoints) {
            if(distanceBetweenPoints(a, p) < minDistance || minPoint == null) {
                minDistance = distanceBetweenPoints(a, p);
                minPoint = a;
            }
        }
        return minPoint;
    }

    private int distanceBetweenPoints(Point p1, Point p2) {
        return (int)Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    private void drawTarget(Graphics2D g) {
        for(Plane p : planes) {
            p.draw(g);
        }
    }

    private void drawSource(Graphics2D g) {
        for(Point p : points) {
            p.drawPoint(g);
        }
    }

    private void drawToClosestPlane(Graphics2D g) {
        Point end = findClosestPlane(lastMousePosition);
        g.drawLine(lastMousePosition.getX(), lastMousePosition.getY(), end.getX(), end.getY());
    }

    class DrawingPanel extends JComponent {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(Color.blue);
            Scene.this.drawTarget((Graphics2D)g);
            g.setColor(Color.red);
            Scene.this.drawSource((Graphics2D)g);
            if(showClosestPoint && lastMousePosition != null) {
                g.setColor(Color.white);
                Scene.this.drawToClosestPlane((Graphics2D)g);
            }
        }
    }
}
