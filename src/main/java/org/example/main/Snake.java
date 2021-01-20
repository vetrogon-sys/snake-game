package org.example.main;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    @Setter
    @Getter
    private Direction direction = Direction.RIGHT;
    private int speed;
    @Getter
    private List<Segment> segments = new ArrayList<>();

    public Snake() {
        speed = 20;
        segments.add(new Segment(160, 100));
    }

    public void move() {
        if (direction != Direction.NONE) {
            for (int i = segments.size() - 1; i > 0; i--) {
                segments.get(i).x = segments.get(i - 1).x;
                segments.get(i).y = segments.get(i - 1).y;
            }
        }

        switch (direction) {
            case RIGHT:
                segments.get(0).x += speed;
                break;
            case LEFT:
                segments.get(0).x -= speed;
                break;
            case DOWN:
                segments.get(0).y += speed;
                break;
            case UP:
                segments.get(0).y -= speed;
                break;
            case NONE:
                break;
        }
    }

    public void addSegment() {
        Segment lastSeg = segments.get(segments.size() - 1);
        segments.add(new Segment(lastSeg.getX(), lastSeg.getY()));
    }

    @Getter
    public class Segment {
        public int x;
        public int y;

        public Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
