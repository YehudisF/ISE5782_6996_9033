package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 *
 */
public class Triangle extends Polygon implements FlatGeometry{


    /**
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3)
    {
        super(p1, p2, p3);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        //
         List<GeoPoint> result = plane.findGeoIntersections(ray);

         if(result == null){
             return null;
         }

         //checking that the intersection point is bounded by traingle vertices
         Point p0 = ray.getP0();
         Vector v = ray.getDir();

         Point p1 = vertices.get(0);
         Point p2 = vertices.get(1);
         Point p3 = vertices.get(2);

        Vector v1 = p1.subtract(p0);// p0->p1
        Vector v2 = p2.subtract(p0);// p0->p2
        Vector v3 = p3.subtract(p0);//p0->p3

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        double s1 = n1.dotProduct(v);
        double s2 = n2.dotProduct(v);
        double s3 = n3.dotProduct(v);

        if(s1> 0 && s2 > 0 && s3 > 0 ||  s1 < 0 && s2< 0 && s3 < 0)
            return List.of(new GeoPoint(this,result.get(0).point));

        return null;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }


}
