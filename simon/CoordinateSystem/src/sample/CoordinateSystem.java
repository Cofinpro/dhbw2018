package sample;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

class CoordinateSystem {
    private SortedSet<Point> points;
    private double maxX;
    private double maxY;

    CoordinateSystem(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        points = new TreeSet<>();
        points.add(new Point(0, 0));
    }

    SortedSet<Point> getPoints() {
        return points;
    }

    void add(Point point) {
        if (point.getX() > maxX || point.getX() < -maxX || point.getY() > maxY || point.getY() < -maxY) {
            throw new IllegalArgumentException("The x and y values of the point are outside of the bounds.");
        }
        if (!points.add(point)) {
            throw new IllegalArgumentException("Point alreadyExist");
        }
    }
}
