import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

final public class SweepSegment extends Segment {

    private double mPosition;

    public SweepSegment(@NotNull Point p1, @NotNull Point p2) {
        super(p1, p2);
        if (this.p1.compareTo(this.p2) > 0) {
            final Point swap = this.p1;
            this.p1 = this.p2;
            this.p2 = swap;
        }
    }

    public SweepSegment(@NotNull Segment s) {
        this(s.p1, s.p2);
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
        return super.equals(s) && s.position() == position();
    }
}