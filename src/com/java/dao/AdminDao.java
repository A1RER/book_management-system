package com.java.dao;
/**
 * 管理员登录验证
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.java.model.Admin;


/**
 * 管理员数据访问对象，负责处理与管理员相关的数据库操作
 * 提供管理员登录认证、信息查询和更新等功能。
 */



public class AdminDao{
	/**
	 * 验证管理员登录信息
	 * @param con 数据库连接对象
	 * @param admin 包含用户名和密码的管理员对象
	 * @retuen 返回包含管理员信息的Resultret,如果验证失败则返回null
	 * @throws Exception 数据库操作异常
	 */

	public Admin login(Connection con,Admin admin) throws Exception{//登录查找信息
		Admin resultUser = null;
		//SQL查询语句，通过用户名和密码查找管理员
		String sql = "select * from admin where admin_name=? and admin_password=?";
		//创建预编译语句对象
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		//设置第一个参数：管理员用户名
		pstmt.setString(1,admin.getAdmin_name());//将指定参数设置为给定 Java String 值
		//设置第二个参数：管理员密码
		pstmt.setString(2,admin.getAdmin_password());
		//在PreparedStatement对象上执行SQL查询，并返回查询结果的ResultSet对象
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultUser = new Admin();
			resultUser.setAdmin_id(rs.getInt("admin_id"));
			resultUser.setAdmin_name(rs.getString("admin_name"));
			resultUser.setAdmin_phone(rs.getString("admin_phone"));
			resultUser.setAdmin_password(rs.getString("admin_password"));
		}
		return resultUser;
	}
}
