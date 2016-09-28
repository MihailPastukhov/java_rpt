package ru.stqa.pft.sandbox;

/**
 * Created by Michael on 25.09.2016.
 */
public class Point {
    double x;
    double y;
    Point(){
        this.x = 0;
        this.y = 0;
    }

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public boolean isEqual(Point a){
        if ((this.x==a.x)&&(this.y==a.y)){
        return true;
        }
        else return false;
        }
     public boolean isZero(Point a){
        if (((this.x==0)&&(this.y==0))||((a.x==0)&&(a.y==0))){
            return true;
        }
        else return false;
    }

 public void distance(Point a){
        double len;
        if (isEqual(a))
            System.out.println("Точки имеют одинаковые координаты");
        else {
            if (isZero(a)){
                System.out.println("Расстояние от начала координат до точки = " + Math.sqrt(Math.pow((this.x - a.x),2) + Math.pow((this.y - a.y),2)));
                }
            else {
                System.out.println("Расстояние между двумя точками, введенными ранее = " + Math.sqrt(Math.pow((this.x - a.x),2) + Math.pow((this.y - a.y),2)));
                }
            }

                    //return len;
                        }

}
