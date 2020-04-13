import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

class Game extends Canvas {

    private static final int FPS = 60;

    private static boolean inGame = false;

    private static final Game GAME = new Game(800, 600);
    private static final Ball BALL = new Ball();
    private static Paddle leftPaddle, rightPaddle;

    private Game(int w, int h) {
        setSize(w, h);
        leftPaddle = new Paddle(10);
        rightPaddle = new Paddle(770);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        int dashWidth = 30;
        int dashGap = 10;
        for (int i = 0; i <= getHeight() / (dashGap + dashWidth); i++) {
            g.fillRect(400, i * (dashGap + dashWidth), 1, dashWidth);
        }
        if (!inGame) {
            g.drawString("PRESS ANY KEY TO BEGIN", 350, 200);
            return;
        }

        //leftPaddle.aiMove(BALL, 'l');
        leftPaddle.playerMove();
        leftPaddle.render(g);

        rightPaddle.aiMove(BALL, 'r');
        //rightPaddle.playerMove();
        rightPaddle.render(g);
        BALL.bounceEdge(getHeight());
        BALL.render(g);
        checkPaddleHit();
        checkFail();
        g.drawString(Integer.toString(leftPaddle.getScore()), 200, 100);
        g.drawString(Integer.toString(rightPaddle.getScore()), 600, 100);
    }

    @Override
    public void update(Graphics g) {
        Graphics offgc;
        Image offscreen;

        offscreen = createImage(getWidth(), getHeight());
        offgc = offscreen.getGraphics();

        paint(offgc);

        g.drawImage(offscreen, 0, 0, this);
    }

    private void checkFail() {
        int bx = BALL.getX();
        if (bx < 0) {
            BALL.reset();
            rightPaddle.addScore();
        } else if (bx > getWidth()) {
            leftPaddle.addScore();
            BALL.reset();
        }
    }

    private void checkPaddleHit() {
        Ball b = BALL; // quicker to type
        int bx = b.getX();
        int by = b.getY();
        int br = b.getRadius();
        Paddle lp = leftPaddle;
        Paddle rp = rightPaddle;

        if (bx - br < lp.getX() + lp.getWIDTH() && by > lp.getY() && by < lp.getY() + lp.getHEIGHT()) {
            b.bouncePaddle(leftPaddle);
        }
        if (bx + br > rp.getX() && by > rp.getY() && by < rp.getY() + rp.getHEIGHT()) {
            b.bouncePaddle(rightPaddle);
        }
    }

    private void keyDown(int e) {
        int totalMove = 0;
        System.out.println(e);
        // LEFT PADDLE PLAYER MOVEMENT ARROW KEYS
        if (e == 87) {
            totalMove++;
        }
        if (e == 83) {
            totalMove--;
        }
        if (totalMove != 0) {
            leftPaddle.setDirection(totalMove);
            return;
        }

        // RIGHT PADDLE PLAYER MOVEMENT WASD

        if (e == 38) {
            totalMove++;
        }
        if (e == 40) {
            totalMove--;
        }
        if (totalMove != 0) {
            rightPaddle.setDirection(totalMove);
            return;
        }


    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Adam's Pong");
        frame.setResizable(false);
        frame.add(GAME);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GAME.createBufferStrategy(3);
        GAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // irrelevant
            }

            @Override
            public void keyPressed(KeyEvent e) {
                GAME.keyDown(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!inGame) inGame = true;
                int code = e.getKeyCode();
                switch (code) {
                    case 83:
                    case 87:
                        leftPaddle.setDirection(0);
                        break;
                    case 38:
                    case 40:
                        rightPaddle.setDirection(0);
                }

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GAME.repaint();
            }
        }, 1000, 1000 / FPS);
    }
}
