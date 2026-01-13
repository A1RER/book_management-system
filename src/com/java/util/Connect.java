package com.java.util;

/**
 * 数据库工具类
 * 用于连接数据库
 * v1.0: 支持配置文件读取数据库连接信息
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connect {
	Connection con;

	// 数据库配置信息
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	// 静态代码块，类加载时读取配置文件
	static {
		try {
			Properties props = new Properties();
			// 从类路径加载配置文件
			InputStream in = Connect.class.getClassLoader().getResourceAsStream("db.properties");
			if (in == null) {
				System.err.println("警告: db.properties配置文件未找到，使用默认配置");
				// 使用默认配置作为fallback
				driver = "com.mysql.cj.Driver";
				url = "jdbc:mysql://localhost:3306/book_admin?serverTimezone=UTC";
				username = "root";
				password = "123456";
			} else {
				props.load(in);
				driver = props.getProperty("db.driver", "com.mysql.cj.Driver");
				url = props.getProperty("db.url");
				username = props.getProperty("db.username");
				password = props.getProperty("db.password");
				in.close();
			}
			// 加载驱动
			Class.forName(driver);
		} catch (Exception e) {
			System.err.println("数据库配置加载失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库
	 * @return Connection对象
	 */
	public Connection loding() {
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			System.err.println("数据库连接失败！");
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭数据库连接
	 * @param con 数据库连接对象
	 * @throws Exception
	 */
	public void closeCon(java.sql.Connection con) throws Exception {
        if(con != null && !con.isClosed()) {
            con.close();
        }
    }
}