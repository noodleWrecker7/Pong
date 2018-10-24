import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Ball {

    private static Random rand = new Random();

    private final int RADIUS;
    private int x, y, xSpeed, ySpeed;
    private final int maxYSpeed = 6;


    public Ball(int r) {
        this.RADIUS = r;
        /*this.x = 400; // center of ball
        this.y = 300;
        this.xSpeed = -7;
        this.ySpeed = 0;*/
        reset();
    }

    public void setySpeed(int i) {
        this.ySpeed = i;
    }


    public void setX(int i) {
        this.x = i;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return RADIUS;
    }

    public void bouncePaddle(Paddle p) {
        System.out.println("Y: " +this.y); //
        System.out.println("Paddle Y: " + p.getY()); //
        System.out.println("Paddle center Y: " + p.getHEIGHT()/2); //
        int distance = this.y - (p.getY() + p.getHEIGHT() / 2);
        System.out.println("Distance: "+distance);  //
        ySpeed += distance / 6;
        System.out.println("YSpeed: " +ySpeed); // 
        if (ySpeed > maxYSpeed) {
            ySpeed = maxYSpeed;
        } if (ySpeed < -maxYSpeed) {
            ySpeed = -maxYSpeed;
        }
        xSpeed = -xSpeed;
    }

    public void reset(){
        if(this.x <= 400) {
            xSpeed = 8;
        } else {
            xSpeed = -8;
        }
        setySpeed(rand.nextInt(4)-2);
        setX(400);
        setY(300);
    }

    public void bounceEdge(int height) {
        if (getY() < 0 || getY() > height) {
            ySpeed = -ySpeed;
        }
    }

    private void move() {
        x += xSpeed;
        y += ySpeed;
    }


    public void render(Graphics g) {
        move();
        g.setColor(Color.white);
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
    }
}
