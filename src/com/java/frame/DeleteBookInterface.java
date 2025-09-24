package com.java.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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

import com.java.dao.BookDao;
import com.java.model.Book;
import com.java.util.Connect;
import com.java.util.StringNull;
import java.awt.SystemColor;

/**
 * 删除图书界面，提供一个图形用户界面，用于删除图书信息
 */

public class DeleteBookInterface extends JFrame {

	private JPanel contentPane;
	private JTextField bookIdTxt;
	private Connect conutil= new Connect();
	private BookDao bookDao= new BookDao();
	/**
	 * Create the frame.
	 * 创建删除图书界面
	 */
	public DeleteBookInterface() {
		setBackground(Color.WHITE);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteBookInterface.class.getResource("C:\\Users\\Sakura\\Desktop\\new\\4\\books_management\\src\\com\\resource\\1.png")));
		setTitle("删除界面");
		setBounds(100, 100, 450, 355);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("图书编号：");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		bookIdTxt = new JTextField();
		bookIdTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		bookIdTxt.setColumns(10);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出确认对话框
				int jp = JOptionPane.showConfirmDialog(null, "是否确认删除！", "选择一个选项", JOptionPane.YES_NO_OPTION);
				
				if(jp ==JOptionPane.YES_OPTION ) {
					//确认删除，调用删除图书方法
					deleteBook();
				}else {
					JOptionPane.showMessageDialog(null, "请重新填写编号！");
				}	
			}
		});

		//界面组件初始化
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		//水平布局设置
		/**
		 * 水平布局逻辑：
		 * 整体使用并行组(ParallelGroup),所有组件左对齐(Alignment.LEADING)
		 *
		 * 嵌套序列组(SequentialGroup),包含
		 * ->左侧边距66像素（addGap(66)）
		 * ->标签（“图书编号：”）和输入框（宽度固定为164像素）
		 * ->删除按钮位于水平居中偏右位置（左侧160像素边距，按钮宽度131像素）
		 *
		 * 右侧边距为86像素（addContainerGap(86,Short.MAX_VALUE)）,确保界面右侧有足够空间
		 */
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							//图书id输入行
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(66)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
								//bookIdTxt宽度164像素
							.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
							//删除按钮行
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(160)
								//deleteButton宽度31像素
							.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);

		/**
		 * 垂直布局逻辑
		 *
		 * 整体使用并行组，所有组件顶部对齐（Alignment.LEADING）
		 *
		 * 嵌套序列组，包含
		 * 1.顶部105像素边距
		 * 2.标签和输入框在同一水平线上（Alignment.BASELINE）
		 * 3.输入框和删除按钮之间间距53像素
		 * 底部边距77像素，确保界面底部有足够空间
		 */
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
					//顶部边距 105像素 底部边距 77像素 按钮与输入框间距53像素
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)//顶部边框
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)//行间距
					.addComponent(deleteButton)
					.addContainerGap(77, Short.MAX_VALUE))//底部边距
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 定义 删除图书方法
	 */
	protected void deleteBook() {
		String bookId = this.bookIdTxt.getText();

		//检查图书编号是否为空
		if(StringNull.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "请输入图书的编号！");
		}
		
		Connection con = null;
		try {
			Book book = new Book(Integer.parseInt(bookId));
			con = conutil.loding();
			ResultSet rs = bookDao.query2(con, book);
			//检查图书是否存在
			if(rs.next()) {
				//删除图书信息
				bookDao.delete(con, Integer.parseInt(bookId));
				JOptionPane.showMessageDialog(null, "删除成功！");
				return ;
			}else {
				JOptionPane.showMessageDialog(null, "删除失败！");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败！");
			return;
		}finally {
			try {
				//关闭数据库连接
				conutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
