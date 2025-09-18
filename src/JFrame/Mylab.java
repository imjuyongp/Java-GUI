package JFrame;

import javax.swing.*;
import java.awt.*;

public class Mylab extends JFrame {
  // 접근 제어자 설정 x -> default 접근제어자로 설정(패키지 내부에서만 사용 가능)
  JPanel p;
  JLabel l1, l2;
  JTextField t1, t2;
  JButton b;

  public Mylab() {
    // 온도변환기 (배치관리자x)
    p = new JPanel();
    this.add(p); // JFrame에 패널을 넣음 (Mylab은 JFrame을 상속 받기 때문에 JFrame을 상속 받은 Mylab에다가 추가(this -> Mylab(JFrame))
    l1 = new JLabel("화씨 온도");
    l2 = new JLabel("섭씨 온도");
    t1 = new JTextField(15);
    t2 = new JTextField(15);
    b = new JButton("convert");

    p.add(l1);
    p.add(l2);
    p.add(t1);
    p.add(t2);
    p.add(b);

    Toolkit kit = Toolkit.getDefaultToolkit(); // 도구 모음
    Dimension screenSize = kit.getScreenSize(); // 현재 화면의 크기를 얻음

    setSize(300,150);
    setTitle("온도변환기");
    setLocation(screenSize.width / 2, screenSize.height / 2); // 화면의 중앙
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
