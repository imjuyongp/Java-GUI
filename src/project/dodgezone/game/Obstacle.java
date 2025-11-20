package project.dodgezone.game;

import java.awt.*;

public abstract class Obstacle {
    // 장애물 생성 좌표
    protected int x;
    protected int y;

    // 장애물을 그리는 메서드
    public abstract void paintObstacle(Graphics g);

    // 장애물을 아래로 이동시키는 메서드
    public abstract void move();

    // 화면 밖으로 나갔는지 확인
    public abstract boolean isOutOfScreen();
}
