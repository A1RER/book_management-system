package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *用户操作主界面
 * 提供查询图书、借阅图书信息和归还图书等功能入口
 */
public class ReaderMainInterface extends JFrame {

	private JPanel contentPane; //内容面板

	JFrame frame = new JFrame();//当前窗口实例
	/**
	 * Create the frame
	 * 创建用户操作主界面
	 */
	public ReaderMainInterface() {
		setTitle("用户操作");//设置窗口标题
		setBounds(100, 100, 450, 300);//设置窗口位置和大小

		//初始化内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//查询图书按钮
		JButton queryBook = new JButton("查询图书");
		queryBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBookfunction();
			}
		});

		//借阅图书按钮（修改信息status为1，即为借出，同时判断status是否为1,添加到bookinformation）
		JButton borrowBook = new JButton("借阅图书");
		borrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBookfunction();
			}
		});

		//归还图书按钮
		JButton returnBook = new JButton("归还图书");
		returnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBookfunction();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(queryBook)
							.addContainerGap(315, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(returnBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(315))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(borrowBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(315))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(queryBook)
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(borrowBook)
					.addGap(73)
					.addComponent(returnBook)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 查询图书功能
	 * 打开图书查询系统界面，用于查询，借阅，归还图书
	 */

	private void queryBookfunction() {
		QueryBookInterface qbi = new QueryBookInterface();//创建图书查询界面实例
		qbi.setVisible(true);//显示图书查询界面
	}
}
