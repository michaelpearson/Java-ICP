import geometry.Plane;
import geometry.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] argv) throws IOException, ParseException {
        new Main(argv);
    }


    public Main(String[] arguments) throws IOException, ParseException {
        JSONObject configuration = (JSONObject) new JSONParser().parse(new FileReader(new File(arguments[0])));

        Plane[] planes = Plane.fromJson((JSONArray)((JSONObject)configuration.get("target")).get("planes"));

        List<Point> pointArrayList = new ArrayList<>();
        for(Plane p : planes) {
            Point[] points = Point.generatePointPlane(p, 0.3, 4);
            pointArrayList.addAll(Arrays.asList(points));
        }

        Point[] points = new Point[pointArrayList.size()];
        pointArrayList.toArray(points);

        Point.applyTranslation(points, 10, 30);
        Point.applyRotation(points, new Point(250, 250), 45 * (Math.PI / 180));

        new Scene("Sparse ICP test", planes, points);
    }
}
