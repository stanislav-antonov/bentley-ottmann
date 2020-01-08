package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

final public class SweepSegment extends Segment {

    private double mPosition;

    public SweepSegment(@NotNull Point p1, @NotNull Point p2) {
        super(p1, p2);
        updatePosition(firstPoint().x);
    }

    public SweepSegment(@NotNull Segment s) {
        this(s.firstPoint(), s.secondPoint());
    }

    public double position() {
        return mPosition;
    }

    public void setPosition(double position) {
        mPosition = position;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (!(o instanceof SweepSegment)) {
            return false;
        }

        final SweepSegment s = (SweepSegment) o;
        return super.equals(s);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    void updatePosition(double x) {
        double x1 = firstPoint().x;
        double y1 = firstPoint().y;
        double x2 = secondPoint().x;
        double y2 = secondPoint().y;

        double y = y1 + ( ((y2 - y1) * (x - x1)) / (x2 - x1) );
        this.setPosition(y);
    }
}