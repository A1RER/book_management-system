package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.BookDao;
import com.java.dao.BookInformationDao;
import com.java.model.Book;
import com.java.model.BookInformation;
import com.java.util.Connect;
import com.java.util.StringNull;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 图书的查询、借阅、归还界面
 * 提供图书信息查询、借阅和归还功能
 */
public class QueryBookInterface extends JFrame {

	private JPanel contentPane;//内容面板
	private JTextField book_nameTxt;//图书名称文本框
	private JTextField book_writerTxt;//图书作者文本框
	private JTextField book_publishTxt;//图书出版社文本框
	private Connect conutil= new Connect();//数据库连接工具
	private BookDao bookDao= new BookDao();//图书信息数据访问对象
	private BookInformationDao bookInformationDao = new BookInformationDao();//图书信息数据访问对象
	public static String readerName;//当前读者名称（静态变量，用于存储登录的读者信息）
	public static String adminName;//当前管理员名称（静态变更，用于存储登陆的管理员信息）

	/**
	 * Launch the application.
	 */
	JFrame frame = new JFrame();//当前窗口实例
	private JTable bookTable;//图书表格
	private JPanel panel;//面板
	private JLabel lblNewLabel;//标签：图书名称
	private JButton borrowButton;//借阅按钮
	private JLabel label;//标签：作者
	private JLabel label_4;//标签：出版社
	private JLabel label_5;//标签：状态
	private JTextField book_RBnameTxt;//借阅/归还图书名称 文本框
	private JTextField book_RBwriterTxt;//借阅/归还图书作者 文本框
	private JTextField book_RBpublishTxt;//借阅/归还图书出版社 文本框
	private JTextField book_RBstatusTxt;//借阅/归还图书状态 文本框
	private JButton returnButton;//归还按钮
	private JTextField book_RBidTxt;//借阅/归还图书Id 文本框
	/**
	 * 构造函数，创建图书查询界面
	 */
	public QueryBookInterface() {
		//设置窗口标题
		setTitle("图书的查询，借阅，归还");
		//设置关闭操作为隐藏窗口
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//设置窗口位置和大小
		setBounds(100, 100, 920, 686);
		//初始化内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//初始化查询按钮，并添加事件监听器
		JButton queryButton = new JButton("查询");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//调用查询图书方法
				queryBookTable(e);
			}
		});

		//初始化标签
		JLabel label_1 = new JLabel("图书名字:");
		JLabel label_2 = new JLabel("图书作者：");
		JLabel label_3 = new JLabel("出版社");

		//初始化文本框
		book_nameTxt = new JTextField();
		book_nameTxt.setColumns(10);
		
		book_writerTxt = new JTextField();
		book_writerTxt.setColumns(10);
		 
		book_publishTxt = new JTextField();
		book_publishTxt.setColumns(10);

		//初始化滚动面板和表格
		JScrollPane scrollPane = new JScrollPane();

		//初始化面板
		panel = new JPanel();

		//使用GroupLayout设置界面布局
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		//水平方向
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_1)
								.addComponent(label_3))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(queryButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(book_nameTxt)
									.addComponent(book_writerTxt)
									.addComponent(book_publishTxt, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		//垂直方向
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(book_nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(book_writerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(book_publishTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(queryButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGap(21))
		);

		//初始化面板中的标签
		lblNewLabel = new JLabel("图书名字：");

		//初始化借阅按钮，并添加事件监听器
		borrowButton = new JButton("借阅");
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//调用借阅图书方法
				borrowBook();
			}
		});

		//初始化其他标签
		label = new JLabel("\u4F5C\u8005\uFF1A");
		label_4 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label_5 = new JLabel("\u72B6\u6001\uFF1A");

		//初始化面板中的文本框
		book_RBnameTxt = new JTextField();
		book_RBnameTxt.setColumns(10);
		
		book_RBwriterTxt = new JTextField();
		book_RBwriterTxt.setColumns(10);
		
		book_RBpublishTxt = new JTextField();
		book_RBpublishTxt.setColumns(10);
		
		book_RBstatusTxt = new JTextField();
		book_RBstatusTxt.setColumns(10);

		//初始化归还按钮，并添加事件监听器
		returnButton = new JButton("归还");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBook();
			}
		});

		//初始化图书ID标签和文本框
		JLabel label_6 = new JLabel("编号：");
		book_RBidTxt = new JTextField();
		book_RBidTxt.setColumns(10);

		//使用GroupLayout设置面板布局
		GroupLayout gl_panel = new GroupLayout(panel);
		//水平方向
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label)
						.addComponent(label_4)
						.addComponent(label_5)
						.addComponent(label_6))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(book_RBidTxt, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(borrowButton)
							.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
							.addComponent(returnButton))
						.addComponent(book_RBwriterTxt, Alignment.LEADING, 286, 286, Short.MAX_VALUE)
						.addComponent(book_RBnameTxt, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
						.addComponent(book_RBpublishTxt, Alignment.LEADING, 286, 286, Short.MAX_VALUE)
						.addComponent(book_RBstatusTxt, Alignment.LEADING, 286, 286, Short.MAX_VALUE))
					.addContainerGap())
		);

		//垂直方向
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(book_RBnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(book_RBwriterTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(book_RBpublishTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(book_RBstatusTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(book_RBidTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(returnButton)
						.addComponent(borrowButton))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		//初始化图书表格，并设置为不可编辑
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTableMousePressed(e);//表格点击事件处理
			}
		});
		bookTable.setFillsViewportHeight(true);
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"编号", "图书名字", "作者", "出版社", "状态"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(bookTable);
		contentPane.setLayout(gl_contentPane);

		this.fillTable(new Book());//初始化图书信息，加载所有图书
	}
	/**
	 * 归还图书函数
	 * 处理用户归还图书的操作
	 */
	protected void returnBook() {
		//获取图书信息
		String bookId = this.book_RBidTxt.getText();
		String bookName = this.book_RBnameTxt.getText();
		String bookPublish =this.book_RBpublishTxt.getText();
		String bookWriter = this.book_RBwriterTxt.getText();
		String bookStatus = this.book_RBstatusTxt.getText();
		//验证输入
		if(StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书信息不能为空！");
			return ;
		}
		
		Connection con = null;
		try {
			//创建图书信息对象和图书对象
			BookInformation bi = new BookInformation(Integer.parseInt(bookId));
			Book book = new Book(Integer.parseInt(bookId));
			book.setBook_status("0");//把图书状态改变为0（即 未借出）

			//连接数据库
			con = conutil.loding();
			ResultSet rs = bookInformationDao.returnn(con,bi);//得到符合bookId的那一行。
			
			//验证用户权限：只有借此书的读者才能还此书，或者管理员可以归还任何图书
			if((rs.next() && rs.getString(2).equals(readerName))||((StringNull.isNotEmpty(adminName))&&rs.getString(5).equals("1"))) {
				bookInformationDao.delete(con, Integer.parseInt(bookId));//删除借书表里的哪一行数据
				bookDao.update(con, book);//更新图书表中的图书状态
				JOptionPane.showMessageDialog(null, "归还成功！");
				return ;
			}else{
				JOptionPane.showMessageDialog(null, "归还失败！");
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 设置当前读者ID
	 * 用于从其他类接收读者ID
	 * @param name 读者名称
	 */
	public static void setReaderId(String name) {
		readerName = name;
	}

	/**
	 * 设置当前管理员ID
	 * 用于从其他类接收管理员ID
	 * @param name 管理员名称
	 */

	public static void setAdminId(String name) {
		adminName = name;
	}
	/**
	 *  定义 借阅图书函数
	 *  处理用户借阅图书的操作
	 */
	private void borrowBook() {
		//获取图书信息
		String bookId = this.book_RBidTxt.getText();
		String bookName = this.book_RBnameTxt.getText();
		String bookPublish = this.book_RBpublishTxt.getText();
		String bookWriter = this.book_RBwriterTxt.getText();
		String bookStatus = this.book_RBstatusTxt.getText();

		//验证输入
		if(StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "图书信息不能为空！");
			return;
		}

		//验证图书状态
		if(bookStatus.equals("1")) {
			JOptionPane.showMessageDialog(null, "该图书已被借走了！");
			return;
		}
		
		Connection con = null;
		try {
			con = conutil.loding();//连接数据库

			//创建图书信息对象（根据当前用户是读者还是管理员）
			BookInformation bi;
			if(StringNull.isNotEmpty(readerName)) {
				bi = new BookInformation(Integer.parseInt(bookId), readerName, null, null, "1");//读者
			}else {
				bi = new BookInformation(Integer.parseInt(bookId), adminName, null, null, "1");//管理员
			}

			//创建图书对象并设置状态为已借出
			Book book =new Book(Integer.parseInt(bookId), bookName, bookWriter, bookPublish, "1");

			/*执行借阅操作*/
			//添加借书记录
			int find = bookInformationDao.add(con, bi);
			//更新图书状态
			int flag = bookDao.update(con, book);
			//检查操作结果
			if(1 != find ||1 != flag) {
				JOptionPane.showMessageDialog(null, "借阅失败！");
				return;
			}else {
				JOptionPane.showMessageDialog(null, "借阅成功！");
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "借阅失败！");
			return ;
		}finally {
			try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 图书表格鼠标点击事件处理
	 * 当用户点击表格中的某一行时，将该行数据显示在右侧面板中
	 * @param e 鼠标事件
	 */
	private void bookTableMousePressed(MouseEvent e) {
		//获取选中的行
		int row = bookTable.getSelectedRow();

		//将选中行的数据填充到右侧面板的文本框中
		book_RBnameTxt.setText((String)bookTable.getValueAt(row, 1));
		book_RBwriterTxt.setText((String)bookTable.getValueAt(row, 2));
		book_RBpublishTxt.setText((String)bookTable.getValueAt(row, 3));
		book_RBstatusTxt.setText((String)bookTable.getValueAt(row, 4));
		book_RBidTxt.setText((String)bookTable.getValueAt(row, 0));
	}
	/**
	 * 图书信息查询函数
	 * 根据用户输入的条件查询图书信息
	 * @param e 动作事件
	 */
	private void queryBookTable(ActionEvent e) {

		//获取用户输入的查询条件
		String book_name = this.book_nameTxt.getText();
		String book_writer = this.book_writerTxt.getText();
		String book_publish = this.book_publishTxt.getText();

		//创建图书对象并设置查询条件
		Book book =new Book();
		book.setBook_name(book_name);
		book.setBook_writer(book_writer);
		book.setBook_publish(book_publish);

		//填充表格数据
		this.fillTable(book);
	}


	/**
	 * 填充图书表格
	 * 根据指定的查询条件，从数据库获取图书信息并填充到表格中
	 * @param book 图书查询条件对象
	 */
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = conutil.loding();//连接数据库
			ResultSet rs = bookDao.query(con, book);//查询图书

			//将查询结果填充到表格中
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("book_id"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("book_writer"));
				v.add(rs.getString("book_publish"));

				//根据图书状态显示相应文本
				if(rs.getString("book_status").equals("1")) {
					v.add("已借出");
				}else {
					v.add("未借出");
				}
				dtm.addRow(v);//添加一行数据到表格
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conutil.closeCon(con);//关闭数据库连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
