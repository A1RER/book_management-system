package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.dao.UserDao;
import com.java.util.Connect;
import com.java.util.StringNull;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * 删除读者界面，提供一个图形用户界面，用于删除读者信息
 */
public class DeleteReaderInterface extends JFrame {

	private JPanel contentPane;
	private JTextField readerIdTxt;
	private UserDao readerDao = new UserDao();//读者数据访问对象
	private Connect conutil = new Connect(); //数据库连接工具类

	/**
	 * Create the frame.
	 * 创建删除读者假面
	 */
	public DeleteReaderInterface() {
		setTitle("删除用户");//设置窗口标题
		setBounds(100, 100, 450, 300);//设置窗口位置和大小
		//初始化内容面板并设置边框
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//创建“用户编号”标签并设置字体
		JLabel label = new JLabel("用户编号：");
		label.setFont(new Font("宋体", Font.PLAIN, 20));

		//创建用户编号输入框 并设置字体和列数
		readerIdTxt = new JTextField();
		readerIdTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		readerIdTxt.setColumns(10);

		//创建“删除此用户”按钮并添加点击事件监听器
		JButton deleteButton = new JButton("\u5220\u9664\u6B64\u7528\u6237");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出确认对话框，询问用户是否确认删除
				int i = JOptionPane.showConfirmDialog(null, "是否确认删除！", "选择一个选项", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION) {
					deleteReader();//用户点击“是”，并执行删除操作
				}else {
					JOptionPane.showMessageDialog(null, "请重新填写编号！");//用户点击“否”，提示重新填写
				}
				
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));//设置按钮字体

		//使用GroupLayout布局管理器设置界面布局
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(76)//左侧边距
							.addComponent(label)//添加“用户编号”标签
							.addPreferredGap(ComponentPlacement.RELATED)//添加组件间的默认间距
							.addComponent(readerIdTxt, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))//添加输入框
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)//左侧边距
							.addComponent(deleteButton)))//添加删除按钮
					.addContainerGap(59, Short.MAX_VALUE))//右侧边距
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(69)//顶部边距
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(readerIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))//标签和输入框对齐
					.addGap(48)//中间间距
					.addComponent(deleteButton)//添加删除按钮
					.addContainerGap(63, Short.MAX_VALUE))//底部边距
		);
		contentPane.setLayout(gl_contentPane);//设置布局管理器
	}
	/**
	 * 定义 删除用户功能
	 */
	protected void deleteReader() {
		String readerId  = this.readerIdTxt.getText();//获取输入的用户编号

		//检查用户编号是否为空
		if(StringNull.isEmpty(readerId)) {
			JOptionPane.showMessageDialog(null, "用户编号不能为空");
		}
		
		Connection con = null;
		try {
			con = conutil.loding();//获取数据库连接

			//查询用户是否存在
			ResultSet rs = readerDao.query(con, Integer.parseInt(readerId));
			if(rs.next()) {
				//用户存在 执行删除操作
				readerDao.delete(con, Integer.parseInt(readerId));
				JOptionPane.showMessageDialog(null, "删除成功!");
				return;
			}
			else {
				//用户不存在，提示删除失败
				JOptionPane.showMessageDialog(null, "删除失败！");
				return;
			}
		
		} catch (Exception e) {
			e.printStackTrace();//打印异常堆栈信息
			JOptionPane.showMessageDialog(null, "删除失败！");
			return;
		}finally {
			try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
