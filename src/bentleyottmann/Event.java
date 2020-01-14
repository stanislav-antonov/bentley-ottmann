package bentleyottmann;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

final class Event implements Comparable<Event> {
    enum Type {
        POINT_LEFT, POINT_RIGHT, INTERSECTION
    }

    final private static double EPSILON = 1E-9;

    @NotNull
    private Type mType;

    @NotNull
    final private IPoint mPoint;

    @NotNull
    final private List<SweepSegment> mSegments = new ArrayList<>();

    Event(@NotNull IPoint p, @NotNull SweepSegment s1, @NotNull Type type) {
        mPoint = p;
        mType = type;
        mSegments.add(s1);
    }

    Event(@NotNull IPoint p, @NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        this(p, s1, Type.INTERSECTION);
        mSegments.add(s2);

        // Ensure s1 is always above s2
        if (!(mSegments.get(0).position() > mSegments.get(1).position())) {
            Collections.swap(mSegments, 0, 1);
        }
    }

    void setType(@NotNull Type type) {
        mType = type;
    }

    @NotNull
    Type type() {
        return mType;
    }

    @NotNull
    IPoint point() {
        return mPoint;
    }

    @NotNull
    SweepSegment firstSegment() {
        return mSegments.get(0);
    }

    @NotNull
    SweepSegment secondSegment() {
        return mSegments.get(1);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "[%s, %s]", point().x(), point().y());
    }

    @Override
    public int compareTo(@NotNull Event e) {
        if (e.point().x() < point().x() ||
                (nearlyEqual(e.point().x(), point().x()) && e.point().y() < point().y())) {
            return 1;
        }

        if (e.point().x() > point().x() ||
                (nearlyEqual(e.point().x(), point().x()) && e.point().y() > point().y())) {
            return -1;
        }

        return 0;
    }

    boolean nearlyEqual(@NotNull Event e) {
        return nearlyEqual(point().x(), e.point().x()) && nearlyEqual(point().y(), e.point().y());
    }

    // Taken from: https://floating-point-gui.de/errors/comparison/
    private static boolean nearlyEqual(double a, double b) {
        final double absA = Math.abs(a);
        final double absB = Math.abs(b);
        final double diff = Math.abs(a - b);

        if (a == b) { // shortcut, handles infinities
            return true;
        } else if (a == 0 || b == 0 || (absA + absB < Double.MIN_NORMAL)) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (Event.EPSILON * Double.MIN_NORMAL);
        } else { // use relative error
            return diff / Math.min((absA + absB), Double.MAX_VALUE) < Event.EPSILON;
        }
    }
}
