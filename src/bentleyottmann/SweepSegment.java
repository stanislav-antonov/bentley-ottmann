package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Locale;
import java.util.Objects;

final public class SweepSegment {

    @NotNull
    final private Event e1;

    @NotNull
    final private Event e2;

    @NotNull
    final private Segment mSegment;

    private double mPosition;

    public SweepSegment(@NotNull Segment s) {
        mSegment = s;

        Event e1 = new Event(s.p1, this, Event.Type.POINT_LEFT);
        Event e2 = new Event(s.p2, this, Event.Type.POINT_RIGHT);
        if (!(Objects.compare(e2, e1, Event::compareTo) == 1)) {
            final Event swap = e1;
            e1 = e2; e2 = swap;
            e1.setType(Event.Type.POINT_LEFT);
            e2.setType(Event.Type.POINT_RIGHT);
        }

        this.e1 = e1;
        this.e2 = e2;

        updatePosition(leftEvent().x);
    }

    public double position() {
        return mPosition;
    }

    public void setPosition(double position) {
        mPosition = position;
    }

    @NotNull
    public Event leftEvent() {
        return e1;
    }

    @NotNull
    public Event rightEvent() {
        return e2;
    }

    @NotNull
    public Segment segment() {
        return mSegment;
    }

    // See: http://www.cs.swan.ac.uk/~cssimon/line_intersection.html
    @Nullable
    public static Point intersection(@NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        final double x1 = s1.leftEvent().x;
        final double y1 = s1.leftEvent().y;
        final double x2 = s1.rightEvent().x;
        final double y2 = s1.rightEvent().y;

        final double x3 = s2.leftEvent().x;
        final double y3 = s2.leftEvent().y;
        final double x4 = s2.rightEvent().x;
        final double y4 = s2.rightEvent().y;

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
        if (!(o instanceof SweepSegment)) {
            return false;
        }

        final SweepSegment s = (SweepSegment) o;
        return s.leftEvent().equals(leftEvent()) && s.rightEvent().equals(rightEvent());
    }

    @Override
    public int hashCode() {
        return 31 * leftEvent().hashCode() + rightEvent().hashCode();
    }

    @NotNull
    public String toString() {
        return String.format(Locale.getDefault(), "[%s : %s]", leftEvent(), rightEvent());
    }

    private void updatePosition(double x) {
        final double x1 = leftEvent().x;
        final double y1 = leftEvent().y;
        final double x2 = rightEvent().x;
        final double y2 = rightEvent().y;

        final double y = y1 + ( ((y2 - y1) * (x - x1)) / (x2 - x1) );
        this.setPosition(y);
    }
}