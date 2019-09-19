import java.awt.*;

class Paddle {

    private int y;
    private final int X;
    private final int WIDTH = 15;
    private final int HEIGHT = 75;
    private final int SPEED = 7;
    private int direction;
    private final int aiBuffer = 5;
    private int score = 0;

    Paddle(int x) {
        this.X = x;
        this.y = 275;
    }

    int getScore() {
        return score;
    }

    int getY() {
        return y;
    }

    int getX() {
        return X;
    }

    int getWIDTH() {
        return WIDTH;
    }

    int getHEIGHT() {
        return HEIGHT;
    }

    void playerMove() {
        switch (direction) {
            case 1:
                y -= SPEED;
                break;
            case -1:
                y += SPEED;
                break;
        }
    }

    void addScore() {
        score++;
    }

    void aiMove(Ball b, char side) {
        if(side == 'l' && b.getxSpeed() > 0 ) return; // if ball heading away
        if(side == 'r' && b.getxSpeed() < 0 ) return;
        if (b.getY() < this.y + aiBuffer) {
            y -= SPEED;
        } else if (b.getY() > this.y + this.HEIGHT - aiBuffer) {
            y += SPEED;
        }
    }


    void setDirection(int d) {
        direction = d;
    }

    void render(Graphics g) {
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
