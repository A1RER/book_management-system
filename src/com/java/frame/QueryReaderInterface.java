package com.java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.UserDao;
import com.java.util.Connect;
import com.java.util.StringNull;

import java.awt.Font;

/**
 * 用户查询界面
 * 提供根据用户编号查询用户信息的功能
 */


public class QueryReaderInterface extends JFrame {

	private JPanel contentPane;//内容面板
	private JTextField readerIdTxt;//用户编号输入框
	private JTable readerTable;//用户信息表格
	private UserDao readerDao = new UserDao();//用户数据访问对象
	private Connect conutil = new Connect();//数据库连接工具

	/**
	 * 创建用户查询界面
	 */
	public QueryReaderInterface() {
		setTitle("查找");//设置窗口标题
		setBounds(100, 100, 781, 471);//设置窗口位置和大小

		//初始化内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//初始化用户编号标签
		JLabel lblNewLabel = new JLabel("用户编号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

		//初始化用户编号输入框
		readerIdTxt = new JTextField();
		readerIdTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		readerIdTxt.setColumns(10);

		//初始化查询按钮并添加事件监听器
		JButton btnNewButton = new JButton("查找");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryReader();//调用查询用户方法
			}
		});

		//初始化滚动面板用于显示表格
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(readerIdTxt, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(79)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addGap(25))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(readerIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(90)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);

		//初始化用户信息表格，设置为不可编辑
		readerTable = new JTable();
		readerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"编号", "姓名", "电话", "密码"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(readerTable);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 用户查询方法
	 * 根据输入的用户编号从数据库查询用户信息并显示在表格中
	 */
	protected void queryReader() {
		String readerId = this.readerIdTxt.getText();//获取用户输入的编号
		DefaultTableModel dtm = (DefaultTableModel) readerTable.getModel();
		dtm.setRowCount(0);//清空数据表格

		//验证输入
		if(StringNull.isEmpty(readerId)) {
			JOptionPane.showMessageDialog(null, "用户编号不能为空");
			return ;
		}
		
		Connection con = null;
		try {
			con = conutil.loding();//连接数据库
			//查询指定编号的用户信息
			ResultSet rs = readerDao.query(con, Integer.parseInt(readerId));
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("reader_id"));//添加用户编号
				v.add(rs.getNString("reader_name"));//添加用户姓名
				v.add(rs.getString("reader _phone"));//添加用户电话，这确实有一个空格
				v.add(rs.getString("reader_password"));//添加用户密码
				dtm.addRow(v);//添加一行数据到表格
			}
		} catch (Exception e) {
			e.printStackTrace();//打印异常堆栈信息
		}
	}
}
