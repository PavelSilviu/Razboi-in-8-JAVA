package gamemodel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    private double x;
    private double y;
    private double OldX;
    private double OldY;

    private double radius;
    private Circle circle;

    public Piece(int x, int y, double radius, Circle c){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.circle=c;
    }

    public void setColor(Color color){
        circle.setFill(color);
    }

    public void draw(){
        circle.setRadius(radius);
        circle.setTranslateX(x);
        circle.setTranslateY(y);
    }

    public void memorateOldValues(double x, double y){
        OldX=x;
        OldY=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOldX() {
        return OldX;
    }

    public void setOldX(double OldX) {
        this.OldX = OldX;
    }

    public double getOldY() {
        return OldY;
    }

    public void setOldY(double OldY) {
        this.OldY = OldY;
    }
}
