import java.awt.*;

public class Paddle {

    private int y;
    private final int X;
    private final int WIDTH = 15;
    private final int HEIGHT = 75;
    private final int SPEED = 5;
    private int direction;
    private final int aiBuffer = 5;
    private int score = 0;

    public Paddle(int x) {
        this.X = x;
        this.y = 275;
    }

    public int getScore() {
        return score;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return X;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void playerMove() {
        switch (direction) {
            case 1:
                y -= SPEED;
                break;
            case -1:
                y += SPEED;
                break;
        }
    }

    public void addScore() {
        score++;
    }

    public void aiMove(Ball b) {
        if (b.getY() < this.y + aiBuffer) {
            y -= SPEED;
        } else if (b.getY() > this.y + this.HEIGHT - aiBuffer) {
            y += SPEED;
        }
    }


    public void setDirection(int d) {
        direction = d;
    }

    public void render(Graphics g) {
        if(this.y < 0){ // checks its legal movement
            this.y = 0;
        }
        if(this.y + this.HEIGHT > 600) {
            this.y = 600 - this.HEIGHT;
        }
        g.setColor(Color.white);
        g.fillRect(this.X, this.y, WIDTH, HEIGHT);
    }
}
