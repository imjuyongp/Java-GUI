package project.dodgezone.game;

import java.awt.*;

public class ObstacleDown extends Obstacle{

    public ObstacleDown() {
        this.x = (int)(Math.random() * 480); // 화면 너비 고려
        this.y = 500;
    }

    // 장애물을 그리는 메서드
    @Override
    public void paintObstacle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 10, 10);
    }

    // 장애물을 아래로 이동시키는 메서드
    @Override
    public void move() {
        y -= 5; // 위로 5씩 이동
    }

    // 화면 밖으로 나갔는지 확인
    @Override
    public boolean isOutOfScreen() {
        return y < 0;
    }
}
