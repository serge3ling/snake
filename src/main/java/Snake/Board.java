package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author tret
 */
public class Board extends JPanel implements ActionListener {
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int DOT_CNT = 900;
    private final int RANDOM_DOT = 29;
    private final int DELAY = 140;

    private final int x[] = new int[DOT_CNT];
    private final int y[] = new int[DOT_CNT];

    private int dots;
    private int foodX;
    private int foodY;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean playing = true;

    private Timer timer;
    private Image ball;
    private Image food;
    private Image head;
    
    private Conf conf;

    public Board(Conf conf) {
        this.conf = conf;

        addKeyListener(new BoardKeyAdapter());
        setBackground(Color.darkGray);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("food.png");
        food = iia.getImage();

        ImageIcon iih = new ImageIcon("head.png");
        head = iih.getImage();
    }

    public void initGame() {
        directionsFalse();
        right = true;
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        putFood();

        timer = new Timer(DELAY, this);
        timer.start();
        
        clickMouse();
    }
    
    private void clickMouse() {
        try {
            java.awt.Point point = javax.swing.SwingUtilities.convertPoint(
                    this, this.getX(), this.getY(), this);
            java.awt.Robot robot = new java.awt.Robot();
            robot.mouseMove(point.x, point.y);
            robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        } catch (java.awt.AWTException e) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        if (playing) {
            g.drawImage(food, foodX, foodY, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        /*String msg = "Гра закінчена.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);*/
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                playing = true;
                RecordFrame recordFrame = new RecordFrame(conf, dots);
                recordFrame.setVisible(true);
            }
        });
    }

    private void checkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            dots++;
            putFood();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) {
            x[0] -= DOT_SIZE;
        }

        if (right) {
            x[0] += DOT_SIZE;
        }

        if (up) {
            y[0] -= DOT_SIZE;
        }

        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                playing = false;
            }
        }

        if ((y[0] >= HEIGHT) || (y[0] < 0) || (x[0] >= WIDTH) || (x[0] < 0)) {
            playing = false;
        }
        
        if(!playing) {
            timer.stop();
        }
    }

    private void putFood() {
        int r = (int) (Math.random() * RANDOM_DOT);
        foodX = r * DOT_SIZE;

        r = (int) (Math.random() * RANDOM_DOT);
        foodY = r * DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playing) {
            checkFood();
            checkCollision();
            move();
        }

        repaint();
    }

    private class BoardKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                directionsFalse();
                left = true;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                directionsFalse();
                right = true;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                directionsFalse();
                up = true;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                directionsFalse();
                down = true;
            }
        }
    }
    
    private void directionsFalse() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
