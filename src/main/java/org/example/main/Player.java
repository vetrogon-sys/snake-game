package org.example.main;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;

@Getter
@Setter
public class Player {
    private String name;
    private int maxScore;

    private MyMain game;
    private Snake snake;

    public Player(MyMain game) {
        this.game = game;
        this.snake = new Snake();
    }

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                if (snake.getDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                snake.setDirection(Direction.NONE);
                break;
        }
    }
}
