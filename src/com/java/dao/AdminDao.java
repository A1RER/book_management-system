package com.java.dao;
/**
 * ����Ա��¼��֤
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.java.model.Admin;
import com.java.util.MD5Util;


/**
 * ����Ա���ݷ��ʶ��󣬸����������Ա��ص����ݿ����
 * �ṩ����Ա��¼��֤����Ϣ��ѯ�͸��µȹ��ܡ�
 */



public class AdminDao{
	/**
	 * ��֤����Ա��¼��Ϣ
	 * @param con ���ݿ����Ӷ���
	 * @param admin �����û���������Ĺ���Ա����
	 * @retuen ���ذ�������Ա��Ϣ��Resultret,�����֤ʧ���򷵻�null
	 * @throws Exception ���ݿ�����쳣
	 */

	public Admin login(Connection con,Admin admin) throws Exception{//��¼������Ϣ
		Admin resultUser = null;
		//SQL��ѯ��䣬�Ȳ�ѯ����Ա�Ƿ����
		String sql = "select * from admin where admin_name=?";
		//����Ԥ����������
		PreparedStatement pstmt = (PreparedStatement)con.prepareStatement(sql);
		//���õ�һ������������Ա�û���
		pstmt.setString(1,admin.getAdmin_name());
		//��PreparedStatement������ִ��SQL��ѯ�������ز�ѯ�����ResultSet����
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			// ʹ��MD5��֤����
			String dbPassword = rs.getString("admin_password");
			if(MD5Util.verify(admin.getAdmin_password(), dbPassword)) {
				resultUser = new Admin();
				resultUser.setAdmin_id(rs.getInt("admin_id"));
				resultUser.setAdmin_name(rs.getString("admin_name"));
				resultUser.setAdmin_phone(rs.getString("admin_phone"));
				resultUser.setAdmin_password(dbPassword);
			}
		}
		return resultUser;
	}
}
