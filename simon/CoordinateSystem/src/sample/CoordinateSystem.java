package sample;

import java.util.SortedSet;
import java.util.TreeSet;

class CoordinateSystem {
    protected SortedSet<Point> points;
    protected SortedSet<Function> functions;
    protected double maxX;
    protected double maxY;
    protected double minDistanceOfPoints;

    CoordinateSystem(double maxX, double maxY, double minDistanceOfPoints) {
        this.maxX = maxX;
        this.maxY = maxY;
        points = new TreeSet<>();
        functions = new TreeSet<>();
        if (minDistanceOfPoints < 0) {
            throw new IllegalArgumentException("A minDistance that is under 0 makes no sense. Nevertheless it was tried to set it to " + minDistanceOfPoints);
        }
        this.minDistanceOfPoints = minDistanceOfPoints;
    }

    SortedSet<Point> getPoints() {
        return points;
    }

    void add(Point point) {
        if (minDistanceOfPoints > getMinDistanceToOtherPoints(point)) {
            throw new IllegalArgumentException("The point is too close to another point.");
        }
        if (point.getX() > maxX || point.getX() < -maxX || point.getY() > maxY || point.getY() < -maxY) {
            throw new IllegalArgumentException("The x and y values of the point are outside of the bounds.");
        }
        if (!points.add(point)) {
            throw new IllegalArgumentException("Point alreadyExist");
        }
    }

    void add(Function pn) {
        if (!functions.add(pn)) {
            throw new IllegalArgumentException("Function with this name already exists");
        }
    }

    double getMinDistanceToOtherPoints(Point newPoint) {
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point point : points) {
            double distance = newPoint.getDistanceTo(point);
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
