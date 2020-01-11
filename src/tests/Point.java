package tests;

import bentleyottmann.IPoint;

class Point implements IPoint {
    final private double x;
    final private double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }
}
