package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args){
        System.out.println("Hello, world");
        Point a = new Point(5, 10);
        Point b = new Point(10, 10);
        System.out.println("Создана точка с координатами "+ a.x + " и " + a.y);

        System.out.println("Создана точка с координатами "+ b.x + " и " + b.y);

        Point z = new Point();
        System.out.println("Создана точка с координатами "+ z.x + " и " + z.y);
        a.distance(b);
        a.distance(z);
        b.distance(z);
        b.distance(a);
    }

}
