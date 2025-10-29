package JFrame.eventprogramming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChageBackground extends JFrame implements ActionListener {

  public JPanel panel;
  public JButton b1;
  public JButton b2;

  public ChageBackground() {
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    b1 = new JButton("노란색");
    b1.addActionListener(this);
    panel.add(b1);

    b2 = new JButton("핑크색");
    b2.addActionListener(this);
    panel.add(b2);

    this.add(panel);
    setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) {
      panel.setBackground(Color.yellow);
    } else if (e.getSource() == b2) {
      panel.setBackground(Color.pink);
    }
  }

}
