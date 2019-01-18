package sample;

public class Point implements Comparable<Point> {

    private double x;
    private double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }

    @Override
    public int compareTo(Point o) {
        if (x > o.getX()) {
            return 1;
        } else if (x < o.getX()) {
            return -1;
        } else {
            return Double.compare(getY(), o.getY());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Point.class) {
            Point point = (Point) obj;
            return compareTo(point) == 0;
        }
        return false;
    }

    public double getDistanceTo(Point point) {
        return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y -this.y, 2));
    }
}
