 package com.java.frame;
 /**
  * 读者注册界面
  * 提供读者注册功能，包括输入读者信息和验证
  */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.java.dao.UserDao;
import com.java.model.Reader;
import com.java.util.Connect;
import com.java.util.StringNull;
import java.awt.Dialog.ModalExclusionType;

public class RegisterInterface extends JFrame {

	private JPanel contentPane;//内容面板
	private JTextField reader_id;//读者编号文本框
	private JTextField reader_name;//读者姓名文本框
	private JTextField reader_phone;//读者手机号文本框
	private JTextField reader_password;//读者密码文本框
	private Connect conutil= new Connect();//数据库连接工具

	JFrame frame = new JFrame();//当前窗口实例
	/**
	 * Create the frame.
	 * 创建注册界面
	 */
	public RegisterInterface() {
		//设置窗口标题
		setTitle("注册");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口位置和大小
		setBounds(100, 100, 663, 530);

		//初始化内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//初始化标签和文本框
		JLabel label = new JLabel("编号：");
		reader_id = new JTextField();
		reader_id.setColumns(10);
		
		JLabel label_1 = new JLabel("姓名：");
		reader_name = new JTextField();
		reader_name.setColumns(10);
		
		JLabel label_2 = new JLabel("手机号码：");
		reader_phone = new JTextField();
		reader_phone.setColumns(10);
		
		JLabel label_3 = new JLabel("密码：");
		reader_password = new JTextField();
		reader_password.setColumns(10);
		
		//初始化注册按钮并添加事件监听器
		JButton button = new JButton("注册");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//调用用户注册方法
				usearRegister(e);
			}
		});

		//初始化清空按钮并添加事件监听器
		JButton buttonNot = new JButton("\u6E05\u7A7A");
		buttonNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//调用清空文本框方法
				delActiontxt();
			}
		});
			
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(136)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonNot))
						.addComponent(reader_password)
						.addComponent(reader_phone)
						.addComponent(reader_name)
						.addComponent(reader_id, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addContainerGap(194, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(reader_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(reader_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(reader_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(reader_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(buttonNot))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
/**
 * 读者注册方法
 * 处理用户的注册请求，验证输入并将数据存入数据库
 * @param e
 */
	private void usearRegister(ActionEvent e) {
		//获取用户输入
		 String readerIdStr = this.reader_id.getText().toString();
		 int readerId = 0;
		 if(!StringNull.isEmpty(readerIdStr)) {
			 readerId = Integer.parseInt(readerIdStr);
		 }
		 
		 String readerName = this.reader_name.getText();
		 String readerPhone = this.reader_phone.getText();
		 String readerPassword = this.reader_password.getText();

		 //验证输入是否为空
		 if(StringNull.isEmpty(readerIdStr)) {
			 JOptionPane.showMessageDialog(null,"用户编号不能为空!");
			 return;
		 }
		 if(StringNull.isEmpty(readerName)) {
			 JOptionPane.showMessageDialog(null, "用户姓名不能为空！");
			 return;
		 }
		 if(StringNull.isEmpty(readerPhone)) {
			 JOptionPane.showMessageDialog(null, "用户手机号不能为空！");
			 return;
		 }
		 if(StringNull.isEmpty(readerPassword)) {
			 JOptionPane.showMessageDialog(null, "用户密码不能为空！");
			 return;
		 }
		 
		 Connection con = null;
		 UserDao ud = new UserDao();
		 try {
			 //创建读者对象
			Reader reader = new Reader(readerId,readerName,readerPhone,readerPassword);
			con = conutil.loding();//连接数据库

			 //检查账号是否已存在
			 Reader rs = ud.login(con, reader);
			if(String.valueOf(rs.getReader_id()).equals(readerIdStr)) {
				JOptionPane.showMessageDialog(null, "账号已存在，注册失败!");
				return;
			}
			else {
				ud.register(con, reader);//注册新用户
				JOptionPane.showMessageDialog(null, "注册成功!");
				return;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "账号已存在，注册失败!");
			e1.printStackTrace();
			return;
		} finally {
			  try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 清空输入框方法
	 * 清空所有输入文本框的内容
	 */

	private void delActiontxt() {
			this.reader_id.setText("");
			this.reader_name.setText("");
			this.reader_password.setText("");
			this.reader_phone.setText("");
	}
}