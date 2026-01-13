package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Reader;
import com.java.util.MD5Util;

/**
 * �û�ע����Ϣ
 * @author admin
 *�û������ʶ��󣬸��������ݿ���н������ṩ�û�ע�ᣬ��¼����ѯ��ɾ���Ȳ���
 */
public class UserDao {
	/**
	 * �û�ע�᷽��
	 * @param con ���ݿ����Ӷ���
	 * @param reader �����û�ע����Ϣ�Ķ���
	 * @return ������Ӱ�������
	 * @throws Exception ���ݿ�����쳣
	 */
	public int register(Connection con,Reader reader) throws Exception{
		String sql = "insert into reader values(?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, reader.getReader_id());
		pstmt.setString(2, reader.getReader_name());
		pstmt.setString(3, reader.getReader_phone());
		// ʹ��MD5���������룬��ȫ����
		pstmt.setString(4, MD5Util.encrypt(reader.getReader_password()));
		return pstmt.executeUpdate();
	}
	/**
	 * �û���Ϣ����,�����ã��û�id������ͬ
	 * �û���½��֤����
	 * @param con ���ݿ����Ӷ���
	 * @param reader �����û���¼��Ϣ�Ķ���
	 * @return ������֤�ɹ�����û���Ϣ���������֤ʧ���򷵻�null
	 * @throws Exception ���ݿ�����쳣
	 */
	public Reader login(Connection con,Reader reader) throws Exception{
		Reader resultUser = null;
		// �Ȳ�ѯ�û��Ƿ����
		String sql ="select * from reader where reader_name=?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setString(1, reader.getReader_name());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			// ʹ��MD5��֤����
			String dbPassword = rs.getString("reader_password");
			if(MD5Util.verify(reader.getReader_password(), dbPassword)) {
				resultUser = new Reader();
				resultUser.setReader_id(rs.getInt("reader_id"));
				resultUser.setReader_name(rs.getString("reader_name"));
				resultUser.setReader_phone(rs.getString("reader_phone")); // �Ѿ��������ո����
				resultUser.setReader_password(dbPassword);
			}
		}
		return resultUser;
	}
	/**
	 * ͨ���û�Id��ѯ�û�������Ϣ
	 * @param con ���ݿ����Ӷ���
	 * @param readerId Ҫ��ѯ���û�id
	 * @return ���ز�ѯ�����ResultSet����
	 * @throws Exception ���ݿ�����쳣
	 */
	public ResultSet query(Connection con, int readerId)throws Exception{
		String sql = "select * from reader where reader_id = ?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeQuery();
	}
	/**
	 * ɾ���û�
	 * �����û�idɾ���û���Ϣ
	 * @param con ���ݿ����Ӷ���
	 * @param readerId Ҫɾ�����û�id
	 * @return ������Ӱ�������
	 * @throws Exception ���ݿ�����쳣
	 */
	public int delete(Connection con, int readerId)throws Exception{
		String sql = "delete from reader where reader_id = ?";
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		pstmt.setInt(1, readerId);
		return pstmt.executeUpdate();
	}
}
