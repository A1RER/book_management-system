package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.java.model.BookInformation;

/**
 * 图书借阅信息数据 访问对象，负责与数据库进行交互，提供图书借阅信息的添加，查询，删除操作
 */

public class BookInformationDao {
	/**
	 * book_borrow表添加操作
	 * 向book_information表中添加借阅信息
	 * @param con 数据库连接对象
	 * @param booki 包含要添加信息的图书借阅信息对象
	 * @return 返回受影响的行数
	 * @throws Exception 数据库操作异常
	 */
	public int add(Connection con, BookInformation booki) throws Exception{
		String sql = "insert into book_information values(?,?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		
		pstmt.setInt(1, booki.getBook_id());
		pstmt.setString(2, booki.getReader_name());
		pstmt.setTime(3, (Time) booki.getBorrow_data());
		pstmt.setTime(4, (Time) booki.getReturn_data());
		pstmt.setString(5, booki.getBook_status());
		
		return pstmt.executeUpdate();
	}
	/**
	 * book_borrow表归还操作
	 * 根据图书id查询图书借阅信息
	 * @param con 数据库连接对象
	 * @param booki 包含图书id的图书借阅信息对象
	 * @return 返回查询结果的resultset对象
	 * @throws SQLException 数据库操作异常
	 */
	public ResultSet returnn(Connection con, BookInformation booki) throws SQLException { 
		ResultSet rs = null;
		String sql = "select * from book_information where book_id = ?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, booki.getBook_id());
		rs = pstmt.executeQuery();
		return rs;
	}
	/**
	 * book_borrow表删除操作
	 * 根据图书id删除图书借阅信息
	 * @param con 数据库连接对象
	 * @param bookId 要删除的图书id
	 * @return 返回受影响的行数
	 * @throws Exception 数据库操作异常
	 */
	public int delete(Connection con,int bookId) throws Exception{
		String sql = "delete from book_information where book_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		return pstmt.executeUpdate();
	}
}
