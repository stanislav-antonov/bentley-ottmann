import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Point implements Comparable<Point> {
    final public double x;
    final public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double angleOf(@NotNull Point p1, @NotNull Point p2) {
        final double dy = p2.y - p1.y;
        final double dx = p2.x - p1.x;
        final double result = Math.toDegrees(Math.atan2(dy, dx));

        return (result < 0) ? (360.0 + result) : result;
    }

    @Override
    public int compareTo(@NotNull Point p2) {
        if (y > p2.y || (y == p2.y && x < p2.x)) {
            return -1;
        }

        if (y < p2.y || (y == p2.y && x > p2.x)) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        Point p = (Point) o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return 31 * Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
    }

    @Override
    public final String toString() {
        return "[" + x + ", " + y + "]";
    }
}
