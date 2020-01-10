package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Locale;
import java.util.Objects;

public class Segment {
    @NotNull
    final private Point p1;

    @NotNull
    final private Point p2;

    @Nullable
    final private String mName;

    public Segment(@NotNull Point p1, @NotNull Point p2) {
        this(p1, p2, null);
    }

    public Segment(@NotNull Point p1, @NotNull Point p2, @Nullable String name) {
        if (!(Objects.compare(p2, p1, Point::compareTo) == 1)) {
            final Point swap = p1;
            p1 = p2;
            p2 = swap;
        }

        this.p1 = p1;
        this.p2 = p2;

        this.mName = name;
    }

    // See: http://www.cs.swan.ac.uk/~cssimon/line_intersection.html
    @Nullable
    public static Point intersection(@NotNull Segment s1, @NotNull Segment s2) {
        final double x1 = s1.leftPoint().x;
        final double y1 = s1.leftPoint().y;
        final double x2 = s1.rightPoint().x;
        final double y2 = s1.rightPoint().y;

        final double x3 = s2.leftPoint().x;
        final double y3 = s2.leftPoint().y;
        final double x4 = s2.rightPoint().x;
        final double y4 = s2.rightPoint().y;

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

        final Segment s = (Segment) o;
        return s.leftPoint().equals(leftPoint()) && s.rightPoint().equals(rightPoint());
    }

    @Override
    public int hashCode() {
        return 31 * leftPoint().hashCode() + rightPoint().hashCode();
    }

    @NotNull
    public String toString() {
        return String.format(Locale.getDefault(), "%s [%s : %s]", name(), leftPoint(), rightPoint());
    }

    @Nullable
    public String name() {
        return mName;
    }

    @NotNull
    public Point leftPoint() {
        return p1;
    }

    @NotNull
    public Point rightPoint() {
        return p2;
    }
}