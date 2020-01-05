import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Segment {
    @NotNull
    public Point p1;

    @NotNull
    public Point p2;

    public Segment(@NotNull Point p1, @NotNull Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // See: http://www.cs.swan.ac.uk/~cssimon/line_intersection.html
    @Nullable
    public static Point intersection(@NotNull Segment s1, @NotNull Segment s2) {
        final double x1 = s1.p1.x;
        final double y1 = s1.p1.y;
        final double x2 = s1.p2.x;
        final double y2 = s1.p2.y;

        final double x3 = s2.p1.x;
        final double y3 = s2.p1.y;
        final double x4 = s2.p2.x;
        final double y4 = s2.p2.y;

        final double v = (x4 - x3) * (y1 - y2) - (x1 - x2) * (y4 - y3);
        if (v == 0) {
            return null;
        }

        final double ta = ((y3 - y4) * (x1 - x3) + (x4 - x3) * (y1 - y3)) / v;
        final double tb = ((y1 - y2) * (x1 - x3) + (x2 - x1) * (y1 - y3)) / v;

        if ((ta >= 0.0f && ta <= 1.0f) && (tb >= 0.0f && tb <= 1.0f)) {
            final double px = x1 + ta * (x2 - x1);
            final double py = y1 + ta * (y2 - y1);

            return new Point(px, py);
        }

        return null;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (!(o instanceof Segment)) {
            return false;
        }

        Segment s = (Segment) o;
        return s.p1.equals(p1) && s.p2.equals(p2);
    }

    @NotNull
    public final String toString() {
        return "[" + p1 + " : " + p2 + "]";
    }
}