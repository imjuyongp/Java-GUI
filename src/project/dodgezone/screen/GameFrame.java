package project.dodgezone.screen;

import JFrame.graphics.DropBall;
import project.dodgezone.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    public GameFrame() {
        add(new GamePanel());
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class GamePanel extends JPanel {

        Player player = new Player(); // 플레이어 객체 생성

        public GamePanel() {
            setBackground(Color.BLACK);
            setLayout(null);

            setFocusable(true);
            requestFocusInWindow();

            // 키 이벤트 정의 (플레이어 이동)
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> player.moveUp();
                        case KeyEvent.VK_DOWN -> player.moveDown();
                        case KeyEvent.VK_LEFT -> player.moveLeft();
                        case KeyEvent.VK_RIGHT -> player.moveRight();
                    }
                    repaint(); // 이벤트 발생 후 해당 메서드 실행 후 그림을 다시 그림
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            player.paintImg(g); // 플레이어를 그림
        }
    }
}
