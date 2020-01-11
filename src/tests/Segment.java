package tests;

import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import com.sun.istack.internal.NotNull;

class Segment implements ISegment {
    @NotNull
    final private IPoint p1;

    @NotNull
    final private IPoint p2;

    Segment(@NotNull IPoint p1, @NotNull IPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public IPoint p1() {
        return p1;
    }

    @Override
    public IPoint p2() {
        return p2;
    }
}