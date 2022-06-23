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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class 个人薪酬发放情况_jpanel  extends JPanel{
	public 个人薪酬发放情况_jpanel() {
		setSize(1080, 720);
		String[][] datas = {};
		String[] titles = { "档案编号", "姓名", "总工资", "基本工资", "职位名称", "交通补助", "午餐补助", "通信补助", "养老保险", "失业保险", "医疗保险", "住房公积金" };
		DefaultTableModel myModel = new DefaultTableModel(datas, titles);// myModel存放表格的数据
		JTable table = new JTable(myModel);// 表格对象table的数据来源是myModel对象
		JScrollPane scrollPane = new JScrollPane(table);// 带滚动条的面板
		table.setRowHeight(20);// 行高
		JButton b1=new JButton("刷新");
		JButton backButton=new JButton("返回");
		
		JLabel l11 = new JLabel("当前正在做的业务是:");
		JLabel l12 = new JLabel("<html><u>首页--></u><html>");
		l12.setForeground(Color.blue);
		JLabel l13 = new JLabel("<html><u>薪酬经理--></u><html>");
		l13.setForeground(Color.blue);
		JLabel l14 = new JLabel("查看个人薪酬发放标准");
		
		add(b1);
		add(backButton);
		add(scrollPane);
		
		add(l11);
		add(l12);
		add(l13);
		add(l14);
		
		b1.setBounds(650, 25, 100, 25);
		backButton.setBounds(800, 25, 100, 25);
		scrollPane.setBounds(50, 75, 950, 500);
		
		l11.setBounds(50, 10, 150, 25);
		l12.setBounds(200, 10, 50, 25);
		l13.setBounds(270, 10, 75, 25);
		l14.setBounds(350, 10, 150, 25);
		
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=人力资源管理系统2"; // 人力资源管理系统为数据库名
		String userName = "sa"; // 账号
		String userPwd = "sa"; // 填写密码
		try {
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement st = dbConn.createStatement();
			System.out.println("连接数据库成功");
			String strSQL = "select * from dbo.个人薪酬发放表";
			ResultSet rs = st.executeQuery(strSQL);
			if (myModel.getRowCount() != 0) {
				int num = myModel.getRowCount();
				for (int i = num - 1; i >= 0; i--) {
					myModel.removeRow(i);
				}
			}
			while (rs.next()) {
				Vector<String> ve = new Vector<String>();
				ve.addElement(rs.getString(1));
				ve.addElement(rs.getString(2));
				ve.addElement(rs.getString(3));
				ve.addElement(rs.getString(4));
				ve.addElement(rs.getString(5));
				ve.addElement(rs.getString(6));
				ve.addElement(rs.getString(7));
				ve.addElement(rs.getString(8));
				ve.addElement(rs.getString(9));
				ve.addElement(rs.getString(10));
				ve.addElement(rs.getString(11));
				ve.addElement(rs.getString(12));

				myModel.addRow(ve); // 添加一行到模型结尾

			}
			dbConn.close();

		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.print("连接失败");
		}
		
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
					薪酬经理_jpanel 薪酬经理 = new 薪酬经理_jpanel();
					add(薪酬经理);
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
		
		b1.addActionListener(new ActionListener() //刷新
				{
					public void actionPerformed(ActionEvent e) {

						removeAll();
						个人薪酬发放情况_jpanel 个人薪酬发放情况 = new 个人薪酬发放情况_jpanel();
						add(个人薪酬发放情况);
						repaint();

					}
				});
		
		backButton.addActionListener(new ActionListener() //
		{
			public void actionPerformed(ActionEvent e) {

				removeAll();
				薪酬经理_jpanel 薪酬经理 = new 薪酬经理_jpanel();
				add(薪酬经理);
				repaint();

			}
		});
	}
}
