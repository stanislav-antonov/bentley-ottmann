package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public interface ISegment {
    @NotNull
    IPoint p1();

    @NotNull
    IPoint p2();

    @Nullable
    String name();
}
