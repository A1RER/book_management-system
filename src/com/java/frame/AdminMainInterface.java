package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;//它通过将组件组织成组来确定它们在容器中的位置和大小。
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
* 管理员操作主界面，提供图书查询，借阅，添加，删除，用户查询，删除等功能按钮
* */
public class AdminMainInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * 创建管理员操作主界面
	 */
	JFrame frame = new JFrame();

	/**
	 * Create the frame.
	 */
	public AdminMainInterface() {
		setTitle("管理员操作");
		setBounds(100, 100, 395, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//查询图书按钮
		JButton queryBook = new JButton("查询图书");
		queryBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击查询图书按钮，打开查询图书界面
				QueryBookInterface qbi = new QueryBookInterface();
				qbi.setVisible(true);
			}
		});

		//借阅图书按钮
		JButton borrowBook = new JButton("借阅图书");
		borrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击借阅图书按钮，打开查询图书界面
				QueryBookInterface qbi = new QueryBookInterface();
				qbi.setVisible(true);
			}
		});

		//添加图书按钮
		JButton addBook = new JButton("增添图书");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击添加图书按钮，打开添加图书界面
				AddBookInterface ai = new AddBookInterface();
				ai.setVisible(true);
			}
		});

		//删除图书按钮
		JButton delBook = new JButton("删除图书");
		delBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击删除图书按钮，调用删除图书方法
				deleteBook();
			}
		});

		//归还图书按钮
		JButton returnBook = new JButton("归还图书");
		returnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击归还图书按钮，打开查询图书界面
				QueryBookInterface qbi = new QueryBookInterface();
				qbi.setVisible(true);
			}
		});

		//删除用户按钮
		JButton delReader = new JButton("删除用户");
		delReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击删除用户按钮，调用删除用户方法
				deleteReader();
			}
		});

		//查询用户按钮
		JButton queryReader = new JButton("查询用户");
		queryReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击查询用户按钮，调用查询用户方法
				queryReader();
			}
		});

		//创建GroupLayout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);

		//水平组设置
		gl_contentPane.setHorizontalGroup(
				//创建一个并行组，所有组件左对齐
			gl_contentPane.createParallelGroup(Alignment.LEADING)
					/**嵌套一个序列组(.createSequentialGroup),
					 * 包含：
					 * 面板内边距(addContainerGap())
					 * 一个并行组，包含所有按钮
					 * 右侧留出251像素的空白（addGap(251)）
					 */
					/**
					 * 按钮宽度设置
					 * 大多数按钮最小宽度为GroupLayout.DEFAULT_SIZE
					 * Short.MAX_VALUE 表示如果窗口变宽，按钮不会无限扩展，而是保持固定最大宽度
					 */
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(queryReader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(queryBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addComponent(returnBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addComponent(delBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
							.addComponent(addBook, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addComponent(delReader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
							.addComponent(borrowBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(251))
		);
		//垂直组设置
		gl_contentPane.setVerticalGroup(
				//使用.createParallelGroup(Alignment.LEADING)创建一个并行组
			gl_contentPane.createParallelGroup(Alignment.LEADING)
					//使用.createSequentialGroup() 嵌套一个序列组，按顺序添加所有按钮
				.addGroup(gl_contentPane.createSequentialGroup()
						/**
						 * 每个按钮之间使用addPreferredGap(ComponentPlacement.RELATED)添加默认间距
						 * 按钮高度设置为GroupLayout.PREFERRED_SIZE
						 *
						 * 最后一个按钮queryReader下方的间距设置为Short.MAX_VALUE,
						 * 表示如果窗口变高，多余空间将分配到此处，使按钮集中在顶部
						 */
					.addGap(1)
					.addComponent(queryBook, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(borrowBook, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(returnBook, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(delBook, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addBook, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(delReader, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(queryReader, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);

		//将GroupLayout设置为内容面板(contentPane)的布局管理器
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 打开删除用户界面
	 */
	protected void deleteReader() {
		DeleteReaderInterface dif = new DeleteReaderInterface();
		dif.setVisible(true);
	}

	/**
	 * 打开查询用户界面
	 */
	protected void queryReader() {
		QueryReaderInterface qri = new QueryReaderInterface();
		qri.setVisible(true);
	}

	/**
	 * 打开删除图书界面
	 */
	private void deleteBook() {
		DeleteBookInterface dbi = new DeleteBookInterface();
		dbi.setVisible(true);
	}
}
