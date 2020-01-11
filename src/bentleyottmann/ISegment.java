package bentleyottmann;

import com.sun.istack.internal.NotNull;

public interface ISegment {
    @NotNull
    IPoint p1();

    @NotNull
    IPoint p2();
}
