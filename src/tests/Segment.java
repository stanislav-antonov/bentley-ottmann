package tests;

import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

class Segment implements ISegment {
    @NotNull
    final private IPoint p1;

    @NotNull
    final private IPoint p2;

    @Nullable
    final private String mName;

    Segment(@NotNull IPoint p1, @NotNull IPoint p2) {
        this(p1, p2, null);
    }

    Segment(@NotNull IPoint p1, @NotNull IPoint p2, @Nullable String name) {
        this.p1 = p1;
        this.p2 = p2;
        mName = name;
    }

    @Override
    public IPoint p1() {
        return p1;
    }

    @Override
    public IPoint p2() {
        return p2;
    }

    @Override
    public String name() {
        return mName;
    }
}