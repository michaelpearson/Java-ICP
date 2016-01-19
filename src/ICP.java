import geometry.Plane;
import geometry.Point;
import geometry.Translation;

public interface ICP {
    Translation iterate(Point[] source, Plane[] target);
}
