package com.java.util;

/**
 * 数据库工具类
 * 用于连接数据库
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	Connection con;
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection loding() {//加载数据库
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
		}
		//连接数据库
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_admin?serverTimezone=UTC","root","123456");
		}
		catch(Exception e1){
		}
		
		return con;
	}
	/**
	 * 关闭数据库
	 * @param con
	 * @throws Exception
	 */
	public void closeCon (java.sql.Connection con)throws Exception {//关闭数据库
        if(con!=null){
            con.close();
        }
    }
}