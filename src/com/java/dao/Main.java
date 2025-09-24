package com.java.dao;

import java.sql.ResultSet;

import com.java.frame.EnterInterface;
import com.java.util.Connect;

/**
 * 程序入口类，负责启动图书管理系统，显示登陆界面。
 */
public class Main {
	/**
	 * 程序入口方法
	 * @param args 命令行参数
	 */

	public static void main(String[] args) {
		//创建登陆界面对象
		EnterInterface ei =new EnterInterface();
		//设置登陆界面可见
		ei.setVisible(true);
	}
}
