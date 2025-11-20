package project.dodgezone.game;

import java.awt.*;

public class ObstacleRight {

    // 오른쪽 모서리 랜덤 좌표 값 생성
    private int x;
    private int y;

    public ObstacleRight() {
        this.x = 500;
        this.y = (int)(Math.random() * 480);
    }

    // 장애물을 그리는 메서드
    public void paintObstacle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 10, 10);
    }

    // 장애물을 왼쪽으로 이동시키는 메서드
    public void move() {
        x -= 5; // 아래로 5씩 이동
    }

    // 화면 밖으로 나갔는지 확인
    public boolean isOutOfScreen() {
        return x < 0;
    }
}
