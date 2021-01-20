package org.example.main;

import lombok.Getter;

@Getter
public class Apple {
    private int x;
    private int y;

    public Apple() {
        this.x = (int) (Math.random() * MyMain.WIDTH);
        while (x % MyMain.SEGMENT_SIZE != 0) {
            this.x = (int) (Math.random() * (MyMain.WIDTH - MyMain.SEGMENT_SIZE));
        }
        this.y = (int) (Math.random() * MyMain.HEIGHT);
        while (y % MyMain.SEGMENT_SIZE != 0) {
            this.y = (int) (Math.random() * ( MyMain.HEIGHT - MyMain.SEGMENT_SIZE ));
        }

    }
}
