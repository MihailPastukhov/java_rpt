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

    public double distance(Point a){
        double dist=0;
        if (isEqual(a))
            dist=0;

        else{
            dist=Math.sqrt(Math.pow((this.x - a.x),2) + Math.pow((this.y - a.y),2));
            if (isZero(a)){
                System.out.println("Расстояние от начала координат до точки = " + dist);
            }
            else {
                System.out.println("Расстояние между двумя точками, введенными ранее = " + dist);
            }
        }
        return dist;
    }

}
