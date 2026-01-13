package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.Book;
import com.java.util.StringNull;

/**
 * �������ڴ������鼮��ص����ݷ��ʲ�����������ѯ�����ӡ����º�ɾ���鼮��Ϣ�ķ�����
 * @author admin
 */
public class BookDao {
    /**
     * �����鼮�����е���Ϣ����ģ����ѯ�鼮��Ϣ��
     * @param con ���ݿ����Ӷ������������ݿ⽨������
     * @param book �鼮���󣬰����鼮�����ơ����ߡ����������Ϣ��������Ϊ��ѯ����
     * @return ����һ�� ResultSet ���󣬰��������ѯ�������鼮��Ϣ
     * @throws Exception �����ݿ���������쳣ʱ�׳����쳣
     */
    public ResultSet query(Connection con, Book book) throws Exception {
        // ���ڴ洢��ѯ�����ַ���������
        StringBuffer sql = new StringBuffer("select * from book where 1=1");

        // �������PreparedStatement�Ĳ���
        java.util.List<String> params = new java.util.ArrayList<>();

        // ����鼮���Ʋ�Ϊ�գ��������鼮���Ƶ�ģ����ѯ����
        if (StringNull.isNotEmpty(book.getBook_name())) {
            sql.append(" and book_name like ?");
            params.add("%" + book.getBook_name() + "%");
        }

        // ����鼮���߲�Ϊ�գ��������鼮���ߵ�ģ����ѯ����
        if (StringNull.isNotEmpty(book.getBook_writer())) {
            sql.append(" and book_writer like ?");
            params.add("%" + book.getBook_writer() + "%");
        }

        // ����鼮�����粻Ϊ�գ��������鼮�������ģ����ѯ����
        if (StringNull.isNotEmpty(book.getBook_publish())) {
            sql.append(" and book_publish like ?");
            params.add("%" + book.getBook_publish() + "%");
        }

        // ����PreparedStatement���������
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.toString());

        // ���������в���
        for (int i = 0; i < params.size(); i++) {
            pstmt.setString(i + 1, params.get(i));
        }

        // ִ�в�ѯ���������ؽ����
        return pstmt.executeQuery();
    }

    /**
     * �����鼮 ID ��ѯ�鼮��Ϣ��
     * @param con ���ݿ����Ӷ������������ݿ⽨������
     * @param book �鼮���󣬰���Ҫ��ѯ���鼮�� ID
     * @return ����һ�� ResultSet ���󣬰��������ѯ�������鼮��Ϣ
     * @throws Exception �����ݿ���������쳣ʱ�׳����쳣
     */
    public ResultSet query2(Connection con, Book book) throws Exception {
        // ���ڴ洢��ѯ�����ַ���
        String sql = "select * from book where book_id=?";
        // ����Ԥ����� SQL ������
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // ����Ԥ��������еĵ�һ������Ϊ�鼮�� ID
        pstmt.setInt(1, book.getBook_id());
        // ִ�в�ѯ���������ؽ����
        return pstmt.executeQuery();
    }

    /**
     * �����鼮�����е���Ϣ�����鼮��״̬��
     * @param con ���ݿ����Ӷ������������ݿ⽨������
     * @param book �鼮���󣬰���Ҫ���µ��鼮�� ID ���µ�״̬��Ϣ
     * @return ����һ����������ʾ��Ӱ�������
     * @throws Exception �����ݿ���������쳣ʱ�׳����쳣
     */
    public int update(Connection con, Book book) throws Exception {
        // ���ڴ洢���������ַ���
        String sql = "update book set book_status=? where book_id=?";
        // ����Ԥ����� SQL ������
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // ����Ԥ��������еĵ�һ������Ϊ�鼮����״̬
        pstmt.setString(1, book.getBook_status());
        // ����Ԥ��������еĵڶ�������Ϊ�鼮�� ID
        pstmt.setInt(2, book.getBook_id());
        // ִ�и��²�����������Ӱ�������
        return pstmt.executeUpdate();
    }

    /**
     * �����ݿ�������һ���µ��鼮��Ϣ��
     * @param con ���ݿ����Ӷ������������ݿ⽨������
     * @param book �鼮���󣬰���Ҫ���ӵ��鼮��������Ϣ
     * @return ����һ����������ʾ��Ӱ�������
     * @throws Exception �����ݿ���������쳣ʱ�׳����쳣
     */
    public int add(Connection con, Book book) throws Exception {
        // ���ڴ洢���������ַ���
        String sql = "insert into book values(?,?,?,?,?)";
        // ����Ԥ����� SQL ������
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        // ����Ԥ��������еĵ�һ������Ϊ�鼮�� ID
        pstmt.setInt(1, book.getBook_id());
        // ����Ԥ��������еĵڶ�������Ϊ�鼮������
        pstmt.setString(2, book.getBook_name());
        // ����Ԥ��������еĵ���������Ϊ�鼮������
        pstmt.setString(3, book.getBook_writer());
        // ����Ԥ��������еĵ��ĸ�����Ϊ�鼮�ĳ�����
        pstmt.setString(4, book.getBook_publish());
        // ����Ԥ��������еĵ��������Ϊ�鼮��״̬
        pstmt.setString(5, book.getBook_status());
        // ִ�в��������������Ӱ�������
        return pstmt.executeUpdate();
    }

    /**
     * �����鼮 ID ɾ�����ݿ��е��鼮��Ϣ��
     * @param con ���ݿ����Ӷ������������ݿ⽨������
     * @param bookId Ҫɾ�����鼮�� ID
     * @return ����һ����������ʾ��Ӱ�������
     * @throws Exception �����ݿ���������쳣ʱ�׳����쳣
     */
    public int delete(Connection con, int bookId) throws Exception {
        // ���ڴ洢ɾ�������ַ���
        String sql = "delete from book where book_id = ?";
        // ����Ԥ����� SQL ������
        PreparedStatement pstmt = con.prepareStatement(sql);
        // ����Ԥ��������еĵ�һ������ΪҪɾ�����鼮�� ID
        pstmt.setInt(1, bookId);
        // ִ��ɾ��������������Ӱ�������
        return pstmt.executeUpdate();
    }
}