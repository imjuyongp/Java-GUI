package homework.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstHomework extends JFrame implements ActionListener {

  JPanel p;
  JTextField t1, t2;
  JButton equal;
  JRadioButton plusRadio, minusRadio;
  ButtonGroup operatorGroup; // 라디오 버튼의 선택 상태를 하나로 묶어주는 객체
  JLabel answer;

  public FirstHomework()
  {
    this.setSize(300, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("1차 과제");

    p = new JPanel();

    t1 = new JTextField(5);
    t2 = new JTextField(5);

    equal = new JButton("=");
    plusRadio = new JRadioButton("+");
    minusRadio = new JRadioButton("-");
    operatorGroup = new ButtonGroup();
    operatorGroup.add(plusRadio); // 라디오 버튼 선택 그룹 객체에 추가하여 관리
    operatorGroup.add(minusRadio); // 라디오 버튼 선택 그룹 객체에 추가하여 관리
    answer = new JLabel("정답 : ");

    // l1 = new JLabel("+");

    p.add(t1);
    // p.add(l1);

    p.add(plusRadio);
    p.add(minusRadio);

    p.add(t2);

    p.add(equal);
    equal.addActionListener(this);

    p.add(answer);
    this.add(p);

    Toolkit kit = Toolkit.getDefaultToolkit(); // 도구 모음
    Dimension screenSize = kit.getScreenSize(); // 현재 화면의 크기를 얻음

    setLocation(screenSize.width / 2, screenSize.height / 2); // 화면의 중앙
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == equal) {
      int num1 = Integer.parseInt(t1.getText());
      int num2 = Integer.parseInt(t2.getText());
      int result = 0;
      if (plusRadio.isSelected()) {
        result = num1 + num2;
      } else if (minusRadio.isSelected()) {
        result = num1 - num2;
      }
      answer.setText("정답 : " + result);
    }
  }

}
