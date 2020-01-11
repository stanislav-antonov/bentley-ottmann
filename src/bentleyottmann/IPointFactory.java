package bentleyottmann;

import com.sun.istack.internal.NotNull;

public interface IPointFactory {
    @NotNull
    IPoint create(double x, double y);
}
