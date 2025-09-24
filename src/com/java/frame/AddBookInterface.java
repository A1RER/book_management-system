package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.dao.BookDao;
import com.java.model.Book;
import com.java.util.Connect;
import com.java.util.StringNull;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * 添加图书界面，提供一个图形用户界面，用于添加新的图书信息
 */
public class AddBookInterface extends JFrame {
	/**
	 * 界面是一个继承自JFrame的窗口，标题为“添加图书”，大小为663*530像素
	 *
	 * 包含
	 * 顶部标题栏
	 * 中央内容面板(带5像素边距)
	 * 表单区域(包含5个标签和文本框)
	 * 底部的“添加”按钮
	 */

	private JPanel contentPane;
	private JTextField bookIdTxt;
	private JTextField bookNameTxt;
	private JTextField bookWriterTxt;
	private JTextField bookPublishTxt;
	private JTextField bookStatusTxt;
	private Connect contil = new Connect();
	private BookDao bookDao= new BookDao();
	JFrame frame = new JFrame();

	/**
	 * Create the frame.
	 * 创建添加图书界面
	 */
	public AddBookInterface() {
		setTitle("添加图书");
		setBounds(100, 100, 663, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//创建“添加”按钮并绑定点击事件
		JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击添加按钮，调用添加图书方法
				addBook();
			}
		});

		//创建5个标签(图书编号、图书名字、图书作者、出版社、状态)
		JLabel label = new JLabel("图书编号:");
		
		JLabel label_1 = new JLabel("图书名字:");
		
		JLabel label_2 = new JLabel("图书作者：");
		
		JLabel label_3 = new JLabel("出版社：");
		
		JLabel label_4 = new JLabel("状态：");

		//创建5个文本框(用于输入图书信息)
		bookIdTxt = new JTextField();
		bookIdTxt.setColumns(10);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		bookWriterTxt = new JTextField();
		bookWriterTxt.setColumns(10);
		
		bookPublishTxt = new JTextField();
		bookPublishTxt.setColumns(10);
		
		bookStatusTxt = new JTextField();
		bookStatusTxt.setColumns(10);

		//使用GroupLayout通过水平组和垂直组来精确控制组件的位置和大小
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							//第一行：图书编号标签和文本框
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
								//其他行：标签和文本框对齐排列
									.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1)
										.addComponent(label_2)
										.addComponent(label_3)
										.addComponent(label_4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(bookStatusTxt, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookPublishTxt, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookWriterTxt, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))))
							//添加按钮(居中)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(279)
							.addComponent(addButton)))
					.addContainerGap(198, Short.MAX_VALUE))
		);
		//垂直组设置
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(bookWriterTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(bookPublishTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(bookStatusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addComponent(addButton)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 添加图书方法
	 */
	protected void addBook() {
		String bookId = this.bookIdTxt.getText();
		String bookName = this.bookNameTxt.getText();
		String bookPublish = this.bookPublishTxt.getText();
		String bookWriter = this.bookWriterTxt.getText();
		String bookStatus = this.bookStatusTxt.getText();
		
		//检查图书编号是否为空
		if(StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书编号不能为空！");
			return;
		}

		//检查图书名称是否为空
		if(StringNull.isEmpty(bookName)) { 
			JOptionPane.showMessageDialog(null, "图书姓名不能为空！");
			return;
		}

		//检查图书出版社是否为空
		if(StringNull.isEmpty(bookPublish)) {
			JOptionPane.showMessageDialog(null, "图书出版社不能为空！");
			return ;
		}

		//检查图书作者是否为空
		if(StringNull.isEmpty(bookWriter)) {
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");
			return;
		}

		//检查图书状态是否为空
		if(StringNull.isEmpty(bookStatus)) {
			JOptionPane.showMessageDialog(null, "图书状态不能为空！");
			return;
		}
		
		Connection con = null;
		/**
		 * try块：包含了可能会抛出异常的代码，程序执行到这里时，Java会监控这些代码的执行情况
		 *
		 * 具体可能抛出异常的点包括
		 * 1.Integer.parseInt(bookId):如果bookId不是一个有效的整数格式，会抛出NumberFormatException
		 * 2.contil.loding():在加载数据库连接时可能会抛出ClassNotFoundException(数据库驱动找不到)或SQLException(如果数据库连接失败)
		 * 3.bookDao.query2(con,book)和bookDao.add(con,book):在执行数据库查询和插入操作时可能会抛出SQLException
		 */
		try {
			Book book = new Book(Integer.parseInt(bookId), bookName, bookWriter, bookPublish, bookStatus);
			con = contil.loding();//获取数据库连接
			ResultSet rs = bookDao.query2(con, book);
			
			if(rs.next()) {
				//检查图书是否已经存在
				JOptionPane.showMessageDialog(null, "添加失败！");
				return; 
			}else {
				//添加图书信息到数据库
				bookDao.add(con, book);
				JOptionPane.showMessageDialog(null, "添加成功！");
				return;
			}
		}
		/**
		 * catch 块
		 * ->用于捕获try块中抛出的异常。(这里的Exception是一个通用的异常类型，可以捕获所有的异常)
		 *
		 * 当try块中的代码抛出异常时，程序会立即跳转到catch块中执行
		 *
		 * e.printStackTrace用于打印异常的堆栈跟踪信息
		 */
		catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * finally块中的代码无论try块中是否发生异常，都会执行
		 * 这里的finally块主要用于关闭数据库连接，确保资源被正确释放
		 */
		finally {
			try {
				//关闭数据库连接
				//在finally块中使用try-catch语句是因为关闭数据库连接时也可能会抛出异常，如SQLException
				contil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
