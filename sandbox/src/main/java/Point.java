/**
 * Created by Michael on 25.09.2016.
 */
public class Point {
    double x;
    double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public static double lenCount(Point a, Point b){
        double len;
        len=Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y-b.y)*(a.y-b.y) );
        return len;
    }

}
