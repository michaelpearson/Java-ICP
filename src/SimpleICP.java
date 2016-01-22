import geometry.Plane;
import geometry.Point;
import geometry.Transformation;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.SingularValueDecomposition;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;


public class SimpleICP implements ICP {
    @Override
    public Transformation iterate(Point[] source, Plane[] target) {


        Point[] targetPoints = new Point[source.length];

        int a = 0;
        for(Point s : source) {
            targetPoints[a++] = Scene.findClosestPlane(target, s);
        }


        return findRigidTransformation(source, targetPoints);
    }





    public Transformation findRigidTransformation(Point[] source, Point[] target) {
        if(source.length != target.length) {
            throw new RuntimeException("Points do not correspond");
        }

        double[][] m1 = new double[source.length][2], m2 = new double[source.length][2];
        for(int a = 0;a < source.length;a++) {
            m1[a][0] = source[a].getX();
            m1[a][1] = source[a].getY();
            m2[a][0] = source[a].getX();
            m2[a][1] = source[a].getY();
        }

        RealMatrix sourceMatrix = MatrixUtils.createRealMatrix(m1);
        RealMatrix destMatrix = MatrixUtils.createRealMatrix(m2);





        return null;
    }
}
