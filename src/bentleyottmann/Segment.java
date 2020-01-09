package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Locale;

public class Segment {
    @NotNull
    final private Point p1;

    @NotNull
    final private Point p2;

    @NotNull
    final private String mName;

    public Segment(@NotNull Point p1, @NotNull Point p2) {
        this(p1, p2, null);
    }

    public Segment(@NotNull Point p1, @NotNull Point p2, @Nullable String name) {
        // TODO: if 'x' coordinates are equal, then need to compare by 'y' afterwards
        if (!(p2.x > p1.x)) {
            Point swap = p1;
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
        final double x1 = s1.firstPoint().x;
        final double y1 = s1.firstPoint().y;
        final double x2 = s1.secondPoint().x;
        final double y2 = s1.secondPoint().y;

        final double x3 = s2.firstPoint().x;
        final double y3 = s2.firstPoint().y;
        final double x4 = s2.secondPoint().x;
        final double y4 = s2.secondPoint().y;

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
        return s.firstPoint().equals(firstPoint()) && s.secondPoint().equals(secondPoint());
    }

    @Override
    public int hashCode() {
        return 31 * firstPoint().hashCode() + secondPoint().hashCode();
    }

    @NotNull
    public final String toString() {
        return String.format(Locale.getDefault(), "%s [%s : %s]", name(), firstPoint(), secondPoint());
    }

    @Nullable
    public String name() {
        return mName;
    }

    @NotNull
    public Point firstPoint() {
        return p1;
    }

    @NotNull
    public Point secondPoint() {
        return p2;
    }
}