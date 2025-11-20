package project.dodgezone.game;

import java.awt.*;

public class ObstacleLeft {

    // 왼쪽 모서리 랜덤 좌표 값 생성
    private int x;
    private int y;

    public ObstacleLeft() {
        this.x = 0;
        this.y = (int)(Math.random() * 480);
    }

    // 장애물을 그리는 메서드
    public void paintObstacle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 10, 10);
    }

    // 장애물을 아래로 이동시키는 메서드
    public void move() {
        x += 5; // 위로 5씩 이동
    }

    // 화면 밖으로 나갔는지 확인
    public boolean isOutOfScreen() {
        return x > 500;
    }
}
