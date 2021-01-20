package org.example.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyMain extends JPanel implements ActionListener {
    public static final int SEGMENT_SIZE = 20;

    public JFrame frame;
    public Timer timer = new Timer(20, this);
    private Player player;
    private Apple apple;

    private int score = 0;

    private boolean IN_GAME = true;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 1080;

    public MyMain(JFrame frame) {
        this.frame = frame;
        this.timer.start();
        this.player = new Player(this);
        apple = new Apple();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.gray);
        for (int i = 0; i <= WIDTH / 20; i++) {
            g.drawLine(i * 20, 0, i * 20, HEIGHT);
        }
        for (int i = 0; i <= HEIGHT / 20; i++) {
            g.drawLine(0, i * 20, WIDTH, i * 20);
        }

        if (IN_GAME) {
            g.setColor(Color.red);
            g.fillRect(apple.getX(), apple.getY(), SEGMENT_SIZE, SEGMENT_SIZE);

            g.setColor(Color.green);
            Snake snake = player.getSnake();
            for (Snake.Segment seg : snake.getSegments()) {
                g.fillRect(seg.getX(), seg.getY(),
                        SEGMENT_SIZE, SEGMENT_SIZE);
            }

            g.setColor(Color.WHITE);
            g.fillRect(WIDTH, 0, 800, HEIGHT);
            Graphics2D eg = (Graphics2D) g;
            eg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            eg.setColor(Color.BLACK);
            eg.drawString("SCORE: " + score, WIDTH + 120, 120);
        } else {
            if (player.getMaxScore() < score) {
                player.setMaxScore(score);
            }
            score = 0;
            player.setSnake(new Snake());
            IN_GAME = true;
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (IN_GAME) {
            Snake snake = player.getSnake();
            snake.move();

            int snakeHead_x = snake.getSegments().get(0).x;
            int snakeHead_y = snake.getSegments().get(0).y;

            if (snakeHead_x + SEGMENT_SIZE > WIDTH) {
                snake.getSegments().get(0).x = 0;
            }
            if (snakeHead_x < 0) {
                snake.getSegments().get(0).x = WIDTH - SEGMENT_SIZE;
            }
            if (snakeHead_y + SEGMENT_SIZE > HEIGHT) {
                snake.getSegments().get(0).y = 0;
            }
            if (snakeHead_y < 0) {
                snake.getSegments().get(0).y = HEIGHT - SEGMENT_SIZE;
            }

            for (int i = 1; i < snake.getSegments().size(); i++) {
                if ((snakeHead_x + MyMain.SEGMENT_SIZE == snake.getSegments().get(i).x + MyMain.SEGMENT_SIZE && snakeHead_y == snake.getSegments().get(i).y)
                        || (snakeHead_x == snake.getSegments().get(i).x && snakeHead_y == snake.getSegments().get(i).y)
                        || (snakeHead_y + SEGMENT_SIZE == snake.getSegments().get(i).y + MyMain.SEGMENT_SIZE && snakeHead_x == snake.getSegments().get(i).x)
                        || (snakeHead_y == snake.getSegments().get(i).y && snakeHead_x == snake.getSegments().get(i).x)) {
//                    snake.setDirection(Direction.NONE);
                    IN_GAME = false;
                    break;
                }
            }

            if ((snakeHead_x + SEGMENT_SIZE == apple.getX() + SEGMENT_SIZE && snakeHead_y == apple.getY())
                    || (snakeHead_x == apple.getX() && snakeHead_y == apple.getY())
                    || (snakeHead_y + SEGMENT_SIZE == apple.getY() + SEGMENT_SIZE && snakeHead_x == apple.getX())
                    || (snakeHead_y == apple.getY() && snakeHead_x == apple.getX())) {
                player.getSnake().addSegment();
                score += 1;
                apple = new Apple();
            }
        }
        repaint();
    }
}