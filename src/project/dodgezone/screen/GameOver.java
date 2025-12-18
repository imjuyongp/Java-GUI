package project.dodgezone.screen;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    private JButton restartButton;
    private JButton exitButton;

    public GameOver() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 200)); // 반투명 검은색 배경

        // 중앙 패널 (메시지와 버튼들)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // 게임 오버 메시지
        JLabel gameOverLabel = new JLabel("게임 종료");
        gameOverLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        // 재시작 버튼
        restartButton = new JButton("재시작");
        restartButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        restartButton.setPreferredSize(new Dimension(120, 40));
        restartButton.setFocusPainted(false);
        restartButton.setBackground(new Color(70, 130, 180)); // 기본 색상

        // 종료 버튼
        exitButton = new JButton("게임 종료");
        exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(120, 40));
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(220, 20, 60)); // 기본 색상

        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);

        // 컴포넌트 배치
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(gameOverLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
