import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class 人事专员_jpanel extends JPanel {
	public 人事专员_jpanel() {
		setSize(1080, 720);
		JLabel l1 = new JLabel("人事专员");

		JButton 录入员工资料按钮 = new JButton("录入员工资料");
		JButton 查询与修改按钮 = new JButton("查询/修改");
		JButton backButton = new JButton("返回");
		this.setLayout(null);// jPanel布局管理器取消默认设置

		this.add(l1);
		this.add(录入员工资料按钮);
		this.add(查询与修改按钮);
		this.add(backButton);
		l1.setFont(new Font("黑体", Font.PLAIN, 32));
		l1.setBounds(335, 50, 300, 100);
		录入员工资料按钮.setBounds(300, 150, 200, 50);
		查询与修改按钮.setBounds(300, 220, 200, 50);
		backButton.setBounds(300, 290, 200, 50);

		录入员工资料按钮.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				录入员工资料_jpanel 录入员工资料 = new 录入员工资料_jpanel();
				add(录入员工资料);
				repaint();
			}
		});

		查询与修改按钮.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				查询与修改_jpanel 查询与修改 = new 查询与修改_jpanel();
				add(查询与修改);
				repaint();
			}
		});

		backButton.addActionListener(new ActionListener() // 监听人事经理按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				身份选择_jpanel 身份选择 = new 身份选择_jpanel();
				add(身份选择);
				repaint();
			}
		});
	}
}
