public class MyFirstProgram {
    public static void main(String[] args){
        System.out.println("Hello, world");
        Point d = new Point(10, 10);
        System.out.println("Создана точка с координатами "+ d.x + " и " + d.y);
        Point v = new Point(5, 11);
        System.out.println("Создана точка с координатами "+ v.x + " и " + v.y);
        Point z = new Point();
        System.out.println("Создана точка с координатами "+ z.x + " и " + z.y);
        d.distance(v);
        d.distance(z);
        v.distance(z);
        v.distance(d);

    }

}
