import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
public class 主界面_jframe extends JFrame {
	private static final long serialVersionUID = 1L;
	public 主界面_jframe() {
		// 初始界面
		身份选择_jpanel 身份选择 = new 身份选择_jpanel();
		setTitle("人力管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1080, 720);
		this.setLocationRelativeTo(null);// 窗口居中
		this.setVisible(true);
		setLayout(null);// 布局管理器取消默认设置
		this.add(身份选择);
	}
	public static void main(String[] args) {
		Font f = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Button" + ".font", f);
		new 主界面_jframe();
	}
}
