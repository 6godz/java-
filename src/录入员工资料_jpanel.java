import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class 录入员工资料_jpanel extends JPanel {
	public 录入员工资料_jpanel() {
		setSize(1080, 720);
		JLabel l1 = new JLabel("一级机构:");
		JComboBox<String> c1 = new JComboBox<String>();// 一级机构下拉框

		JLabel l2 = new JLabel("二级机构:");
		JComboBox<String> c2 = new JComboBox<String>();// 二级机构下拉框

		JLabel l3 = new JLabel("三级机构:");
		JComboBox<String> c3 = new JComboBox<String>();// 三级机构下拉框

		JLabel l4 = new JLabel("职位:");
		JComboBox<String> c4 = new JComboBox<String>();// 职位下拉框

		JLabel l5 = new JLabel("姓名:");
		JTextField t5 = new JTextField();// 姓名输入框
		JLabel l6 = new JLabel("性别:");
		JComboBox<String> c6 = new JComboBox<String>();// 性别下拉框
		c6.addItem("男");
		c6.addItem("女");
		JLabel l7 = new JLabel("Email:");
		JTextField t7 = new JTextField();// Email输入框
		JLabel l8 = new JLabel("电话:");
		JTextField t8 = new JTextField();// 电话输入框
		JLabel l9 = new JLabel("QQ:");
		JTextField t9 = new JTextField();// QQ输入框
		JLabel l10 = new JLabel("住址:");
		JTextField t10 = new JTextField();// 住址输入框
		JLabel l11 = new JLabel("国籍:");
		JTextField t11 = new JTextField();// 国籍输入框
		JLabel l12 = new JLabel("民族:");
		JTextField t12 = new JTextField();// 民族输入框
		JLabel l13 = new JLabel("年龄:");
		JTextField t13 = new JTextField();// 年龄输入框
		JLabel l14 = new JLabel("学历:");
		JComboBox<String> c14 = new JComboBox<String>();// 学历下拉框
		c14.addItem("专科");
		c14.addItem("本科");
		c14.addItem("研究生");
		JLabel l15 = new JLabel("薪酬标准:");
		JComboBox<String> c15 = new JComboBox<String>();// 薪酬标准下拉框

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=人力资源管理系统2"; // 人力资源管理系统为数据库名
		String userName = "sa"; // 账号
		String userPwd = "sa"; // 填写密码

		try {
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement st = dbConn.createStatement();
			System.out.println("连接数据库成功1");
			String strSQL = "select 薪酬标准名称 from dbo.薪酬标准表 where 标准状态='正常'";
			ResultSet rs = st.executeQuery(strSQL);
			while (rs.next()) {
				c15.addItem(rs.getString(1));
			}

			String strSQL1 = "select 单位名称 from 机构与职位表 where 单位关系 like '_'";
			ResultSet rs1 = st.executeQuery(strSQL1);
			while (rs1.next()) {
				c1.addItem(rs1.getString(1));
			}

			String strSQL3 = "select 单位名称 from 机构与职位表 where 单位关系='0.0.0'";
			ResultSet rs3 = st.executeQuery(strSQL3);
			while (rs3.next()) {
				c3.addItem(rs3.getString(1));
			}

			String strSQL4 = "select 单位名称 from 机构与职位表 where 单位关系='0.0.0.0'";
			ResultSet rs4 = st.executeQuery(strSQL4);
			while (rs4.next()) {
				c4.addItem(rs4.getString(1));
			}
			dbConn.close();
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.print("连接失败");
		}

		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c2.removeAllItems();
				try {
					Class.forName(driverName);
					Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					Statement st = dbConn.createStatement();
					System.out.println("连接数据库成功1");
					String strSQL1 = "declare @单位关系 varchar(10) set @单位关系=(select 单位关系 from 机构与职位表 where 单位名称='"
							+ c1.getSelectedItem() + "' ) select 单位名称 from 机构与职位表 where 单位关系 like @单位关系+'._'";
					ResultSet rs1 = st.executeQuery(strSQL1);
					while (rs1.next()) {
						c2.addItem(rs1.getString(1));
					}
					dbConn.close();
				} catch (Exception ee) {
					ee.printStackTrace();
					System.out.print("连接失败");
				}

			}
		});

		JLabel l16 = new JLabel("个人履历:");
		JTextField t16 = new JTextField();// 个人履历输入框
		JLabel l17 = new JLabel("家庭关系信息:");
		JTextField t17 = new JTextField();// 家庭关系信息输入框
		JLabel l18 = new JLabel("备注:");
		JTextField t18 = new JTextField();// 备注输入框
		JLabel l19 = new JLabel("登记人:");
		l19.setFont(new Font(null, Font.BOLD, 20));
		JTextField t19 = new JTextField();// 登记人输入框
		JLabel l20 = new JLabel("建档时间" + gettime());
		l20.setFont(new Font(null, Font.BOLD, 20));

		JLabel l21 = new JLabel("当前正在做的业务是:");
		JLabel l22 = new JLabel("<html><u>首页--></u><html>");
		l22.setForeground(Color.blue);
		JLabel l23 = new JLabel("<html><u>人事专员--></u><html>");
		l23.setForeground(Color.blue);
		JLabel l24 = new JLabel("录入员工信息");

		JButton b1 = new JButton("录入");// 录入按钮
		JButton b2 = new JButton("清空");// 清空按钮
		JButton backButton = new JButton("返回");// 返回按钮

		setLayout(null);// jPanel布局管理器取消默认设置
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(l7);
		add(l8);
		add(l9);
		add(l10);
		add(l11);
		add(l12);
		add(l13);
		add(l14);
		add(l15);
		add(l16);
		add(l17);
		add(l18);
		add(l19);
		add(l20);

		add(c1);
		add(c2);
		add(c3);
		add(c4);
		add(t5);
		add(c6);
		add(t7);
		add(t8);
		add(t9);
		add(t10);
		add(t11);
		add(t12);
		add(t13);
		add(c14);
		add(c15);
		add(t16);
		add(t17);
		add(t18);
		add(t19);

		add(l21);
		add(l22);
		add(l23);
		add(l24);

		add(b1);
		add(b2);
		add(backButton);
		l1.setBounds(50, 50, 75, 25);// 一级机构位置
		c1.setBounds(125, 50, 100, 25);
		l2.setBounds(225, 50, 75, 25);// 二级机构位置
		c2.setBounds(300, 50, 100, 25);
		l3.setBounds(400, 50, 75, 25);// 三级机构位置
		c3.setBounds(475, 50, 100, 25);
		l4.setBounds(575, 50, 75, 25);// 职位位置
		c4.setBounds(650, 50, 100, 25);
		l5.setBounds(750, 50, 75, 25);// 姓名位置
		t5.setBounds(825, 50, 100, 25);

		l6.setBounds(50, 100, 75, 25);// 性别位置
		c6.setBounds(125, 100, 100, 25);
		l7.setBounds(225, 100, 75, 25);// Email位置
		t7.setBounds(300, 100, 100, 25);
		l8.setBounds(400, 100, 75, 25);// 电话位置
		t8.setBounds(475, 100, 100, 25);
		l9.setBounds(575, 100, 75, 25);// QQ位置
		t9.setBounds(650, 100, 100, 25);
		l10.setBounds(750, 100, 75, 25);// 住址位置
		t10.setBounds(825, 100, 100, 25);

		l11.setBounds(50, 150, 75, 25);// 国籍位置
		t11.setBounds(125, 150, 100, 25);
		l12.setBounds(225, 150, 75, 25);// 民族位置
		t12.setBounds(300, 150, 100, 25);
		l13.setBounds(400, 150, 75, 25);// 年龄位置
		t13.setBounds(475, 150, 100, 25);
		l14.setBounds(575, 150, 75, 25);// 学历位置
		c14.setBounds(650, 150, 100, 25);
		l15.setBounds(750, 150, 75, 25);// 薪酬标准位置
		c15.setBounds(825, 150, 100, 25);

		l16.setBounds(50, 200, 75, 25);// 个人履历位置
		t16.setBounds(125, 200, 400, 35);
		l17.setBounds(50, 250, 75, 25);// 家庭关系信息位置
		t17.setBounds(125, 250, 400, 35);
		l18.setBounds(50, 300, 75, 25);// 备注位置
		t18.setBounds(125, 300, 400, 35);
		l19.setBounds(680, 250, 75, 25);// 登记人位置
		t19.setBounds(750, 250, 150, 30);
		l20.setBounds(690, 280, 200, 35);// 登记时间位置

		l21.setBounds(50, 10, 150, 25);
		l22.setBounds(200, 10, 50, 25);
		l23.setBounds(270, 10, 75, 25);
		l24.setBounds(350, 10, 75, 25);

		b1.setBounds(150, 400, 150, 30);// 录入按钮位置
		b2.setBounds(350, 400, 150, 30);// 清空按钮位置
		backButton.setBounds(550, 400, 150, 30);// 返回按钮位置

		l22.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l23.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() <= l22.getX() + l22.getWidth() && e.getX() >= l22.getX()
						&& e.getY() <= l22.getY() + l22.getHeight() && e.getY() >= l22.getY()) {
					removeAll();
					身份选择_jpanel 身份选择 = new 身份选择_jpanel();
					add(身份选择);
					repaint();
				}
				if (e.getX() <= l23.getX() + l23.getWidth() && e.getX() >= l23.getX()
						&& e.getY() <= l23.getY() + l23.getHeight() && e.getY() >= l23.getY()) {
					removeAll();
					人事专员_jpanel 人事专员 = new 人事专员_jpanel();
					add(人事专员);
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
				if (t5.getText().length() != 0 && t7.getText().length() != 0 && t8.getText().length() != 0
						&& t9.getText().length() != 0 && t10.getText().length() != 0 && t11.getText().length() != 0
						&& t12.getText().length() != 0 && t13.getText().length() != 0 && t16.getText().length() != 0
						&& t17.getText().length() != 0 && t18.getText().length() != 0 && t19.getText().length() != 0) {
					try {
						Class.forName(driverName);
						Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						Statement st = dbConn.createStatement();
						System.out.println("连接数据库成功111");
						String strSQL = "insert into dbo.员工资料表(一级机构,二级机构,三级机构,职位,姓名,性别,Email,电话,QQ,住址,国籍,民族,年龄,学历,薪酬标准,个人履历,家庭关系信息,备注,登记人,状态)"
								+ " values('" + c1.getSelectedItem() + "','" + c2.getSelectedItem() + "','"
								+ c3.getSelectedItem() + "','" + c4.getSelectedItem() + "','" + t5.getText() + "','"
								+ c6.getSelectedItem() + "','" + t7.getText() + "','" + t8.getText() + "','"
								+ t9.getText() + "','" + t10.getText() + "','" + t11.getText() + "','" + t12.getText()
								+ "','" + t13.getText() + "','" + c14.getSelectedItem() + "','" + c15.getSelectedItem()
								+ "','" + t16.getText() + "','" + t17.getText() + "','" + t18.getText() + "','"
								+ t19.getText() + "','待复核')";
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
				录入员工资料_jpanel 录入员工资料 = new 录入员工资料_jpanel();
				add(录入员工资料);
				repaint();
			}
		});
		backButton.addActionListener(new ActionListener() // 返回按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				人事专员_jpanel 人事专员 = new 人事专员_jpanel();
				add(人事专员);
				repaint();
			}
		});
	}

	public static String gettime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return sdf.format(date);
	}

}
