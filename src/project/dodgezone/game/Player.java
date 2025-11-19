package project.dodgezone.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player {

    BufferedImage img;
    private int x = 250;
    private int y = 250;

    public Player () {
        try {
            img = ImageIO.read(new File("/Users/parkjuyong/Desktop/4-1/JavaApplication/javaapplication-practice/src/project/assets/player.png"));
        } catch (Exception e) {
            System.out.println("이미지 불러오기에 실패했습니다.");
        }
    }

    // 플레이어의 그림을 그려주는 메서드
    public void paintImg(Graphics g) {
        g.drawImage(img, x, y,30,30, null); // 이미지를 x,y 좌표에 그리고 이미지 크기를 조정함
    }


    // 플레이어를 이동시키는 메서드 (좌표 업데이트)
    public void moveLeft() {
        if (x > 0) {  // 왼쪽 경계 체크
            x -= 10;
        }
    }

    public void moveRight() {
        if (x < 470) {  // 오른쪽 경계 체크
            x += 10;
        }
    }

    public void moveUp() {
        if (y > 0) {  // 위쪽 경계 체크
            y -= 10;
        }
    }

    public void moveDown() {
        if (y < 470) {  // 아래쪽 경계 체크
            y += 10;
        }
    }
}
