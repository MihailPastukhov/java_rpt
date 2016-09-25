public class MyFirstProgram {
    public static void main(String[] args){
        System.out.println("Hello, world");
        Point d = new Point(5, 10);
        System.out.println("Создана точка с координатами "+ d.x + " и " + d.y);
        Point v = new Point(15, 19);
        System.out.println("Создана точка с координатами "+ v.x + " и " + v.y);
        System.out.println("Расстояние между двумя точками, введенными ранее = " + Point.lenCount(d,v));
    }

}
