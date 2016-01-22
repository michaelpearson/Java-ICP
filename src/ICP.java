import geometry.Plane;
import geometry.Point;
import geometry.Transformation;

public interface ICP {
    Transformation iterate(Point[] source, Plane[] target);
}
