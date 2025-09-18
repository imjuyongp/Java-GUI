package JFrame;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
  public MyFrame() {
    setSize(300 ,200); // JFrame 크기 설정
    setTitle("MyFrame"); // JFrame title 다시 설정 (제목이라고 생각)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close 버튼 누르면 종료되도록
    setVisible(true); // 프레임을 화면에 나타나도록

    setLayout(new FlowLayout());
    add(new JButton("ok")); // java.swing 패키지에 정의되어있음
    add(new JButton("cancel"));
  }
}
