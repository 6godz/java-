import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class 录入薪酬标准_jpanel extends JPanel {
	public 录入薪酬标准_jpanel() {
		setSize(1080, 720);

		JLabel l1 = new JLabel("薪酬标准名称:");
		JTextField t1 = new JTextField();// 薪酬标准名称输入框
		JLabel l2 = new JLabel("职位名称:");
		JTextField t2 = new JTextField();// 职位名称输入框
		JLabel l3 = new JLabel("制定人:");
		JTextField t3 = new JTextField();// 制定人输入框
		JLabel l4 = new JLabel("登记人:");
		JTextField t4 = new JTextField();// 登记人输入框
		JLabel l5 = new JLabel("基本工资:");
		JTextField t5 = new JTextField();// 基本工资输入框
		JLabel l6 = new JLabel("交通补助:");
		JTextField t6 = new JTextField();// 交通补助输入框
		JLabel l7 = new JLabel("午餐补助:");
		JTextField t7 = new JTextField();// 午餐补助输入框
		JLabel l8 = new JLabel("通信补助:");
		JTextField t8 = new JTextField();// 通信补助输入框

		JLabel l11 = new JLabel("当前正在做的业务是:");
		JLabel l12 = new JLabel("<html><u>首页--></u><html>");
		l12.setForeground(Color.blue);
		JLabel l13 = new JLabel("<html><u>薪酬专员--></u><html>");
		l13.setForeground(Color.blue);
		JLabel l14 = new JLabel("录入薪酬标准");

		JButton b1 = new JButton("录入");// 录入按钮
		JButton b2 = new JButton("清空");// 清空按钮
		JButton backButton = new JButton("返回");// 返回按钮

		setLayout(null);// jPanel布局管理器取消默认设置
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(l6);
		add(t6);
		add(l7);
		add(t7);
		add(l8);
		add(t8);
		
		
		add(l11);
		add(l12);
		add(l13);
		add(l14);

		add(b1);
		add(b2);
		add(backButton);
		l1.setBounds(50, 50, 75, 25);// 薪酬标准名称文本位置
		t1.setBounds(125, 50, 100, 25);// 薪酬标准名称输入框位置
		l2.setBounds(225, 50, 75, 25);// 职位名称文本位置
		t2.setBounds(300, 50, 100, 25);// 职位名称输入框位置
		l3.setBounds(400, 50, 75, 25);// 制定人文本位置
		t3.setBounds(475, 50, 100, 25);// 制定人输入框位置
		l4.setBounds(575, 50, 75, 25);// 登记人文本位置
		t4.setBounds(650, 50, 100, 25);// 登记人输入框位置
		l5.setBounds(750, 50, 75, 25);// 基本工资文本位置
		t5.setBounds(825, 50, 100, 25);// 基本工资输入框位置
		l6.setBounds(50, 100, 75, 25);// 交通补助文本位置
		t6.setBounds(125, 100, 100, 25);// 交通补助输入框位置
		l7.setBounds(225, 100, 75, 25);// 午餐补助文本位置
		t7.setBounds(300, 100, 100, 25);// 午餐补助输入框位置
		l8.setBounds(400, 100, 75, 25);// 通信补助文本位置
		t8.setBounds(475, 100, 100, 25);// 通信补助输入框位置

		l11.setBounds(50, 10, 150, 25);
		l12.setBounds(200, 10, 50, 25);
		l13.setBounds(270, 10, 75, 25);
		l14.setBounds(350, 10, 75, 25);

		b1.setBounds(150, 200, 150, 30);// 录入按钮位置
		b2.setBounds(350, 200, 150, 30);// 清空按钮位置
		backButton.setBounds(550, 200, 150, 30);// 返回按钮位置

		// b1.setEnabled(false);
		l12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() <= l12.getX() + l12.getWidth() && e.getX() >= l12.getX()
						&& e.getY() <= l12.getY() + l12.getHeight() && e.getY() >= l12.getY()) {
					removeAll();
					身份选择_jpanel 身份选择 = new 身份选择_jpanel();
					add(身份选择);
					repaint();
				}
				if (e.getX() <= l13.getX() + l13.getWidth() && e.getX() >= l13.getX()
						&& e.getY() <= l13.getY() + l13.getHeight() && e.getY() >= l13.getY()) {
					removeAll();
					薪酬专员_jpanel 薪酬专员 = new 薪酬专员_jpanel();
					add(薪酬专员);
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		b1.addActionListener(new ActionListener() // 录入按钮
		{
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().length() != 0 && t2.getText().length() != 0 && t3.getText().length() != 0
						&& t4.getText().length() != 0 && t5.getText().length() != 0&& t6.getText().length() != 0&& t7.getText().length() != 0&& t8.getText().length() != 0) {

					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=人力资源管理系统2"; // 人力资源管理系统为数据库名

					String userName = "sa"; // 账号
					String userPwd = "sa"; // 填写密码

					try {
						Class.forName(driverName);
						Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						Statement st = dbConn.createStatement();

						System.out.println("连接数据库成功");
						// insert into 薪酬标准表(薪酬标准名称,职位名称,制定人,登记人,基本工资,标准状态)
						// values('经理标准','经理''aa','ca',8000,'待复核')

						String strSQL = "insert into dbo.薪酬标准表(薪酬标准名称,职位名称,制定人,登记人,基本工资,交通补助,午餐补助,通信补助,标准状态) values('" + t1.getText()
								+ "','" + t2.getText() + "','" + t3.getText() + "','" + t4.getText() + "','"
								+ t5.getText() +"','" + t6.getText() +"','" + t7.getText() +"','" + t8.getText() + "','待复核')";
						boolean rs = st.execute(strSQL);
						System.out.println(rs);
						JOptionPane.showMessageDialog(null, "录入成功！", "恭喜", JOptionPane.DEFAULT_OPTION);
						dbConn.close();

					} catch (Exception ee) {
						ee.printStackTrace();
						System.out.print("连接失败");
					}

				} else {
					JOptionPane.showMessageDialog(null, "不能留空！", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		b2.addActionListener(new ActionListener() // 清空按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				录入薪酬标准_jpanel 录入薪酬标准 = new 录入薪酬标准_jpanel();
				add(录入薪酬标准);
				repaint();
			}
		});

		backButton.addActionListener(new ActionListener() // 返回按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				薪酬专员_jpanel 薪酬专员 = new 薪酬专员_jpanel();
				add(薪酬专员);
				repaint();
			}
		});

	}

	
}
