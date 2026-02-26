package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Reader;

/**
 * 用户注册信息
 * @author admin
 *用户书库访问对象，负责与数据库进行交互，提供用户注册，登录，查询和删除等操作
 */
public class UserDao {
	/**
	 * 用户注册方法
	 * @param con 数据库连接对象
	 * @param reader 包含用户注册信息的对象
	 * @return 返回受影响的行数
	 * @throws Exception 数据库操作异常
	 */
	public int register(Connection con,Reader reader) throws Exception{
		String sql = "insert into reader values(?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, reader.getReader_id());
		pstmt.setString(2, reader.getReader_name());
		pstmt.setString(3, reader.getReader_phone());
		pstmt.setString(4, reader.getReader_password());
		return pstmt.executeUpdate();
	}
	/**
	 * 用户信息查找,查重用，用户id不能相同
	 * 用户登陆验证方法
	 * @param con 数据库连接对象
	 * @param reader 包含用户登录信息的对象
	 * @return 返回验证成功后的用户信息对象，如果验证失败则返回null
	 * @throws Exception 数据库操作异常
	 */
	public Reader login(Connection con,Reader reader) throws Exception{
		Reader resultUser = null;
		String sql ="select * from reader where reader_name=? and reader_password=?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setString(1, reader.getReader_name());
		pstmt.setString(2, reader.getReader_password());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			resultUser = new Reader();
			resultUser.setReader_id(rs.getInt("reader_id"));
			resultUser.setReader_name(rs.getString("reader_name"));
			resultUser.setReader_phone(rs.getString("reader _phone"));//这多了一个空格，哈哈哈哈哈哈哈
			resultUser.setReader_password(rs.getString("reader_password"));
		}
		return resultUser;
	}
	/**
	 * 通过用户Id查询用户所有信息
	 * @param con 数据库连接对象
	 * @param readerId 要查询的用户id
	 * @return 返回查询结果的ResultSet对象
	 * @throws Exception 数据库操作异常
	 */
	public ResultSet query(Connection con, int readerId)throws Exception{
		String sql = "select * from reader where reader_id = ?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeQuery();
	}
	/**
	 * 删除用户
	 * 根据用户id删除用户信息
	 * @param con 数据库连接对象
	 * @param readerId 要删除的用户id
	 * @return 返回受影响的行数
	 * @throws Exception 数据库操作异常
	 */
	public int delete(Connection con, int readerId)throws Exception{
		String sql = "delete from reader where reader_id = ?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeUpdate();
	}
}
