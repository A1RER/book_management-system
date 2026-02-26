package com.java.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.java.dao.AdminDao;
import com.java.dao.UserDao;
import com.java.model.Admin;
import com.java.model.Reader;
import com.java.util.Connect;
import com.java.util.StringNull;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.ImageIcon;

/**
 * 系统登陆界面
 * 提供用户和管理员两种角色的登录功能
 */

public class EnterInterface extends JFrame {

	private JPanel contentPane;
	private JTextField user_name;//用户名输入框
	private JLabel label;//“用户名：”标签
	private JLabel label_1;//“密码：”标签
	private JRadioButton userBut;//用户登录单选按钮
	private JRadioButton adminBut;//管理员登录单选按钮
	private final JPanel panel = new JPanel();
	private JLabel label_2;//系统标题标签
	private JPasswordField user_password;//密码输入框
	private Connect conutil= new Connect();//数据库连接工具
	private AdminDao adminDao = new AdminDao();//管理员数据访问对象
	private UserDao readerDao = new UserDao();//用户数据访问对象
	private final ButtonGroup buttonGroup = new ButtonGroup();//单选按钮组
	private int action = 0;//登陆类型：0-未选择 1-用户 2-管理员
	
	JFrame frame = new JFrame(); 
	
	/**
	 * Create the frame.
	 * 创建登录窗口
	 */
	public EnterInterface() {
		setResizable(false);//禁止调整窗口大小
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\图书管理系统\\数据库\\window.jpg"));//设置窗口图标
		setTitle("图书管理系统");//设置窗口标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭操作为退出程序
		setBounds(100, 100, 663, 530);//设置窗口位置和大小

		//初始化内容面板
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//初始化用户名输入框
		user_name = new JTextField();
		user_name.setColumns(10);

		//初始化标签
		label = new JLabel("用户名：");
		label_1 = new JLabel("密码：");
		label_1.setIcon(null);

		//初始化登录按钮
		JButton button = new JButton("登录");
		button.setIcon(null);
		button.setForeground(Color.BLUE);

		//为登录按钮添加事件监听器（第一个监听器，检查是否先择登陆方式）
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(0 == action)
				JOptionPane.showMessageDialog(null, "请选择登录方式！");
			}
		});
		button.setForeground(Color.BLUE);

		//初始化注册按钮
		JButton button_1 = new JButton("注册");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开注册界面
				userRegister(e);
			}
		});
		button_1.setForeground(Color.BLUE);

		//初始化用户单选按钮
		userBut = new JRadioButton("用户");
		buttonGroup.add(userBut);//将用户按钮添加到按钮组
		userBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action =1;//选择用户登录
			}
		});

		//初始化管理员单选按钮
		adminBut = new JRadioButton("管理员");
		buttonGroup.add(adminBut);//将管理员按钮添加到按钮组
		adminBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//选择管理员登录
				action = 2;
			}
		});

		//为登录按钮添加事件监听器（第二个监听器：处理登录逻辑）
		button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e2) {
				if(1 == action) {//用户登录
					int toRmif = userLogin(e2);
					if(1 == toRmif) {//登陆成功
						ReaderMainInterface Rmif = new ReaderMainInterface();
						Rmif.setVisible(true);//打开读者主界面
					}
				}	
				if(2 == action) {//管理员登录
					int toAmif = adminLogin(e2);
					if(1 == toAmif) {//登陆成功
						AdminMainInterface Amif = new AdminMainInterface();
						Amif.setVisible(true);//打开管理员主界面
					}
				}
			}
		});

		//初始化层面版（用于界面布局）
		JLayeredPane layeredPane = new JLayeredPane();
		JLayeredPane layeredPane_1 = new JLayeredPane();
		JLayeredPane layeredPane_2 = new JLayeredPane();

		//初始化系统管理标签
		label_2 = new JLabel("图书管理系统");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 40));

		//初始化密码输入框
		user_password = new JPasswordField();

		//使用GroupLayout设置界面布局
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		//水平方向
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(129)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(260)
							.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
													.addGap(32))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(label, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED)))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(user_name, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(button, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
														.addComponent(user_password, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
											.addComponent(userBut)
											.addGap(31)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(37)
													.addComponent(adminBut))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(77))
												.addComponent(button_1))))
									.addGap(138))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
									.addGap(119)))))
					.addGap(190))
		);
		//垂直方向
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(adminBut)
								.addComponent(userBut))
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1)))
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addGap(137)
							.addComponent(layeredPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_1)))
		);
		contentPane.setLayout(gl_contentPane);//设置布局管理器
	}
	/**
	 * 管理员登陆验证
	 * @param e 事件对象
	 * @return 登陆成功返回1，失败返回0
	 */
	private int adminLogin(ActionEvent e)  {
		String userName = this.user_name.getText();//获取用户名
		String password = new String(this.user_password.getPassword());//获取密码
		//设置管理员ID，用于后续查询
		QueryBookInterface.setAdminId(userName);
		//验证输入
		if(StringNull.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "管理员名不能为空！");
			return 0;
		}
		if(StringNull.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return 0;
		}
		Admin admin = new Admin(userName,password);
		Connection con =null;
		try {
		con = conutil.loding();//连接数据库
		Admin curreatAdmin= adminDao.login(con, admin);
		
		if(curreatAdmin!=null) {
			//登陆成功
			JOptionPane.showMessageDialog(null, "管理员登陆成功！");
			return 1;
		}else {
			//登陆失败
			JOptionPane.showMessageDialog(null, "管理员名或者密码错误！");
			return 0;
		}
		}catch(Exception e1){
			e1.printStackTrace();//打印异常堆栈
			return 0;
		}finally {
			  try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 用户登陆验证
	 * @param e 事件对象
	 * @return 登陆成功返回1，失败返回0
	 */
	private int userLogin(ActionEvent e)  {
		String userName1 = this.user_name.getText(); //获取用户名
		String password1 = new String(this.user_password.getPassword());//获取密码
		//设置读者ID，用于后续查询
		 QueryBookInterface.setReaderId(userName1);
		//验证输入
		if(StringNull.isEmpty(userName1)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return 0;
		}
		if(StringNull.isEmpty(password1)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return 0;
		}

		Reader reader =new Reader(userName1,password1);//创建读者对象
		Connection con =null;
		try {
		con = conutil.loding();//连接数据库
		Reader curreatReader= readerDao.login(con, reader);
		
		if(curreatReader!=null) {
			//登陆成功
			JOptionPane.showMessageDialog(null, "登陆成功！");
			return 1;
		}else {
			//登陆失败
			JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
			return 0;
		}
		}catch(Exception e1){
			e1.printStackTrace();//打印异常堆栈
			return 0;
		}finally {
			  try {
				conutil.closeCon(con); //关闭数据库连接
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 注册事件处理
	 * @param e 事件对象
	 */
	private void userRegister(ActionEvent e) {
		RegisterInterface ri = new RegisterInterface();
		ri.setVisible(true);//打开注册界面
	}
}
