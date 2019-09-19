import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

class Ball {

    private final Random rand = new Random();

    private int x, y, xSpeed, ySpeed;
    private final int maxYSpeed = 7;
    private final int RADIUS = 7;

    Ball() {
        reset();
    }

    private void setySpeed(int i) {
        this.ySpeed = i;
    }

    public int getxSpeed() { return this.xSpeed;}

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getRadius() {
        return RADIUS;
    }

    void bouncePaddle(Paddle p) {
        System.out.println("Y: " + this.y); //
        System.out.println("Paddle Y: " + p.getY()); //
        System.out.println("Paddle center Y: " + p.getHEIGHT() / 2); //
        int distance = this.y - (p.getY() + p.getHEIGHT() / 2);
        System.out.println("Distance: " + distance);  //
        ySpeed += distance / 6;
        System.out.println("YSpeed: " + ySpeed); //
        if (ySpeed > maxYSpeed) {
            ySpeed = maxYSpeed;
        }
        if (ySpeed < -maxYSpeed) {
            ySpeed = -maxYSpeed;
        }
        xSpeed = -xSpeed;
    }

    public void reset() {
        if (this.x <= 400) {
            xSpeed = 8;
        } else {
            xSpeed = -8;
        }
        setySpeed(rand.nextInt(4) - 2);
        this.x = 400;
        this.y = 300;
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
